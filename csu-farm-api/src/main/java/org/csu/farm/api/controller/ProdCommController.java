

package org.csu.farm.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.csu.farm.bean.app.dto.ProdCommDataDto;
import org.csu.farm.bean.app.dto.ProdCommDto;
import org.csu.farm.bean.app.param.ProdCommParam;
import org.csu.farm.bean.model.ProdComm;
import org.csu.farm.common.util.PageParam;
import org.csu.farm.security.api.util.SecurityUtils;
import org.csu.farm.service.ProdCommService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/prodComm")
@Api(tags = "评论接口")
@AllArgsConstructor
public class ProdCommController {

    private final ProdCommService prodCommService;


    @GetMapping("/prodCommData")
    @ApiOperation(value = "返回商品评论数据(好评率 好评数量 中评数 差评数)", notes = "根据商品id获取")
    public ResponseEntity<ProdCommDataDto> getProdCommData(Long prodId) {
        return ResponseEntity.ok(prodCommService.getProdCommDataByProdId(prodId));
    }

    @GetMapping("/prodCommPageByUser")
    @ApiOperation(value = "根据用户返回评论分页数据", notes = "传入页码")
    public ResponseEntity<IPage<ProdCommDto>> getProdCommPage(PageParam page) {
        return ResponseEntity.ok(prodCommService.getProdCommDtoPageByUserId(page, SecurityUtils.getUser().getUserId()));
    }

    @GetMapping("/prodCommPageByProd")
    @ApiOperation(value = "根据商品返回评论分页数据", notes = "传入商品id和页码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prodId", value = "商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "evaluate", value = "-1或null 全部，0好评 1中评 2差评 3有图", required = true, dataType = "Long"),
    })
    public ResponseEntity<IPage<ProdCommDto>> getProdCommPageByProdId(PageParam page, Long prodId, Integer evaluate) {
        return ResponseEntity.ok(prodCommService.getProdCommDtoPageByProdId(page, prodId, evaluate));
    }

    @PostMapping("/addProdComm")
    @ApiOperation(value = "添加评论")
    public ResponseEntity<Void> saveProdCommPage(@Valid @RequestBody ProdCommParam prodCommParam) {
        ProdComm prodComm = new ProdComm();
        //prodComm.setProdId(69L);
        prodComm.setProdId(prodCommParam.getProdId());
        prodComm.setOrderItemId(prodCommParam.getOrderItemId());
        //这句有点危险，尚未登入时测试
        prodComm.setUserId("9d42ae45bc0f4a4899db50f522caa3fe");
        //prodComm.setUserId(SecurityUtils.getUser().getUserId());
        prodComm.setScore(prodCommParam.getScore());
        prodComm.setContent(prodCommParam.getContent());
        prodComm.setPics(null);
//        prodComm.setPics(prodCommParam.getPics());
        prodComm.setIsAnonymous(0);
//        prodComm.setIsAnonymous(prodCommParam.getIsAnonymous());
        prodComm.setRecTime(new Date());
        prodComm.setStatus(0);
        prodComm.setEvaluate(0);


        prodCommService.save(prodComm);
        return ResponseEntity.ok().build();
//        ProdComm prodComm = new ProdComm();
//        prodComm.setProdId(prodCommParam.getProdId());
//        prodComm.setOrderItemId(prodCommParam.getOrderItemId());
//        //这句有点危险，尚未登入时测试
//        prodComm.setUserId(SecurityUtils.getUser().getUserId());
//        prodComm.setScore(prodCommParam.getScore());
//        prodComm.setContent(prodCommParam.getContent());
//        prodComm.setPics(prodCommParam.getPics());
//        prodComm.setIsAnonymous(prodCommParam.getIsAnonymous());
//        prodComm.setRecTime(new Date());
//        prodComm.setStatus(0);
//        prodComm.setEvaluate(prodCommParam.getEvaluate());
//        prodCommService.save(prodComm);
//        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @ApiOperation(value = "删除评论", notes = "根据id删除")
    public ResponseEntity<Void> deleteProdComm(Long prodCommId) {
        prodCommService.removeById(prodCommId);
        return ResponseEntity.ok().build();
    }
}
