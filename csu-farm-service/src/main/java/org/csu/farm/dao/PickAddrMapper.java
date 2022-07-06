

package org.csu.farm.dao;

import org.apache.ibatis.annotations.Param;

import org.csu.farm.bean.model.PickAddr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface PickAddrMapper extends BaseMapper<PickAddr> {

	void deleteByIds(@Param("ids") Long[] ids);
}