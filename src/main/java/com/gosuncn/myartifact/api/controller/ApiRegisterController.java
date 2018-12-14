package com.gosuncn.myartifact.api.controller;


import com.gosuncn.myartifact.api.entity.ApiUserEntity;
import com.gosuncn.myartifact.api.form.ApiRegisterForm;
import com.gosuncn.myartifact.api.service.ApiUserService;
import com.gosuncn.myartifact.common.utils.ResponseMap;
import com.gosuncn.myartifact.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 注册接口
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 17:27
 */
@RestController
@RequestMapping("/api")
@Api(tags="注册接口")
public class ApiRegisterController {
    @Autowired
    private ApiUserService userService;

    @PostMapping("register")
    @ApiOperation("注册")
    public ResponseMap register(@RequestBody ApiRegisterForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        ApiUserEntity user = new ApiUserEntity();
        user.setMobile(form.getMobile());
        user.setUsername(form.getMobile());
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setCreatedtime(new Date());
        userService.insert(user);

        return ResponseMap.ok();
    }
}
