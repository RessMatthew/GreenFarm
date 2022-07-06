

package org.csu.farm.dao;

import org.csu.farm.bean.model.ShopDetail;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface ShopDetailMapper extends BaseMapper<ShopDetail> {

    Integer getIsDistributionByShopId(Long shopId);
}