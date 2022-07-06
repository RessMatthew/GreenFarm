

package org.csu.farm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.PickAddr;


public interface PickAddrService extends IService<PickAddr> {

	void deleteByIds(Long[] ids);

}
