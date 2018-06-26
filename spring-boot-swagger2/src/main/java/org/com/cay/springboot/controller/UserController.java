package org.com.cay.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.com.cay.springboot.entity.User;
import org.com.cay.springboot.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Cay on 2018/3/13.
 */

/**
 * Swagger注释API详细说明:
 * 协议描述	            @ApiOperation	            用在controller的方法上
			value api名称
			notes 备注说明
 * 对象                  @ApiModel                  用在实体对象上
 * 对象属性	            @ApiModelProperty	        用在出入参数对象的字段上
			value 参数名称
			required 是否必须 boolean
			hidden 是否隐藏 boolean

 * 协议集描述	            @Api	                    用于controller类上
 * Response集	        @ApiResponses	            用在controller的方法上
 * Response	            @ApiResponse	            用在 @ApiResponses里边
 * 非对象参数集	        @ApiImplicitParams	        用在controller的方法上
 * 非对象参数描述	        @ApiImplicitParam	        用在@ApiImplicitParams的方法里边
 *              name：对应方法中接收参数名
				value：接收参数的意义描述
				required：是否必须 boolean
				paramType：参数类型 body、path、query、header、form中的一种:
					body：使用@RequestBody接收数据 POST有效
					path：在url中配置{}的参数
					query：普通查询参数 例如 ?query=q ,jquery ajax中data设置的值也可以，例如 {query:”q”},springMVC中不需要添加注解接收
					header：使用@RequestHeader接收数据
                    form：表单提交，必须使用post提交
				dataType：数据类型，如果类型名称相同，请指定全路径，例如 dataType = “java.util.Date”，springfox会自动根据类型生成模型
                defaultValue：默认值
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户Controller")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	//@RequestMapping("/")
	@GetMapping("/")
	@ApiOperation(value = "获取User列表", notes = "获取所有User对象")
	public List<User> getUserList() {
		logger.info("获取User列表...");
		return userService.getUserList();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "获取用户详情", notes = "根据url中用户id来获取该用户的详情")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", paramType = "path")
	public User getById(@PathVariable Integer id) {
		logger.info("获取id为{}的User...", id);
		return userService.getById(id);
	}

	@PostMapping("/")
	@ApiOperation(value = "新增User", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User", paramType = "body")
	public String addUser(@RequestBody User user) {
		logger.info("新增User对象...");
		int result = userService.addUser(user);
		if (result == 1) {
			return "success";
		} else {
			return "error";
		}
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "更新指定user对象", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "需要更新的用户id", required = true, dataType = "Integer", paramType = "path"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User", paramType = "body")
	})
	public String updateUser(@PathVariable Integer id, @RequestBody User user) {
		logger.info("更新id为{}的User对象...", id);
		user.setUid(id);
		int result = userService.updateUser(id, user);
		if (result == 1) {
			return "success";
		} else {
			return "error";
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除指定user对象", notes = "根据url中的id来删除user对象")
	@ApiImplicitParam(name = "id", value = "需要删除user对象的id", required = true, dataType = "Integer", paramType = "path")
	public String deleteUser(@PathVariable Integer id) {
		logger.info("删除id为{}的User对象...", id);
		int result = userService.deleteUser(id);
		if (result == 1) {
			return "success";
		} else {
			return "error";
		}
	}
}
