package com.suning.crowdfunding.service.impl;

import com.suning.crowdfunding.dao.RoleDao;
import com.suning.crowdfunding.domain.Role;
import com.suning.crowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/12/1 19:52
 * @desc
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public List<Role> pageQueryData(Map<String, Object> map) {
        return roleDao.pageQueryData(map);
    }

    public int pageQueryCount(Map<String, Object> map) {
        return roleDao.pageQueryCount(map);
    }

    public void deleteUserById(Integer id) {
        roleDao.deleteUserById(id);
    }

    public void deleteUsers(Map<String, Object> map) {
        roleDao.deleteUsers(map);
    }

    public List<Role> queryAll() {
        return roleDao.queryAll();
    }

    public void insertRolePermission(Map<String, Object> paramMap) {
        roleDao.deleteRolePermissions(paramMap);
        roleDao.insertRolePermission(paramMap);
    }
}
