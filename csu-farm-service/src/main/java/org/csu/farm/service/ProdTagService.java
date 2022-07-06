

package org.csu.farm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.ProdTag;

import java.util.List;

/**
 * 商品分组标签
 */
public interface ProdTagService extends IService<ProdTag> {

    List<ProdTag> listProdTag();

    void removeProdTag();
}
