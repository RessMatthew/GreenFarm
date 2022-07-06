

package org.csu.farm.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.Sku;


public interface SkuService extends IService<Sku> {

	/**
	 * 根据商品id获取商品中的sku列表（将会被缓存起来）
	 * @param prodId 商品id
	 * @return sku列表
	 */
	List<Sku> listByProdId(Long prodId);

	/**
	 * 根据skuId获取sku信息（将会被缓存起来）
	 * @param skuId
	 * @return
	 */
	Sku getSkuBySkuId(Long skuId);

	void removeSkuCacheBySkuId(Long skuId,Long prodId);
}
