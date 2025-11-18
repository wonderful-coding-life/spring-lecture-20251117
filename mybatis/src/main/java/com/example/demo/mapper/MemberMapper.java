package com.example.demo.mapper;

import com.example.demo.model.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Results(id="memberResult", value={
            @Result(column = "name", property = "name"),
            @Result(column = "email", property = "email"),
            @Result(column = "age", property = "age")
    })
    @Select("SELECT * FROM member")
    List<Member> selectAll();

    @ResultMap("memberResult")
    @Select("SELECT * FROM member ORDER BY ${order} ${direction}")
    List<Member> selectAllOrderBy(@Param("order") String order, @Param("direction") String direction);

    @ResultMap("memberResult")
    @Select("SELECT * FROM member WHERE id=#{id}")
    Member selectById(@Param("id") Long id);

    @ResultMap("memberResult")
    @Select("SELECT * FROM member WHERE email=#{email}")
    Member selectByEmail(@Param("email") String email);

    @Insert("INSERT INTO member(name, email, age) VALUES(#{member.name}, #{member.email}, #{member.age})")
    @Options(useGeneratedKeys=true, keyColumn="id", keyProperty="member.id")
    int insert(@Param("member") Member member);

    @Update("UPDATE member SET name=#{member.name}, email=#{member.email}, age=#{member.age} WHERE id=#{id}")
    int updateById(@Param("id") Long id, @Param("member") Member member);

    @Delete("DELETE FROM member WHERE id=#{id}")
    int deleteById(@Param("id") Long id);
}
