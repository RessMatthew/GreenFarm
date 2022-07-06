

package org.csu.farm.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/notice/pay")
@AllArgsConstructor
public class PayNoticeController {
//模拟支付不需要回调
//    /**
//     * 小程序支付
//     */
//    private final WxPayService wxMiniPayService;
//
//    private final PayService payService;
//
//
//    @RequestMapping("/order")
//    public ResponseEntity<Void> submit(@RequestBody String xmlData) throws WxPayException {
//        WxPayOrderNotifyResult parseOrderNotifyResult = wxMiniPayService.parseOrderNotifyResult(xmlData);
//
//        String payNo = parseOrderNotifyResult.getOutTradeNo();
//        String bizPayNo = parseOrderNotifyResult.getTransactionId();
//
//        // 根据内部订单号更新order settlement
//        payService.paySuccess(payNo, bizPayNo);
//
//
//        return ResponseEntity.ok().build();
//    }
}