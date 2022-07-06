

package org.csu.farm.dao;

import org.csu.farm.bean.model.Message;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface MessageMapper extends BaseMapper<Message> {

	void deleteByIds(Long[] ids);
}