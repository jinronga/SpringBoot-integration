package cn.jinronga.redis.dao;

import cn.jinronga.redis.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author jinronga
 * @email sunlightcs@gmail.com
 * @date 2021-12-05 13:59:00
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
