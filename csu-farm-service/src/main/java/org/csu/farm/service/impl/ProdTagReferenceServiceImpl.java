

package org.csu.farm.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.farm.bean.model.ProdTagReference;
import org.csu.farm.dao.ProdTagReferenceMapper;
import org.csu.farm.service.ProdTagReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分组标签引用
 */
@Service
public class ProdTagReferenceServiceImpl extends ServiceImpl<ProdTagReferenceMapper, ProdTagReference> implements ProdTagReferenceService {

    @Autowired
    private ProdTagReferenceMapper prodTagReferenceMapper;

    @Override
    public List<Long> listTagIdByProdId(Long prodId) {
        return prodTagReferenceMapper.listTagIdByProdId(prodId);
    }
}
