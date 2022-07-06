

package org.csu.farm.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.OrderItem;


public interface OrderItemService extends IService<OrderItem> {

	List<OrderItem> getOrderItemsByOrderNumber(String orderNumber);

}
