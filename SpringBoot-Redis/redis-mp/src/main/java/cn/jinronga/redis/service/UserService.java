package cn.jinronga.redis.service;

import cn.jinronga.redis.common.utils.PageUtils;
import cn.jinronga.redis.common.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.jinronga.redis.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jinronga
 * @email sunlightcs@gmail.com
 * @date 2021-12-05 13:59:00
 */
public interface UserService extends IService<UserEntity> {

    R queryPage(Map<String, Object> params);

    void insertUser(UserEntity user);

    UserEntity selectUserById(Long userId);
    UserEntity selectUserById1(Long userId);
    void deleteUserById(List<Long> userIds);

    void updateUserInfo(UserEntity user);

}

