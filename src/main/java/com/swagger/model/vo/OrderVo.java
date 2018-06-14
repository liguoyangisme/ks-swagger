package com.swagger.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: User
 * @Author 袁伟倩
 * @Date 2018-6-418:59
 * @Version 1.0
 */
@ApiModel(value="user对象",description="用户对象user返回")
public class OrderVo {
    @ApiModelProperty(value="用户id",name="id")
    private Long id;
    @ApiModelProperty(value="用户名",name="name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
