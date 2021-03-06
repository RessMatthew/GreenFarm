

package org.csu.farm.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.csu.farm.bean.app.dto.*;
import org.csu.farm.bean.app.dto.ShopCartDto;
import org.csu.farm.bean.app.dto.ShopCartItemDto;
import org.csu.farm.bean.app.param.ChangeShopCartParam;
import org.csu.farm.bean.app.param.OrderItemParam;
import org.csu.farm.bean.app.param.ShopCartParam;
import org.csu.farm.bean.event.ShopCartEvent;
import org.csu.farm.bean.model.Basket;
import org.csu.farm.bean.model.Product;
import org.csu.farm.bean.model.ShopDetail;
import org.csu.farm.bean.model.Sku;
import org.csu.farm.common.util.Arith;
import org.csu.farm.common.util.CacheManagerUtil;
import org.csu.farm.dao.BasketMapper;
import org.csu.farm.service.BasketService;
import org.csu.farm.service.ProductService;
import org.csu.farm.service.ShopDetailService;
import org.csu.farm.service.SkuService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BasketServiceImpl extends ServiceImpl<BasketMapper, Basket> implements BasketService {

    private final BasketMapper basketMapper;

    private final CacheManagerUtil cacheManagerUtil;

    private final ApplicationContext applicationContext;

    private final SkuService skuService;

    private final ShopDetailService shopDetailService;

    private final ProductService productService;

    @Override
    @CacheEvict(cacheNames = "ShopCartItems", key = "#userId")
    public void deleteShopCartItemsByBasketIds(String userId, List<Long> basketIds) {
        basketMapper.deleteShopCartItemsByBasketIds(userId, basketIds);
    }

    @Override
    @CacheEvict(cacheNames = "ShopCartItems", key = "#userId")
    public void addShopCartItem(ChangeShopCartParam param, String userId) {
        Basket basket = new Basket();
        basket.setBasketCount(param.getCount());
        basket.setBasketDate(new Date());
        basket.setProdId(param.getProdId());
        basket.setShopId(param.getShopId());
        basket.setUserId(userId);
        basket.setSkuId(param.getSkuId());
        basket.setDistributionCardNo(param.getDistributionCardNo());
        basketMapper.insert(basket);
    }

    @Override
    @CacheEvict(cacheNames = "ShopCartItems", key = "#basket.userId")
    public void updateShopCartItem(Basket basket) {
        basketMapper.updateById(basket);
    }

    @Override
    @CacheEvict(cacheNames = "ShopCartItems", key = "#userId")
    public void deleteAllShopCartItems(String userId) {
        basketMapper.deleteAllShopCartItems(userId);
    }

    @Override
    public List<ShopCartItemDto> getShopCartItems(String userId) {
        // ??????????????????????????????????????????????????????????????????aop????????????????????????
        List<ShopCartItemDto> shopCartItemDtoList = cacheManagerUtil.getCache("ShopCartItems", userId);
        if (shopCartItemDtoList != null) {
            return shopCartItemDtoList;
        }
        shopCartItemDtoList = basketMapper.getShopCartItems(userId);
        for (ShopCartItemDto shopCartItemDto : shopCartItemDtoList) {
            shopCartItemDto.setProductTotalAmount(Arith.mul(shopCartItemDto.getProdCount(), shopCartItemDto.getPrice()));
        }
        cacheManagerUtil.putCache("ShopCartItems", userId, shopCartItemDtoList);
        return shopCartItemDtoList;
    }

    @Override
    public List<ShopCartItemDto> getShopCartExpiryItems(String userId) {
        return basketMapper.getShopCartExpiryItems(userId);
    }

    @Override
    @CacheEvict(cacheNames = "ShopCartItems", key = "#userId")
    public void cleanExpiryProdList(String userId) {
        basketMapper.cleanExpiryProdList(userId);
    }

    @Override
    @CacheEvict(cacheNames = "ShopCartItems", key = "#userId")
    public void updateBasketByShopCartParam(String userId, Map<Long, ShopCartParam> basketIdShopCartParamMap) {
        basketMapper.updateDiscountItemId(userId, basketIdShopCartParamMap);
    }

    @Override
    @CacheEvict(cacheNames = "ShopCartItems", key = "#userId")
    public void removeShopCartItemsCacheByUserId(String userId) {

    }

    @Override
    public List<String> listUserIdByProdId(Long prodId) {
        return basketMapper.listUserIdByProdId(prodId);
    }

    @Override
    public List<ShopCartDto> getShopCarts(List<ShopCartItemDto> shopCartItems) {
        // ????????????ID??????item
        Map<Long, List<ShopCartItemDto>> shopCartMap = shopCartItems.stream().collect(Collectors.groupingBy(ShopCartItemDto::getShopId));

        // ?????????????????????????????????
        List<ShopCartDto> shopCartDtos = Lists.newArrayList();
        for (Long shopId : shopCartMap.keySet()) {

            //??????????????????????????????
            List<ShopCartItemDto> shopCartItemDtoList = shopCartMap.get(shopId);

            // ????????????????????????????????????
            ShopCartDto shopCart = new ShopCartDto();

            //????????????
            shopCart.setShopId(shopId);
            shopCart.setShopName(shopCartItemDtoList.get(0).getShopName());

            applicationContext.publishEvent(new ShopCartEvent(shopCart, shopCartItemDtoList));

            shopCartDtos.add(shopCart);
        }

        return shopCartDtos;
    }

    @Override
    public List<ShopCartItemDto> getShopCartItemsByOrderItems(List<Long> basketId, OrderItemParam orderItem,String userId) {
        if (orderItem == null && CollectionUtil.isEmpty(basketId)) {
            return Collections.emptyList();
        }

        // ?????????????????????????????????????????????????????????????????????
        if (CollectionUtil.isEmpty(basketId) && orderItem != null) {

            Sku sku = skuService.getSkuBySkuId(orderItem.getSkuId());
            if (sku == null) {
                throw new RuntimeException("?????????????????????????????????");
            }
            Product prod = productService.getProductByProdId(orderItem.getProdId());
            if (prod == null) {
                throw new RuntimeException("?????????????????????????????????");
            }

            // ????????????????????????item
            ShopCartItemDto shopCartItemDto = new ShopCartItemDto();
            shopCartItemDto.setBasketId(-1L);
            shopCartItemDto.setSkuId(orderItem.getSkuId());
            shopCartItemDto.setProdCount(orderItem.getProdCount());
            shopCartItemDto.setProdId(orderItem.getProdId());
            shopCartItemDto.setSkuName(sku.getSkuName());
            shopCartItemDto.setPic(StrUtil.isBlank(sku.getPic())? prod.getPic() : sku.getPic());
            shopCartItemDto.setProdName(sku.getProdName());
            shopCartItemDto.setProductTotalAmount(Arith.mul(sku.getPrice(),orderItem.getProdCount()));
            shopCartItemDto.setPrice(sku.getPrice());
            shopCartItemDto.setDistributionCardNo(orderItem.getDistributionCardNo());
            shopCartItemDto.setBasketDate(new Date());
            ShopDetail shopDetail = shopDetailService.getShopDetailByShopId(orderItem.getShopId());
            shopCartItemDto.setShopId(shopDetail.getShopId());
            shopCartItemDto.setShopName(shopDetail.getShopName());
            return Collections.singletonList(shopCartItemDto);
        }
        List<ShopCartItemDto> dbShopCartItems = getShopCartItems(userId);

        // ????????????????????????????????????
        return dbShopCartItems
                .stream()
                .filter(shopCartItemDto -> basketId.contains(shopCartItemDto.getBasketId()))
                .collect(Collectors.toList());
    }


}
