package com.suning.crowdfunding.controller;

import com.suning.crowdfunding.domain.AJAXResult;
import com.suning.crowdfunding.domain.Page;
import com.suning.crowdfunding.domain.Role;
import com.suning.crowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/12/1 19:29
 * @desc
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    /**
     * 跳转到角色的首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "role/index";
    }

    /**
     * 分页查询
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageQuery")
    public Object pageQuery( String queryText, Integer pageno, Integer pagesize ) {

        AJAXResult result = new AJAXResult();

        try {

            // 分页查询
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("start", (pageno-1)*pagesize);
            map.put("size", pagesize);
            map.put("queryText", queryText);

            List<Role> roles = roleService.pageQueryData( map );
            // 当前页码
            // 总的数据条数
            int totalsize = roleService.pageQueryCount( map );
            // 最大页码（总页码）
            int totalno = 0;
            if ( totalsize % pagesize == 0 ) {
                totalno = totalsize / pagesize;
            } else {
                totalno = totalsize / pagesize + 1;
            }

            // 分页对象
            Page<Role> rolePage = new Page<Role>();
            rolePage.setDatas(roles);
            rolePage.setTotalno(totalno);
            rolePage.setTotalsize(totalsize);
            rolePage.setPageno(pageno);

            result.setData(rolePage);
            result.setSuccess(true);
        } catch ( Exception e ) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;

    }
    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete( Integer id ) {
        AJAXResult result = new AJAXResult();

        try {

            roleService.deleteUserById(id);
            result.setSuccess(true);
        } catch ( Exception e ) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }
    /**
     * 批量删除角色信息
     * @param roleid
     * @return
     */
    @ResponseBody
    @RequestMapping("/deletes")
    public Object deletes( Integer[] roleid ) {

        AJAXResult result = new AJAXResult();
        try {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleids", roleid);
            roleService.deleteUsers(map);
            result.setSuccess(true);
        } catch ( Exception e ) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 分配权限
     * @param roleid
     * @param permissionids
     * @return
     */
    @ResponseBody
    @RequestMapping("/doAssign")
    public Object doAssign( Integer roleid, Integer[] permissionids ) {
        AJAXResult result = new AJAXResult();

        try {

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("roleid", roleid);
            paramMap.put("permissionids", permissionids);
            roleService.insertRolePermission(paramMap);

            result.setSuccess(true);
        } catch ( Exception e ) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 跳转到分配权限页面
     * @return
     */
    @RequestMapping("/assign")
    public String assign() {
        return "role/assign";
    }
}
