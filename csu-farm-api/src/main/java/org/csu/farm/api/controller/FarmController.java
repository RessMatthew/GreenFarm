package org.csu.farm.api.controller;

import org.csu.farm.bean.model.Farm;
import org.csu.farm.bean.param.FarmParam;
import org.csu.farm.service.FarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farm")
@Api(tags = "农场接口")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping("/farmInfo")
    @ApiOperation(value = "农场详细信息", notes = "根据农场id获取农场详细信息")
//    @ApiImplicitParam(name = "id", value = "农场ID", required = true, dataType = "Long")
    public ResponseEntity<Farm> farmInfo(@RequestParam long id){
        System.out.println("controller "+id);
        Farm farm = farmService.getFarmById(id);
        if(farm==null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(farm);
    }

    @GetMapping("/deleteFarm")
    @ApiOperation(value = "删除农场",notes = "根据传入的id删除对应id的农场,注意id需要存在")
//    @ApiImplicitParam(name = "id", value = "农场ID", required = true, dataType = "Long")
    public ResponseEntity<Void> deleteFarm(@RequestParam long id){
        System.out.println("controller "+id);
        farmService.deleteFarmById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allFarms")
    @ApiOperation(value = "农场详细信息", notes = "根据农场id获取农场详细信息")
    public ResponseEntity<List<Farm>> getAllFarms(){
        List<Farm> farmList = farmService.getAllFarms();
        return ResponseEntity.ok(farmList);
    }



    @PostMapping("/updateFarm")
    @ApiOperation(value = "更新农场数据", notes = "根据传入的id以及其他农场信息更新农场数据")
    public ResponseEntity<Void> updateFarm(FarmParam farmParam){
        Farm farm = new Farm();
        farm.setId(farmParam.getId());
        farm.setName(farmParam.getName());
        farm.setLongitude(farmParam.getLongitude());
        farm.setLatitude(farmParam.getLatitude());
        farm.setAddress(farmParam.getAddress());
        farm.setStatus(farmParam.getStatus());
        farm.setOpeningTime(farmParam.getOpeningTime());
        farm.setDistance(farmParam.getDistance());
        farmService.updateFarm(farm);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insertFarm")
    @ApiOperation(value = "插入农场数据",notes = "根据传入的ID以及其他农场信息插入农场数据,注意插入id不可以与已存在的重复")
    public ResponseEntity<Void> insertFarm(FarmParam farmParam){
        Farm farm = new Farm();
        farm.setId(farmParam.getId());
        farm.setName(farmParam.getName());
        farm.setLongitude(farmParam.getLongitude());
        farm.setLatitude(farmParam.getLatitude());
        farm.setAddress(farmParam.getAddress());
        farm.setStatus(farmParam.getStatus());
        farm.setOpeningTime(farmParam.getOpeningTime());
        farm.setDistance(farmParam.getDistance());
        farmService.insertFarm(farm);
        return ResponseEntity.ok().build();
    }




}
