package com.suning.crowdfunding.controller;

import com.suning.crowdfunding.domain.AJAXResult;
import com.suning.crowdfunding.domain.Permission;
import com.suning.crowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/12/2 13:24
 * @desc
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    /**
     * 跳转到权限控制的首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "permission/index";
    }
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
        List<Permission> permissions = new ArrayList<Permission>();
        List<Permission> ps = permissionService.queryAll();
        Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
        for (Permission p : ps) {
            permissionMap.put(p.getId(), p);
        }
        for ( Permission p : ps ) {
            Permission child = p;
            if ( child.getPid() == 0 ) {
                permissions.add(p);
            } else {
                Permission parent = permissionMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }

        return permissions;
    }

    /**
     * 执行新增
     * @param permission
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert( Permission permission ) {
        AJAXResult result = new AJAXResult();

        try {
            permissionService.insertPermission(permission);
            result.setSuccess(true);
        } catch ( Exception e ) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return "permission/add";
    }

    /**
     * 跳转到修改许可信息页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit( Integer id, Model model ) {

        Permission permission = permissionService.queryById(id);
        model.addAttribute("permission", permission);

        return "permission/edit";
    }

    /**
     * 修改许可信息
     * @param permission
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update( Permission permission ) {
        AJAXResult result = new AJAXResult();

        try {
            permissionService.updatePermission(permission);
            result.setSuccess(true);
        } catch ( Exception e ) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 删除许可信息
     * @param permission
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete( Permission permission ) {
        AJAXResult result = new AJAXResult();

        try {
            permissionService.deletePermission(permission);
            result.setSuccess(true);
        } catch ( Exception e ) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 回显已分配的许可
     * @param roleid
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadAssignData")
    public Object loadAssignData( Integer roleid ) {
        List<Permission> permissions = new ArrayList<Permission>();
        List<Permission> ps = permissionService.queryAll();

        // 获取当前角色已经分配的许可信息
        List<Integer> permissionids = permissionService.queryPermissionidsByRoleid(roleid);

        Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
        for (Permission p : ps) {
            if ( permissionids.contains(p.getId()) ) {
                p.setChecked(true);
            } else {
                p.setChecked(false);
            }
            permissionMap.put(p.getId(), p);
        }
        for ( Permission p : ps ) {
            Permission child = p;
            if ( child.getPid() == 0 ) {
                permissions.add(p);
            } else {
                Permission parent = permissionMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }

        return permissions;
    }
}
