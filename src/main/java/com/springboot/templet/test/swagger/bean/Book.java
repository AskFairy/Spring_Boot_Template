package com.springboot.templet.test.swagger.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "图书实体")
public class Book {

    @ApiModelProperty(value = "唯一ID")
    private long id;

    @ApiModelProperty(value = "图书名称")
    private String name;

    @ApiModelProperty(value = "价格")
    private double price;

}
