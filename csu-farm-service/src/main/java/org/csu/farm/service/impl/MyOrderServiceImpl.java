

package org.csu.farm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.farm.bean.app.dto.MyOrderDto;
import org.csu.farm.bean.model.Order;
import org.csu.farm.common.util.PageAdapter;
import org.csu.farm.dao.OrderMapper;
import org.csu.farm.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements MyOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage<MyOrderDto> pageMyOrderByUserIdAndStatus(Page<MyOrderDto> page, String userId, Integer status) {
        page.setRecords(orderMapper.listMyOrderByUserIdAndStatus(new PageAdapter(page), userId, status));
        page.setTotal(orderMapper.countMyOrderByUserIdAndStatus(userId, status));
        return page;
    }

}
