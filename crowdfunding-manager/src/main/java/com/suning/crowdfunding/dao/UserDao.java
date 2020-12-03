package com.suning.crowdfunding.dao;

import com.suning.crowdfunding.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/30 13:23
 * @desc
 */

public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from t_user")
    List<User> queryAll();

    /**
     * 登录，根据用户名和密码查询用户
     * @param user
     * @return
     */
    @Select("select * from t_user where loginacct=#{loginacct} and userpswd=#{userpswd}")
    User query4Login(User user);

    /**
     * 分页查询用户
     * @param map
     * @return
     */
    @Select({"<script>",
            "select * from t_user",
            "<where>",
            "<if test='queryText!=null'>",
            "and loginacct like concat('%',#{queryText},'%')",
            "</if>",
            "</where>",
            "order by createtime desc",
            "limit #{start},#{size}",
            "</script>"})
    List<User> pageQueryData(Map<String, Object> map);

    /**
     * 查询用户数量
     * @param map
     * @return
     */
    @Select({"<script>",
            "select count(*) from t_user",
            "<where>",
            "<if test='queryText!=null'>",
            "and loginacct like concat('%',#{queryText},'%')",
            "</if>",
            "</where>",
            "</script>"})
    int pageQueryCount(Map<String, Object> map);

    /**
     * 新增用户
     * @param user
     */
    @Insert("insert into t_user (loginacct, username, userpswd, email, createtime) values (#{loginacct}, #{username}, #{userpswd}, #{email}, #{createtime})")
    void insertUser(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from t_user where id=#{id}")
    User queryById(Integer id);

    /**
     * 修改用户信息
     * @param user
     */
    @Update("update t_user set loginacct = #{loginacct}, username = #{username}, email = #{email} where id = #{id}")
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    @Delete("delete from t_user where id = #{id}")
    void deleteUserById(Integer id);

    /**
     * 批量删除用户信息
     * @param map
     */
    @Delete({
            "<script>",
            "delete from t_user where id in",
            "<foreach collection='userids' item='userid' open='(' close=')' separator=','>",
            "#{userid}",
            "</foreach>",
            "</script>"
    })
    void deleteUsers(Map<String, Object> map);

    /**
     * 给某个用户分配角色
     * @param map
     */
    @Insert({
            "<script>",
            "insert into t_user_role ( userid, roleid ) values ",
            "<foreach collection='roleids' item='roleid' separator=','>",
            "( #{userid}, #{roleid} )",
            "</foreach>",
            "</script>"
    })
    void insertUserRoles(Map<String, Object> map);

    /**
     * 取消某个用户分配的角色
     * @param map
     */
    @Delete({
            "<script>",
            "delete from t_user_role where userid = #{userid} and roleid in",
            "<foreach collection='roleids' item='roleid' open='(' close=')' separator=','>",
            "#{roleid}",
            "</foreach>",
            "</script>"
    })
    void deleteUserRoles(Map<String, Object> map);

    /**
     * 根据用户id在t_role_user表中查找对应角色
     * @param id
     * @return
     */
    @Select("select roleid from t_user_role where userid= #{id}")
    List<Integer> queryRoleidsByUserid(Integer id);
}
