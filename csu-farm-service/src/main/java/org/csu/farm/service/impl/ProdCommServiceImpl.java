

package org.csu.farm.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.farm.bean.app.dto.ProdCommDataDto;
import org.csu.farm.bean.app.dto.ProdCommDto;
import org.csu.farm.bean.model.ProdComm;
import org.csu.farm.common.util.Arith;
import org.csu.farm.dao.ProdCommMapper;
import org.csu.farm.service.ProdCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品评论
 */
@Service
public class ProdCommServiceImpl extends ServiceImpl<ProdCommMapper, ProdComm> implements ProdCommService {

    @Autowired
    private ProdCommMapper prodCommMapper;

    @Override
    public ProdCommDataDto getProdCommDataByProdId(Long prodId) {
        ProdCommDataDto prodCommDataDto=prodCommMapper.getProdCommDataByProdId(prodId);
        //计算出好评率
        if(prodCommDataDto.getPraiseNumber() == 0||prodCommDataDto.getNumber() == 0){
            prodCommDataDto.setPositiveRating(0.0);
        }else{
            prodCommDataDto.setPositiveRating(Arith.mul(Arith.div(prodCommDataDto.getPraiseNumber(),prodCommDataDto.getNumber()),100));
        }
        return prodCommDataDto;
    }

    @Override
    public IPage<ProdCommDto> getProdCommDtoPageByUserId(Page page, String userId) {
        return prodCommMapper.getProdCommDtoPageByUserId(page,userId);
    }

    @Override
    public IPage<ProdCommDto> getProdCommDtoPageByProdId(Page page, Long prodId, Integer evaluate) {

        IPage<ProdCommDto> prodCommDtos = prodCommMapper.getProdCommDtoPageByProdId(page, prodId, evaluate);
        prodCommDtos.getRecords().forEach(prodCommDto -> {
            // 匿名评价
            if (prodCommDto.getIsAnonymous() == 1) {
                prodCommDto.setNickName(null);
            }
        });
        return prodCommDtos;
    }

    @Override
    public IPage<ProdComm> getProdCommPage(Page page,ProdComm prodComm) {
        return prodCommMapper.getProdCommPage(page,prodComm);
    }
}
