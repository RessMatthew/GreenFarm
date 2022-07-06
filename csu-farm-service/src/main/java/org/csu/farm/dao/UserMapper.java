

package org.csu.farm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.farm.bean.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

	User getUserByUserMail(@Param("userMail") String userMail);

	User selectOneByUserName(@Param("userName") String userName);
}
