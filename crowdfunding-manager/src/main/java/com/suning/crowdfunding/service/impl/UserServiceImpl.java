package com.suning.crowdfunding.service.impl;

import com.suning.crowdfunding.dao.UserDao;
import com.suning.crowdfunding.domain.User;
import com.suning.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/30 13:21
 * @desc
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public List<User> queryAll() {
        return userDao.queryAll();
    }

    public User query4Login(User user) {
        return userDao.query4Login(user);
    }

    public List<User> pageQueryData(Map<String, Object> map) {
        return userDao.pageQueryData(map);
    }

    public int pageQueryCount(Map<String, Object> map) {
        return userDao.pageQueryCount(map);
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    public void deleteUsers(Map<String, Object> map) {
        userDao.deleteUsers(map);
    }

    /**
     * 给某个用户分配角色
     *
     * @param map
     */
    public void insertUserRoles(Map<String, Object> map) {
        userDao.insertUserRoles(map);
    }

    /**
     * 取消某个用户分配的角色
     *
     * @param map
     */
    public void deleteUserRoles(Map<String, Object> map) {
        userDao.deleteUserRoles(map);
    }

    public List<Integer> queryRoleidsByUserid(Integer id) {
        return userDao.queryRoleidsByUserid(id);
    }
}
