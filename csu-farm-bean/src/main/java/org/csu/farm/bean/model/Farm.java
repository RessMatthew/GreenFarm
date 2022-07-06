package org.csu.farm.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("farm")
public class Farm implements Serializable {

    @TableId(type = IdType.INPUT)
    private Long id;

    private String name;

    private double longitude;

    private double latitude;

    private String address;

    private String openingTime;

    private int status;

    private double distance;

}
