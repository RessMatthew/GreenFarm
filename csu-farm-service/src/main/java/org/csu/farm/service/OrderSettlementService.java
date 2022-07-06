

package org.csu.farm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.OrderSettlement;


public interface OrderSettlementService extends IService<OrderSettlement> {

	/**
	 * 根据内部订单号更新order settlement
	 */
	void updateSettlementsByPayNo(String outTradeNo, String transactionId);
}
