

package org.csu.farm.bean.event;

import org.csu.farm.bean.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  取消订单的事件
 */
@Data
@AllArgsConstructor
public class CancelOrderEvent {

    private Order order;
}
