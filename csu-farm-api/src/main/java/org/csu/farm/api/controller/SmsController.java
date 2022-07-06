

package org.csu.farm.api.controller;

import com.google.common.collect.Maps;
import org.csu.farm.bean.app.param.SendSmsParam;
import org.csu.farm.bean.enums.SmsType;
import org.csu.farm.security.api.util.SecurityUtils;
import org.csu.farm.service.SmsLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/p/sms")
@Api(tags="发送验证码接口")
public class SmsController {

	@Autowired
	private SmsLogService smsLogService;
    /**
     * 发送验证码接口
     */
    @PostMapping("/send")
    @ApiOperation(value="发送验证码", notes="用户的发送验证码")
    public ResponseEntity<Void> audit(@RequestBody SendSmsParam sendSmsParam) {
		String userId = SecurityUtils.getUser().getUserId();
		smsLogService.sendSms(SmsType.VALID, userId, sendSmsParam.getMobile(),Maps.newHashMap());
		
		return ResponseEntity.ok().build();
    }
}
