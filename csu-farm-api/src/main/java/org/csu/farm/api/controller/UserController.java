

package org.csu.farm.api.controller;

import cn.hutool.extra.emoji.EmojiUtil;
import org.csu.farm.bean.app.dto.UserDto;
import org.csu.farm.bean.app.param.UserInfoParam;
import org.csu.farm.bean.model.User;
import org.csu.farm.security.api.util.SecurityUtils;
import org.csu.farm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/p/user")
@Api(tags="用户接口")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	private final MapperFacade mapperFacade;
	/**
	 * 查看用户接口
	 */
	@GetMapping("/userInfo")
	@ApiOperation(value="查看用户信息", notes="根据用户ID（userId）获取用户信息")
	public ResponseEntity<UserDto> userInfo() {
		String userId = SecurityUtils.getUser().getUserId();
		User user = userService.getById(userId);
		UserDto userDto = mapperFacade.map(user, UserDto.class);
		return ResponseEntity.ok(userDto);
	}

	@PutMapping("/setUserInfo")
	@ApiOperation(value="设置用户信息", notes="设置用户信息")
	public ResponseEntity<Void> setUserInfo(@RequestBody UserInfoParam userInfoParam) {
		String userId = SecurityUtils.getUser().getUserId();
		User user = new User();
		user.setUserId(userId);
		user.setPic(userInfoParam.getAvatarUrl());
		user.setNickName(EmojiUtil.toAlias(userInfoParam.getNickName()));
		userService.updateById(user);
		return ResponseEntity.ok().build();
	}
}
