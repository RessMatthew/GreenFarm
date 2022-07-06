

package org.csu.farm.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.app.dto.UserCollectionDto;
import org.csu.farm.bean.model.UserCollection;

/**
 * 用户收藏表
 */
public interface UserCollectionService extends IService<UserCollection> {
    IPage<UserCollectionDto> getUserCollectionDtoPageByUserId(Page page, String userId);
}
