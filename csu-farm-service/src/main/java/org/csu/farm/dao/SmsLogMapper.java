

package org.csu.farm.dao;

import org.apache.ibatis.annotations.Param;

import org.csu.farm.bean.model.SmsLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface SmsLogMapper extends BaseMapper<SmsLog> {

	void invalidSmsByMobileAndType(@Param("mobile") String mobile, @Param("type") Integer type);
}