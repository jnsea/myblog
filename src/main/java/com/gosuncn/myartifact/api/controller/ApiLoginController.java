package com.gosuncn.myartifact.api.controller;


import com.gosuncn.myartifact.api.annotation.ApiLogin;
import com.gosuncn.myartifact.api.form.ApiLoginForm;
import com.gosuncn.myartifact.api.service.ApiTokenService;
import com.gosuncn.myartifact.api.service.ApiUserService;
import com.gosuncn.myartifact.common.utils.ResponseMap;
import com.gosuncn.myartifact.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 登录接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api")
@Api(tags="登录接口")
public class ApiLoginController {
    @Autowired
    private ApiUserService userService;
    @Autowired
    private ApiTokenService tokenService;


    @PostMapping("login")
    @ApiOperation("登录")
    public ResponseMap login(@RequestBody ApiLoginForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        //用户登录
        Map<String, Object> map = userService.login(form);

        return ResponseMap.ok(map);
    }

    @ApiLogin
    @PostMapping("logout")
    @ApiOperation("退出")
    public ResponseMap logout(@ApiIgnore @RequestAttribute("userId") long userId){
        tokenService.expireToken(userId);
        return ResponseMap.ok();
    }

}
