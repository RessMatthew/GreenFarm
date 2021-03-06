

package org.csu.farm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.Area;

import java.util.List;


public interface AreaService extends IService<Area> {

    /**
     * 通过pid 查找地址接口
     *
     * @param pid 父id
     * @return
     */
    List<Area> listByPid(Long pid);

    /**
     * 通过pid 清除地址缓存
     *
     * @param pid
     */
    void removeAreaCacheByParentId(Long pid);

}
