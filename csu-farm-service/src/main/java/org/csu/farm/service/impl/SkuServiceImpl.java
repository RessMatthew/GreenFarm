

package org.csu.farm.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.farm.dao.SkuMapper;
import org.csu.farm.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import org.csu.farm.bean.model.Sku;


@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

	@Override
	@Cacheable(cacheNames = "skuList", key = "#prodId")
	public List<Sku> listByProdId(Long prodId) {
		return skuMapper.listByProdId(prodId);
	}

	@Override
	@Cacheable(cacheNames = "sku", key = "#skuId")
	public Sku getSkuBySkuId(Long skuId) {
		return skuMapper.selectById(skuId);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = "sku", key = "#skuId"),
			@CacheEvict(cacheNames = "skuList", key = "#prodId")
	})
	public void removeSkuCacheBySkuId(Long skuId,Long prodId) {

	}

}
