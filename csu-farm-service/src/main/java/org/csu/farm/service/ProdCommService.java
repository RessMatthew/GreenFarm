

package org.csu.farm.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.app.dto.ProdCommDataDto;
import org.csu.farm.bean.app.dto.ProdCommDto;
import org.csu.farm.bean.model.ProdComm;


/**
 * 商品评论
 */
public interface ProdCommService extends IService<ProdComm> {
    ProdCommDataDto getProdCommDataByProdId(Long prodId);

    IPage<ProdCommDto> getProdCommDtoPageByUserId(Page page,String userId);

    IPage<ProdCommDto> getProdCommDtoPageByProdId(Page page, Long prodId, Integer evaluate);

    IPage<ProdComm> getProdCommPage(Page page,ProdComm prodComm);

}
