package com.gosuncn.myartifact.api.controller;


import com.gosuncn.myartifact.api.annotation.ApiLogin;
import com.gosuncn.myartifact.api.annotation.ApiLoginUser;
import com.gosuncn.myartifact.api.entity.ApiUserEntity;
import com.gosuncn.myartifact.common.utils.ResponseMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 测试接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:47
 */
@RestController
@RequestMapping("/api")
@Api(tags="测试接口")
public class ApiTestController {

    @ApiLogin
    @GetMapping("userInfo")
    @ApiOperation(value="获取用户信息", response= ApiUserEntity.class)
    public ResponseMap userInfo(@ApiIgnore @ApiLoginUser ApiUserEntity user){
        return ResponseMap.ok().put("user", user);
    }

    @ApiLogin
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public ResponseMap userInfo(@ApiIgnore @RequestAttribute("userId") Integer userId){
        return ResponseMap.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public ResponseMap notToken(){
        return ResponseMap.ok().put("msg", "无需token也能访问。。。");
    }

}
