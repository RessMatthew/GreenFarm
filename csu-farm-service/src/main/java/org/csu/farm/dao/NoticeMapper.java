

package org.csu.farm.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.csu.farm.bean.app.dto.NoticeDto;
import org.csu.farm.bean.model.Notice;

/**
 * 公告管理
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    Page<NoticeDto> pageNotice(Page<NoticeDto> page);
}
