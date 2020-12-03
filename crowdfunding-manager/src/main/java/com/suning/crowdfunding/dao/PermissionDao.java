package com.suning.crowdfunding.dao;

import java.util.List;

import com.suning.crowdfunding.domain.Permission;
import com.suning.crowdfunding.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * @author yqx
 */
public interface PermissionDao {

	/**
	 * 查找root结点
	 * @return
	 */
	@Select("select * from t_permission where pid is null")
	Permission queryRootPermission();

	/**
	 * 通过父节点id查找其子节点
	 * @param pid
	 * @return
	 */
	@Select("select * from t_permission where pid = #{pid}")
	List<Permission> queryChildPermissions(Integer pid);

	/**
	 * 查询所有
	 * @return
	 */
	@Select("select * from t_permission")
	List<Permission> queryAll();

	/**
	 * 新增许可信息
	 * @param permission
	 */
	@Insert("insert into t_permission (name, url, pid) values (#{name}, #{url}, #{pid})")
	void insertPermission(Permission permission);

	/**
	 * 根据id查找许可信息
	 * @param id
	 * @return
	 */
	@Select("select * from t_permission where id = #{id}")
	Permission queryById(Integer id);

	/**
	 * 修改许可信息
	 * @param permission
	 */
	@Update("update t_permission set name = #{name}, url = #{url} where id = #{id}")
	void updatePermission(Permission permission);

	/**
	 * 删除许可信息
	 * @param permission
	 */
	@Delete("delete from t_permission where id = #{id}")
	void deletePermission(Permission permission);

	/**
	 * 根据角色id查询其许可
	 * @param roleid
	 * @return
	 */
	@Select("select permissionid from t_role_permission where roleid = #{roleid}")
	List<Integer> queryPermissionidsByRoleid(Integer roleid);

	/**
	 * 根据用户查询其许可
	 * @param dbUser
	 * @return
	 */
	@Select("select * " +
			"from t_permission " +
			"where id in " +
			"(select permissionid " +
			"from t_role_permission " +
			"where roleid in " +
			"( select roleid " +
			"from t_user_role " +
			"where userid = #{id}))")
	List<Permission> queryPermissionsByUser(User dbUser);

}
