

package org.csu.farm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.farm.bean.model.ProdProp;
import org.csu.farm.common.util.PageAdapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProdPropMapper extends BaseMapper<ProdProp> {

    List<ProdProp> listPropAndValue(@Param("adapter") PageAdapter adapter, @Param("prodProp") ProdProp prodProp);

    long countPropAndValue(@Param("prodProp") ProdProp prodProp);

    int deleteByPropId(@Param("propId") Long propId, @Param("rule") Integer rule, @Param("shopId") Long shopId);

    List<ProdProp> listByCategoryId(Long categoryId);

    ProdProp getProdPropByPropNameAndShopId(@Param("propName") String propName, @Param("shopId") Long shopId, @Param("rule") Integer rule);
}
