

package org.csu.farm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.User;
import org.csu.farm.bean.param.UserRegisterParam;


public interface UserService extends IService<User> {

    User getUserByUserId(String userId);

    void validate(UserRegisterParam userRegisterParam, String checkRegisterSmsFlag);
}
