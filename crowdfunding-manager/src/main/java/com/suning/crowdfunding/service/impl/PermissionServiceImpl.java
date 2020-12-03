package com.suning.crowdfunding.service.impl;

import java.util.List;

import com.suning.crowdfunding.dao.PermissionDao;
import com.suning.crowdfunding.domain.Permission;
import com.suning.crowdfunding.domain.User;
import com.suning.crowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author yqx
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	public Permission queryRootPermission() {
		return permissionDao.queryRootPermission();
	}

	public List<Permission> queryChildPermissions(Integer pid) {
		return permissionDao.queryChildPermissions(pid);
	}

	public List<Permission> queryAll() {
		return permissionDao.queryAll();
	}

	public void insertPermission(Permission permission) {
		permissionDao.insertPermission(permission);
	}

	public Permission queryById(Integer id) {
		return permissionDao.queryById(id);
	}

	public void updatePermission(Permission permission) {
		permissionDao.updatePermission(permission);
	}

	public void deletePermission(Permission permission) {
		permissionDao.deletePermission(permission);
	}

	public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
		return permissionDao.queryPermissionidsByRoleid(roleid);
	}

	public List<Permission> queryPermissionsByUser(User dbUser) {
		return permissionDao.queryPermissionsByUser(dbUser);
	}
}
