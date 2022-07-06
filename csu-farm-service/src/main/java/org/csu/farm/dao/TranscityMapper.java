

package org.csu.farm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.csu.farm.bean.model.Transcity;
import org.csu.farm.bean.model.TranscityFree;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface TranscityMapper extends BaseMapper<Transcity> {

	void insertTranscities(@Param("transcities") List<Transcity> transcities);

	void insertTranscityFrees(@Param("transcityFrees") List<TranscityFree> transcityFrees);

	List<Transcity> listTranscityAndArea(@Param("transfeeId") Long transfeeId);

	void deleteBatchByTransfeeIds(@Param("transfeeIds") List<Long> transfeeIds);

	void deleteBatchByTransfeeFreeIds(@Param("transfeeFreeIds") List<Long> transfeeFreeIds);

}