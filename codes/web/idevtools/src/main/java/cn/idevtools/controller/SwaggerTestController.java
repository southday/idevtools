package cn.idevtools.controller;

import cn.idevtools.po.UserTagVO;
import cn.idevtools.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author southday
 * Date   2019/2/25
 */
@RestController
@RequestMapping("/swaggerUser")
public class SwaggerTestController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户详细信息", notes = "根据userId获取用户详细信息（包括用户标签）")
    @ApiParam(name = "userId", value = "用户ID", required = true, type = "Integer")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserTagVO getUser(@PathVariable Integer userId) {
        return userService.getUserDetailWithTagById(userId);
    }
}
