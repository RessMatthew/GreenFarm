

package org.csu.farm.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.farm.bean.app.dto.OrderCountData;
import org.csu.farm.bean.app.dto.ShopCartOrderMergerDto;
import org.csu.farm.bean.event.CancelOrderEvent;
import org.csu.farm.bean.event.ReceiptOrderEvent;
import org.csu.farm.bean.event.SubmitOrderEvent;
import org.csu.farm.bean.model.Order;
import org.csu.farm.bean.model.OrderItem;
import org.csu.farm.bean.param.OrderParam;
import org.csu.farm.common.util.PageAdapter;
import org.csu.farm.dao.OrderItemMapper;
import org.csu.farm.dao.OrderMapper;
import org.csu.farm.dao.ProductMapper;
import org.csu.farm.dao.SkuMapper;
import org.csu.farm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductMapper productMapper;


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public Order getOrderByOrderNumber(String orderNumber) {
        return orderMapper.getOrderByOrderNumber(orderNumber);
    }

    @Override
    @CachePut(cacheNames = "ConfirmOrderCache", key = "#userId")
    public ShopCartOrderMergerDto putConfirmOrderCache(String userId, ShopCartOrderMergerDto shopCartOrderMergerDto) {
        return shopCartOrderMergerDto;
    }

    @Override
    @Cacheable(cacheNames = "ConfirmOrderCache", key = "#userId")
    public ShopCartOrderMergerDto getConfirmOrderCache(String userId) {
        return null;
    }

    @Override
    @CacheEvict(cacheNames = "ConfirmOrderCache", key = "#userId")
    public void removeConfirmOrderCache(String userId) {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Order> submit(String userId, ShopCartOrderMergerDto mergerOrder) {
        List<Order> orderList = new ArrayList<>();
        // ????????????????????????
        eventPublisher.publishEvent(new SubmitOrderEvent(mergerOrder, orderList));

        // ????????????
        orderList.forEach(order -> orderMapper.insert(order));
        List<OrderItem> orderItems = orderList.stream().flatMap(order -> order.getOrderItems().stream()).collect(Collectors.toList());
        // ??????????????????????????????
        orderItemMapper.insertBatch(orderItems);


        return orderList;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delivery(Order order) {
        orderMapper.updateById(order);
        // ????????????????????????
        Map<String, String> params = new HashMap<>(16);
        params.put("orderNumber", order.getOrderNumber());
//		Delivery delivery = deliveryMapper.selectById(order.getDvyId());
//		params.put("dvyName", delivery.getDvyName());
//		params.put("dvyFlowId", order.getDvyFlowId());
//		smsLogService.sendSms(SmsType.NOTIFY_DVY, order.getUserId(), order.getMobile(), params);

    }

    @Override
    public List<Order> listOrderAndOrderItems(Integer orderStatus, DateTime lessThanUpdateTime) {
        return orderMapper.listOrderAndOrderItems(orderStatus, lessThanUpdateTime);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrders(List<Order> orders) {

        orderMapper.cancelOrders(orders);
        List<OrderItem> allOrderItems = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            allOrderItems.addAll(orderItems);
            eventPublisher.publishEvent(new CancelOrderEvent(order));
        }
        if (CollectionUtil.isEmpty(allOrderItems)) {
            return;
        }
        Map<Long, Integer> prodCollect = new HashMap<>(16);
        allOrderItems.stream().collect(Collectors.groupingBy(OrderItem::getProdId)).forEach((prodId, orderItems) -> {
            int prodTotalNum = orderItems.stream().mapToInt(OrderItem::getProdCount).sum();
            prodCollect.put(prodId, prodTotalNum);
        });
        productMapper.returnStock(prodCollect);

        Map<Long, Integer> skuCollect = new HashMap<>(16);
        allOrderItems.stream().collect(Collectors.groupingBy(OrderItem::getSkuId)).forEach((skuId, orderItems) -> {
            int prodTotalNum = orderItems.stream().mapToInt(OrderItem::getProdCount).sum();
            skuCollect.put(skuId, prodTotalNum);
        });
        skuMapper.returnStock(skuCollect);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmOrderWithoutComm(List<Order> orders) {
        orderMapper.confirmOrderWithoutComm(orders);
        for (Order order : orders) {
            eventPublisher.publishEvent(new ReceiptOrderEvent(order));
        }

    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmOrder(List<Order> orders) {
        orderMapper.confirmOrder(orders);
        for (Order order : orders) {
            eventPublisher.publishEvent(new ReceiptOrderEvent(order));
        }

    }



    @Override
    public List<Order> listOrdersDetialByOrder(Order order, Date startTime, Date endTime) {
        return orderMapper.listOrdersDetialByOrder(order, startTime, endTime);
    }

    @Override
    public IPage<Order> pageOrdersDetialByOrderParam(Page<Order> page, OrderParam orderParam) {
        page.setRecords(orderMapper.listOrdersDetialByOrderParam(new PageAdapter(page), orderParam));
        page.setTotal(orderMapper.countOrderDetial(orderParam));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrders(List<Order> orders) {
        orderMapper.deleteOrders(orders);
    }

    @Override
    public OrderCountData getOrderCount(String userId) {
        return orderMapper.getOrderCount(userId);
    }


}
