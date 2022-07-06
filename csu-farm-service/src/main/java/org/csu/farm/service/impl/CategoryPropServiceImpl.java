

package org.csu.farm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.farm.dao.CategoryPropMapper;
import org.csu.farm.service.CategoryPropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.csu.farm.bean.model.CategoryProp;


@Service
public class CategoryPropServiceImpl extends ServiceImpl<CategoryPropMapper, CategoryProp> implements CategoryPropService {

    @Autowired
    private CategoryPropMapper categoryPropMapper;

}
