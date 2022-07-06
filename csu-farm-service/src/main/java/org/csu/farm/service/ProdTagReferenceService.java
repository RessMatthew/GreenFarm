

package org.csu.farm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.ProdTagReference;

import java.util.List;

/**
 * 分组标签引用
 */
public interface ProdTagReferenceService extends IService<ProdTagReference> {

    List<Long> listTagIdByProdId(Long prodId);
}
