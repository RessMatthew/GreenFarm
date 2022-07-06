

package org.csu.farm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.enums.SmsType;
import org.csu.farm.bean.model.SmsLog;

import java.util.Map;


public interface SmsLogService extends IService<SmsLog> {

	void sendSms(SmsType smsType, String userId, String mobile, Map<String, String> params);
}
