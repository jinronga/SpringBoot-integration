package cn.jinronga.redis.controller;

import java.util.Arrays;
import java.util.Map;
import cn.jinronga.redis.common.utils.R;
import cn.jinronga.redis.model.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import cn.jinronga.redis.entity.UserEntity;
import cn.jinronga.redis.service.UserService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author jinronga
 * @email sunlightcs@gmail.com
 * @date 2021-12-05 13:59:00
 */
@Api(description = "用户User接口")
@RestController
@RequestMapping("redis/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @ApiOperation("列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        return R.ok().put("page", userService.queryPage(params));
    }



    @ApiOperation("信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        UserEntity user = userService.selectUserById(id);
        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    public R save(@RequestBody UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto,userEntity);
        userService.insertUser(userEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    public R update(@RequestBody UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto,userEntity);
        userService.updateUserInfo(userEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        userService.deleteUserById(Arrays.asList(ids));
        return R.ok();
    }

}
