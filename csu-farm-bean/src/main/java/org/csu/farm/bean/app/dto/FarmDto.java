package org.csu.farm.bean.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FarmDto {

    @ApiModelProperty(value = "农场ID", required = true)
    private Long id;

    @ApiModelProperty(value = "农场名")
    private String name;

    @ApiModelProperty(value = "农场经度")
    private String longitude;

    @ApiModelProperty(value = "农场纬度")
    private String latitude;

    @ApiModelProperty(value = "农场地址")
    private String address;

    @ApiModelProperty(value = "农场营业时间")
    private String opening_time;

    @ApiModelProperty(value = "农场营业状态")
    private String status;

    @ApiModelProperty(value = "距离")
    private String distance;


}
