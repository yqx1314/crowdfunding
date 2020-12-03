package com.suning.crowdfunding.service;

import com.suning.crowdfunding.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/30 13:21
 * @desc
 */
public interface UserService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> queryAll();

    /**
     * 根据用户名h和密码查询用户信息
     * @param user
     * @return
     */
    User query4Login(User user);

    /**
     * 分页查询数据
     * @param map
     * @return
     */
    List<User> pageQueryData(Map<String, Object> map);

    /**
     * 查询用户的数量
     * @param map
     * @return
     */
    int pageQueryCount(Map<String, Object> map);

    /**
     * 新增用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User queryById(Integer id);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUserById(Integer id);

    /**
     * 批量删除用户信息
     * @param map
     */
    void deleteUsers(Map<String, Object> map);

    /**
     * 给某个用户分配角色
     * @param map
     */
    void insertUserRoles(Map<String, Object> map);

    /**
     * 取消某个用户分配的角色
     * @param map
     */
    void deleteUserRoles(Map<String, Object> map);

    /**
     * 根据用户id在t_role_user表中查找对应角色
     * @param id
     * @return
     */
    List<Integer> queryRoleidsByUserid(Integer id);
}
