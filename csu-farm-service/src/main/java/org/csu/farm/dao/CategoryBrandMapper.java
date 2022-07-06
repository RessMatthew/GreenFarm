

package org.csu.farm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.csu.farm.bean.model.CategoryBrand;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface CategoryBrandMapper extends BaseMapper<CategoryBrand> {

	void insertCategoryBrand(@Param("categoryId") Long categoryId, @Param("brandIds") List<Long> brandIds);

	void deleteByCategoryId(Long categoryId);

	void deleteByBrandId(Long brandId);
}