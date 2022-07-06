

package org.csu.farm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.csu.farm.bean.model.IndexImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface IndexImgMapper extends BaseMapper<IndexImg> {

	void deleteIndexImgsByIds(@Param("ids") Long[] ids);

	List<IndexImg> listIndexImgs();
}
