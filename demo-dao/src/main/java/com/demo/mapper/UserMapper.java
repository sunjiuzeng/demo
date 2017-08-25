package com.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demo.entity.UserEntity;
import org.elasticsearch.common.inject.Inject;

public interface UserMapper {
	String BASE_SQL = "name";

	String TABLE_NAME = "t_user";

	@Select({ 
		"select", 
		BASE_SQL, 
		"from", 
		TABLE_NAME, 
		"where id = #{id} limit 1" 
	})
	UserEntity findEntityById(@Param("id") int id);
}
