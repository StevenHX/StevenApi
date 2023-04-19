package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.BasePageResult;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.entity.SysMenu;
import com.steven.demo01.domain.entity.User;
import com.steven.demo01.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "菜单相关接口")
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {
    @Autowired
    MenuService menuService;

    /**
     * 获取 菜单列表
     *
     * @param menu
     * @return
     */
    @PostMapping("/list")
    public CommonResult<BasePageResult<SysMenu>> list(@RequestBody SysMenu menu) {
        IPage<SysMenu> page = menuService.selectMenuList(menu);
        return CommonResult.success(BasePageResult.newInstance(page));
    }

    /**
     * 获取 菜单详情
     *
     * @param menuId
     * @return
     */
    @GetMapping(value = "/detail/{menuId}")
    public CommonResult<SysMenu> getInfo(@PathVariable Long menuId) {
        SysMenu sysMenu = menuService.selectMenuById(menuId);
        return CommonResult.success(sysMenu);
    }

    /**
     * 根据roleID获取菜单
     *
     * @param roleId
     * @return
     */
    @GetMapping(value = "/menusByRole/{roleId}")
    public CommonResult<List<SysMenu>> getMenusByRoleID(@PathVariable Long roleId) {
        List<SysMenu> sysMenu = menuService.selectMenuByRoleId(roleId);
        return CommonResult.success(sysMenu);
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/add")
    public CommonResult<String> add(@RequestBody SysMenu menu) {
        User loginUser = getCurrentLoginUser().getUser();
        menu.setCreateBy(loginUser.getUserName());
        menu.setCreateTime(new Date());
        menuService.addMenu(menu);
        return CommonResult.success("新增成功");
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/edit")
    public CommonResult<String> edit(@RequestBody SysMenu menu) {
        User loginUser = getCurrentLoginUser().getUser();
        menu.setUpdateBy(loginUser.getUserName());
        menu.setUpdateTime(new Date());
        menuService.editMenu(menu);
        return CommonResult.success("修改成功");
    }



    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    @DeleteMapping("/{menuId}")
    public CommonResult<String> remove(@PathVariable("menuId") Long menuId) {
        menuService.deleteMenu(menuId);
        return CommonResult.success("删除成功");
    }
}
