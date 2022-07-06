

package org.csu.farm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.farm.dao.UserAddrOrderMapper;
import org.csu.farm.service.UserAddrOrderService;
import org.springframework.stereotype.Service;

import org.csu.farm.bean.model.UserAddrOrder;

@Service
public class UserAddrOrderServiceImpl extends ServiceImpl<UserAddrOrderMapper, UserAddrOrder> implements UserAddrOrderService {

}
