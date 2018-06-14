package com.swagger.controller;

import com.swagger.annotation.Mock;
import com.swagger.model.User;
import com.swagger.model.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TestController
 * @Author 袁伟倩
 * @Date 2018-6-413:47
 * @Version 1.0
 */
@Api(value = "API - TestController",description = "用户相关操作")
@RestController
public class TestController {
    /*
     * 在swagger-annotations jar包中 1.5.X版本以上, 注解 io.swagger.annotations.API
     * 中的description被废弃了。新的swagger组件中使用了新的方法来对Web api 进行分组。原来使用 description ，
     * 默认一个Controller类中包含的方法构成一 个api分组。现在使用tag，可以更加方便的分组。
     * 比如把两个Controller类里的方法划分成同一个分组。tag的key用来区分不同的分组。tag的value用做分组的描述。
     * @ApiOperation 中value是api的简要说明，在界面api 链接的右侧，少于120个字符。
     * @ApiOperation 中notes是api的详细说明，需要点开api 链接才能看到。
     * @ApiOperation 中 produces 用来标记api返回值的具体类型。这里是json格式，utf8编码。
     */
    @Mock
    @ApiOperation(value = "测试服务", notes = "测试服务")
    @ApiImplicitParam(name = "User", value = "用户详细实体User", required = true, dataType = "User")
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public String post(@RequestBody User user) {
        return "服务测试成功，你输入的参数为：" + user.toString();
    }

    @Mock
    @ApiOperation(value = "测试服务", notes = "测试服务", consumes = "application/json", produces = "application/json")
    @RequestMapping(value = "/test/{input}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("input") String input) {
        User user = new User();
        user.setId(Long.parseLong("111"));
        user.setName("molly");
        return user;
    }

    @Mock
    @ApiOperation(value = "测试服务", notes = "测试服务")
    @ApiImplicitParam(name = "User", value = "用户详细实体User", required = true, dataType = "User")
    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    @ResponseBody
    public OrderVo put(@RequestBody User user) {
        return null;
    }

    @Mock
    @ApiOperation(value = "测试服务", notes = "测试服务")
    @ApiImplicitParam(name = "User", value = "用户详细实体User", required = true, dataType = "User")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public User delete(@RequestBody User user) {
        return user;
    }
}