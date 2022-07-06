package org.csu.farm.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value= "农场数据")
public class FarmParam {

    @ApiModelProperty(value = "农场id")
    private Long id;

    @ApiModelProperty(value = "农场名字")
    private String name;

    @ApiModelProperty(value = "农场位置经度")
    private double longitude;

    @ApiModelProperty(value = "农场位置纬度")
    private double latitude;

    @ApiModelProperty(value = "农场地址")
    private String address;

    @ApiModelProperty(value = "农场营业时间")
    private String openingTime;

    @ApiModelProperty(value = "农场营业状态")
    private int status;

    @ApiModelProperty(value = "距离")
    private double distance;

}
