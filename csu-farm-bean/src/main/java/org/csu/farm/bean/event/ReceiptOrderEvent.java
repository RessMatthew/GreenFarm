

package org.csu.farm.bean.event;

import org.csu.farm.bean.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 确认收货的事件
 */
@Data
@AllArgsConstructor
public class ReceiptOrderEvent {

    private Order order;
}
