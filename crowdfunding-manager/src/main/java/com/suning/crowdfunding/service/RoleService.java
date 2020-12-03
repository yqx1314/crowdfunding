package com.suning.crowdfunding.service;

import com.suning.crowdfunding.domain.Role;

import java.util.List;
import java.util.Map;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/12/1 19:51
 * @desc
 */
public interface RoleService {
    /**
     * 分页查询查找角色
     * @param map
     * @return
     */
    List<Role> pageQueryData(Map<String, Object> map);

    /**
     * 查询出所有的角色，返回一个值
     * @param map
     * @return
     */
    int pageQueryCount(Map<String, Object> map);

    /**
     * 删除角色信息
     * @param id
     */
    void deleteUserById(Integer id);

    /**
     * 批量删除角色信息
     * @param map
     */
    void deleteUsers(Map<String, Object> map);

    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> queryAll();

    /**
     * 增加角色许可
     * @param paramMap
     */
    void insertRolePermission(Map<String, Object> paramMap);
}
