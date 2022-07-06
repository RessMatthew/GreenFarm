package org.csu.farm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.Farm;

import java.util.List;

public interface FarmService extends IService<Farm> {

    Farm getFarmById(long id);

    void updateFarm(Farm farm);

    List<Farm> getAllFarms();

    void insertFarm(Farm farm);

    void deleteFarmById(long id);

}
