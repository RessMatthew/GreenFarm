

package org.csu.farm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.Message;


public interface MessageService extends IService<Message> {

	void deleteByIds(Long[] ids);

}
