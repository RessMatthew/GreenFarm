

package org.csu.farm.bean.event;

import org.csu.farm.bean.app.dto.ShopCartOrderMergerDto;
import org.csu.farm.bean.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 提交订单时的事件
 */
@Data
@AllArgsConstructor
public class SubmitOrderEvent {
    /**
     * 完整的订单信息
     */
    private final ShopCartOrderMergerDto mergerOrder;

    private List<Order> orders;

}
