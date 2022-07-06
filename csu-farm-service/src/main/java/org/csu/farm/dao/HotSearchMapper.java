

package org.csu.farm.dao;

import org.csu.farm.bean.dto.HotSearchDto;
import org.csu.farm.bean.model.HotSearch;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface HotSearchMapper extends BaseMapper<HotSearch> {
    List<HotSearchDto> getHotSearchDtoByShopId(Long shopId);
}