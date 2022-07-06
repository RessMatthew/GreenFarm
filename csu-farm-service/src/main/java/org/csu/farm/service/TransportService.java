

package org.csu.farm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.Transport;
import org.springframework.cache.annotation.CacheEvict;


public interface TransportService extends IService<Transport> {

	void insertTransportAndTransfee(Transport transport);

	void updateTransportAndTransfee(Transport transport);

	void deleteTransportAndTransfeeAndTranscity(Long[] ids);

	Transport getTransportAndAllItems(Long transportId);

	@CacheEvict(cacheNames = "TransportAndAllItems", key = "#transportId")
	default void removeTransportAndAllItemsCache(Long transportId){}

}
