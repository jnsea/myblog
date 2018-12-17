/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gosuncn.myartifact.api.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gosuncn.myartifact.api.dao.ApiUserDao;
import com.gosuncn.myartifact.api.entity.ApiTokenEntity;
import com.gosuncn.myartifact.api.entity.ApiUserEntity;
import com.gosuncn.myartifact.api.form.ApiLoginForm;
import com.gosuncn.myartifact.api.service.ApiTokenService;
import com.gosuncn.myartifact.api.service.ApiUserService;
import com.gosuncn.myartifact.common.utils.exception.MyServiceException;
import com.gosuncn.myartifact.common.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class ApiUserServiceImpl extends ServiceImpl<ApiUserDao, ApiUserEntity> implements ApiUserService {
	@Autowired
	private ApiTokenService tokenService;

	@Override
	public ApiUserEntity queryByMobile(String mobile) {
		ApiUserEntity userEntity = new ApiUserEntity();
		userEntity.setMobile(mobile);
		return baseMapper.selectOne(userEntity);
	}

	@Override
	public Map<String, Object> login(ApiLoginForm form) {
		ApiUserEntity user = queryByMobile(form.getMobile());
		Assert.isNull(user, "手机号或密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
			throw new MyServiceException("手机号或密码错误");
		}

		//获取登录token
		ApiTokenEntity tokenEntity = tokenService.createToken(user.getSysid());

		Map<String, Object> map = new HashMap<>(2);
		map.put("token", tokenEntity.getToken());
		map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());

		return map;
	}

}
