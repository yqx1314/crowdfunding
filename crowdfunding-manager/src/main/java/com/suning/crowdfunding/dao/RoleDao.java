package com.suning.crowdfunding.dao;

import com.suning.crowdfunding.domain.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/12/1 19:53
 * @desc
 */
public interface RoleDao {

    /**
     * 分页查找角色
     * @param map
     * @return
     */
    @Select({"<script>",
            "select * from t_role",
            "<where>",
            "<if test='queryText!=null'>",
            "and rolename like concat('%',#{queryText},'%')",
            "</if>",
            "</where>",
            "limit #{start},#{size}",
            "</script>"})
    List<Role> pageQueryData(Map<String, Object> map);

    /**
     * 查询所有角色
     * @param map
     * @return
     */
    @Select({"<script>",
            "select count(*) from t_role",
            "<where>",
            "<if test='queryText!=null'>",
            "and rolename like concat('%',#{queryText},'%')",
            "</if>",
            "</where>",
            "</script>"})
    int pageQueryCount(Map<String, Object> map);

    /**
     * 根据角色id删除角色
     * @param id
     */
    @Delete("delete from t_role where id = #{id}")
    void deleteUserById(Integer id);

    /**
     * 批量删除角色信息
     * @param map
     */
    @Delete({
            "<script>",
            "delete from t_role where id in",
            "<foreach collection='roleids' item='roleid' open='(' close=')' separator=','>",
            "#{roleid}",
            "</foreach>",
            "</script>"
    })
    void deleteUsers(Map<String, Object> map);

    /**
     * 查询所有角色信息
     * @return List<Role>
     */
    @Select("select * from t_role")
    List<Role> queryAll();

    /**
     * 增加角色许可
     * @param paramMap
     */
    @Insert({
            "<script>",
            "insert into t_role_permission (roleid, permissionid) values",
            "<foreach collection='permissionids' item='permissionid' separator=','>",
            "( #{roleid}, #{permissionid} )",
            "</foreach>",
            "</script>"
    })
    void insertRolePermission(Map<String, Object> paramMap);

    /**
     * 删除已有的权限
     * @param paramMap
     */
    @Delete("delete from t_role_permission where roleid = #{roleid}")
    void deleteRolePermissions(Map<String, Object> paramMap);
}
