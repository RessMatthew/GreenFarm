

package org.csu.farm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.farm.dao.ProdPropValueMapper;
import org.csu.farm.service.ProdPropValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.csu.farm.bean.model.ProdPropValue;


@Service
public class ProdPropValueServiceImpl extends ServiceImpl<ProdPropValueMapper, ProdPropValue> implements ProdPropValueService {

    @Autowired
    private ProdPropValueMapper prodPropValueMapper;

}
