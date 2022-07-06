

package org.csu.farm.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.app.dto.NoticeDto;
import org.csu.farm.bean.model.Notice;

import java.util.List;

/**
 * 公告管理
 */
public interface NoticeService extends IService<Notice> {

    List<Notice> listNotice();

    void removeNoticeList();

    Page<NoticeDto> pageNotice(Page<NoticeDto> page);

    Notice getNoticeById(Long noticeId);

    void removeNoticeById(Long noticeId);
}
