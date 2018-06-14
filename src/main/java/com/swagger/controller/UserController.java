package com.swagger.controller;

import com.swagger.model.User;
import com.swagger.model.vo.OrderVo;
import com.swagger.model.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: UserController
 * @Author 袁伟倩
 * @Date 2018-6-413:47
 * @Version 1.0
 */
//,tags={"用户操作接口"}
@Api(value = "API - UserController")
@RestController
public class UserController {

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", consumes = "application/json", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="id",value="用户id",dataType="string", paramType = "query", required = true)})
    @RequestMapping(value = "/get/user", method = RequestMethod.GET)
    @ResponseBody
    public UserVo getUser(String id , String name) {
        UserVo user = new UserVo();
        user.setId(Long.parseLong(id));
        user.setName(name);
        return user;
    }

    @ApiOperation(value = "获取用户信息列表", notes = "获取用户信息列表", consumes = "application/json", produces = "application/json")
    @ApiImplicitParam(name="id",value="用户id",dataType="String", paramType = "query", required = true)
    @RequestMapping(value = "/get/userlist", method = RequestMethod.GET)
    @ResponseBody
    public List<UserVo> getUserList(String id) {
        UserVo user = new UserVo();
        user.setId(Long.parseLong(id));
        user.setName("molly");
        UserVo usera = new UserVo();
        usera.setId(Long.parseLong("222"));
        usera.setName("su");
        List<UserVo> userlist = new ArrayList<UserVo>();
        userlist.add(user);
        userlist.add(usera);
        return userlist;
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", consumes = "application/json", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="id",value="用户id",dataType="string", paramType = "query", required = true)})
    @RequestMapping(value = "/get/query", method = RequestMethod.GET)
    @ResponseBody
    public UserVo getUserQuery(String id , String name) {
        UserVo user = new UserVo();
        user.setId(Long.parseLong(id));
        user.setName(name);
        return user;
    }


}