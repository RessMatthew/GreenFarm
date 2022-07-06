

package org.csu.farm.service;

import org.csu.farm.bean.app.param.PayParam;
import org.csu.farm.bean.pay.PayInfoDto;

import java.util.List;


public interface PayService {


    PayInfoDto pay(String userId, PayParam payParam);

    List<String> paySuccess(String payNo, String bizPayNo);

}
