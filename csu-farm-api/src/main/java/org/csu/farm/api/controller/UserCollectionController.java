

package org.csu.farm.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.csu.farm.bean.app.dto.ProductDto;
import org.csu.farm.bean.app.dto.UserCollectionDto;
import org.csu.farm.bean.model.Product;
import org.csu.farm.bean.model.UserCollection;
import org.csu.farm.common.exception.YamiShopBindException;
import org.csu.farm.common.util.PageParam;
import org.csu.farm.security.api.util.SecurityUtils;
import org.csu.farm.service.ProductService;
import org.csu.farm.service.UserCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/p/user/collection")
@Api(tags = "收藏接口")
@AllArgsConstructor
public class UserCollectionController {

    private final UserCollectionService userCollectionService;

    private final ProductService productService;

    @GetMapping("/page")
    @ApiOperation(value = "分页返回收藏数据", notes = "根据用户id获取")
    public ResponseEntity<IPage<UserCollectionDto>> getUserCollectionDtoPageByUserId(PageParam page) {
        return ResponseEntity.ok(userCollectionService.getUserCollectionDtoPageByUserId(page, SecurityUtils.getUser().getUserId()));
    }

    @GetMapping("isCollection")
    @ApiOperation(value = "根据商品id获取该商品是否在收藏夹中", notes = "传入收藏商品id")
    public ResponseEntity<Boolean> isCollection(Long prodId) {
        if (productService.count(new LambdaQueryWrapper<Product>()
                .eq(Product::getProdId, prodId)) < 1) {
            throw new YamiShopBindException("该商品不存在");
        }
        return ResponseEntity.ok(userCollectionService.count(new LambdaQueryWrapper<UserCollection>()
                .eq(UserCollection::getProdId, prodId)
                .eq(UserCollection::getUserId, SecurityUtils.getUser().getUserId())) > 0);
    }

    @PostMapping("/addOrCancel")
    @ApiOperation(value = "添加/取消收藏", notes = "传入收藏商品id,如果商品未收藏则收藏商品，已收藏则取消收藏")
    @ApiImplicitParam(name = "prodId", value = "商品id", required = true, dataType = "Long")
    public ResponseEntity<Void> addOrCancel(@RequestBody Long prodId) {
        if (Objects.isNull(productService.getProductByProdId(prodId))) {
            throw new YamiShopBindException("该商品不存在");
        }
        String userId = SecurityUtils.getUser().getUserId();
        if (userCollectionService.count(new LambdaQueryWrapper<UserCollection>()
                .eq(UserCollection::getProdId, prodId)
                .eq(UserCollection::getUserId, userId)) > 0) {
            userCollectionService.remove(new LambdaQueryWrapper<UserCollection>()
                    .eq(UserCollection::getProdId, prodId)
                    .eq(UserCollection::getUserId, userId));
        } else {
            UserCollection userCollection = new UserCollection();
            userCollection.setCreateTime(new Date());
            userCollection.setUserId(userId);
            userCollection.setProdId(prodId);
            userCollectionService.save(userCollection);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * 查询用户收藏商品数量
     */
    @GetMapping("count")
    @ApiOperation(value = "查询用户收藏商品数量", notes = "查询用户收藏商品数量")
    public int findUserCollectionCount() {
        String userId = SecurityUtils.getUser().getUserId();
        return userCollectionService.count(new LambdaQueryWrapper<UserCollection>().eq(UserCollection::getUserId, userId));
    }

    @GetMapping("/prods")
    @ApiOperation(value = "获取用户收藏商品列表", notes = "获取用户收藏商品列表")
    public ResponseEntity<IPage<ProductDto>> collectionProds(PageParam page) {
        String userId = SecurityUtils.getUser().getUserId();
        IPage<ProductDto> productDtoIPage = productService.collectionProds(page, userId);
        return ResponseEntity.ok(productDtoIPage);
    }

}
