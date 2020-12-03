package com.suning.crowdfunding.service;

import com.suning.crowdfunding.domain.Permission;
import com.suning.crowdfunding.domain.User;

import java.util.List;



public interface PermissionService {

	/**
	 * 查找root结点
	 * @return
	 */
	Permission queryRootPermission();

	/**
	 * 通过父节点id查找其子节点
	 * @param pid
	 * @return
	 */
	List<Permission> queryChildPermissions(Integer pid);

	/**
	 * 查询所有
	 * @return
	 */
	List<Permission> queryAll();

	/**
	 * 新增许可信息
	 * @param permission
	 */
	void insertPermission(Permission permission);

	/**
	 * 根据id查找许可信息
	 * @param id
	 * @return
	 */
	Permission queryById(Integer id);

	/**
	 * 修改许可信息
	 * @param permission
	 */
	void updatePermission(Permission permission);

	/**
	 * 删除许可信息
	 * @param permission
	 */
	void deletePermission(Permission permission);

	/**
	 * 根据角色名查询许可
	 * @param roleid
	 * @return
	 */
	List<Integer> queryPermissionidsByRoleid(Integer roleid);

	/**
	 * 根据用户查询许可
	 * @param dbUser
	 * @return
	 */
	List<Permission> queryPermissionsByUser(User dbUser);

}
