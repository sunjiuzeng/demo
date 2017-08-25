package com.demo.mapper;

import org.apache.ibatis.annotations.*;
import com.demo.entity.UserEntity;

import java.util.List;


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

	/*
	* @update
	* */
	@Update({
			"update",
			TABLE_NAME,
			"set name ='huige'  where id = #{id}"
	})
	int update(@Param("id") int id);

	/**
	 * @insert
	 * */

	@Insert({
			"insert into",
			TABLE_NAME,
			"(id,name) values (4,'jeep')"
	})
	int save(@Param("id") int id);


	/**
	 * @delete
	 * */
	@Delete({
			"delete",
			"from",
			TABLE_NAME,
			"where id =#{id}"
	})
	boolean delete(@Param("id") int id);

	/**
	 * @select
	 * */
	@Select({
			"select *",
			"from",
			TABLE_NAME,
	})
	List<UserEntity> findAll();
}
