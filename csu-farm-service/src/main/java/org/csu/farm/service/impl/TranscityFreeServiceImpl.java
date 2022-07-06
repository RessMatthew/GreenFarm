

package org.csu.farm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.farm.dao.TranscityFreeMapper;
import org.csu.farm.service.TranscityFreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.csu.farm.bean.model.TranscityFree;


@Service
public class TranscityFreeServiceImpl extends ServiceImpl<TranscityFreeMapper, TranscityFree> implements TranscityFreeService {

    @Autowired
    private TranscityFreeMapper transcityFreeMapper;

}
