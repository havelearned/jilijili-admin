package top.jilijili.system.controller.sys;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.group.Insert;
import top.jilijili.module.entity.SysMenu;
import top.jilijili.module.entity.SysRole;
import top.jilijili.module.entity.SysUser;
import top.jilijili.module.entity.dto.SysRoleDto;
import top.jilijili.module.entity.dto.SysUserDto;
import top.jilijili.module.entity.vo.Result;
import top.jilijili.module.entity.vo.SysRoleVo;
import top.jilijili.module.entity.vo.SysUserVo;
import top.jilijili.system.common.aspect.annotation.RestrictAccess;
import top.jilijili.system.common.utils.KeyConstants;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.LoginStrategyContext;
import top.jilijili.system.service.SysRoleService;
import top.jilijili.system.service.SysUserService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static top.jilijili.system.common.enums.LoginOptionEnum.QQ;
import static top.jilijili.system.common.enums.LoginOptionEnum.WX;


/**
 * 用户表控制层
 *
 * @author makejava
 * @since 2023-06-22 01:12:24
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@AllArgsConstructor
public class SysUserController {

    private SysUserService sysUserService;
    private LoginStrategyContext loginStrategyContext;
    private ConvertMapper convertMapper;
    private RedisTemplate<String, Object> redisTemplate;
    private SysRoleService sysRoleService;


    /**
     * 获取验证码
     *
     * @param timestamp
     * @return
     */
    @GetMapping("/captcha")
    public Result<String> getCaptcha(@RequestParam(value = "timestamp") Long timestamp) {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 50, 4, 4);
        String randCaptcha = captcha.getCode();
        long timeout = 60 * 5;
        redisTemplate.opsForValue().set(KeyConstants.CAPTCHA_KEY + timestamp, randCaptcha, timeout, TimeUnit.SECONDS);
        log.info("获取验证码=>{},key=>{},过期时间:{}", randCaptcha, KeyConstants.CAPTCHA_KEY + timestamp, timeout);
        return Result.ok(captcha.getImageBase64());
    }

    /**
     * 根据用户id获取用户菜单
     *
     * @param userId
     * @return
     */
    @GetMapping("/menulist")
    public Result<?> getMenuList(@RequestParam(value = "userId") Long userId) {
        List<SysMenu> menuList = sysUserService.getMenuList(userId);

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("orderNum");            //权重排序字段 默认为weight
        treeNodeConfig.setIdKey("menuId");                  //主键 默认为id
        treeNodeConfig.setNameKey("menuName");              //名称 默认为name
        treeNodeConfig.setParentIdKey("parentId");      //父节点 默认为parentId
//        treeNodeConfig.setChildrenKey("children");    //子点 默认为children
        treeNodeConfig.setDeep(3);                      //最大递归深度 默认此配置为空,不限制(从0开始)
        List<Tree<String>> treeNodes = TreeUtil.build(menuList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    Map<String, Object> map = BeanUtil.beanToMap(treeNode, new HashMap<>(), false, false);
                    map.remove("menuId");
                    map.remove("menuName");
                    map.remove("orderNum");
                    map.remove("parentId");

                    tree.setId(treeNode.getMenuId().toString());
                    tree.setParentId(treeNode.getParentId().toString());
                    tree.setWeight(treeNode.getOrderNum());
                    tree.setName(treeNode.getMenuName());
                    tree.setChildren(null);
                    tree.putAll(map);
                });


        return Result.ok(treeNodes);
    }


    /**
     * 获取当前用户
     *
     * @return
     */
    @GetMapping("/me")
    public Result<SysUserVo> getUserinfo(@RequestParam(value = "token", defaultValue = "") String token) {
        String loginId = StpUtil.getLoginIdByToken(token).toString();
        return Result.ok(this.convertMapper.toSysUserVo(this.sysUserService.getById(loginId)));
    }

    /**
     * 登录
     *
     * @param sysUserDto
     * @return
     */
    @RestrictAccess
    @PostMapping("/login")
    public Result<String> userLogin(@RequestBody @Validated(value = {Insert.class}) SysUserDto sysUserDto) {
//        Long timestamp = sysUserDto.getTimestamp();
//        String key = KeyConstants.CAPTCHA_KEY + timestamp;
//        if (!this.redisTemplate.hasKey(key)) {
//            return Result.fail("验证码错误或者已过期");
//        } else {
//            Object code = this.redisTemplate.opsForValue().getAndDelete(key);
//            if (!sysUserDto.getCaptcha().equals(code)) {
//                return Result.fail("验证码错误或者已过期");
//            }
//        }
        switch (sysUserDto.getLoginType()) {
            case QQ -> {
                return loginStrategyContext.executeLogin(sysUserDto.getUsername(), sysUserDto.getPassword(), QQ);
            }
            case WX -> {
                return loginStrategyContext.executeLogin(sysUserDto.getUsername(), sysUserDto.getPassword(), WX);
            }
            case GITEE -> {
                return Result.ok("GITEE-登录");
            }
            case GITHUB -> {
                return Result.ok("GITHUB-登录");
            }
            case DEFAULT -> {
                return this.sysUserService.login(sysUserDto);
            }
            default -> {
                return null;
//                return Result.ok("jili系统登录");
            }
        }
    }

    /**
     * 获取用户表列表(分页)
     *
     * @param sysUserDto
     * @return
     */
    @GetMapping("/list")
    public Result<IPage<SysUserVo>> getList(SysUserDto sysUserDto) {
        IPage<SysUser> page = new Page<>(sysUserDto.getPage(), sysUserDto.getSize());
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>(convertMapper.toSysUserEnetity(sysUserDto));
        page = this.sysUserService.page(page, queryWrapper);

        return Result.ok(page.convert(item -> {
            SysUserVo sysUserVo = convertMapper.toSysUserVo(item);
            return sysUserVo;
        }));

    }

    /**
     * 通过用户id获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public SysUserVo getUserInformation(@RequestParam Serializable id) {
        return convertMapper.toSysUserVo(sysUserService.getById(id));
    }

    /**
     * 通过用户id获取用户角色信息
     *
     * @param id
     * @return
     */
    @GetMapping("/get/role")
    public Result<List<SysRoleVo>> getUserRoleInfo(@RequestParam Serializable id) {
        List<SysRoleVo> sysUserVo = this.sysUserService.getUserRoleInformation(id);
        return Result.ok(sysUserVo);
    }

    /**
     * 添加用户表
     *
     * @param sysUserDto
     * @param request
     * @return
     */
    @PostMapping
    public Result<SysUserVo> addUser(@RequestBody SysUserDto sysUserDto, HttpServletRequest request) {
        return Result.ok(sysUserService.addSysUser(sysUserDto, request));
    }


    /**
     * 修改用户信息
     *
     * @param sysUserDto
     */
    @PutMapping
    public void updateUser(@RequestBody SysUserDto sysUserDto) {
        SysUser sysUser = this.convertMapper.toSysUserEnetity(sysUserDto);
        sysUser.setUserType(sysUserDto.getLoginType().getValue());
        sysUserService.updateById(sysUser);
    }

    /**
     * 修改用戶的角色信息
     *
     * @param ownedItems
     * @param userId
     * @return
     */
    @PutMapping("/role/update/{userId}")
    public Result<Boolean> updateUserRole(@PathVariable Long userId,
                                          @RequestBody List<SysRoleDto> ownedItems) {
        Boolean result = this.sysUserService.updateUserRole(userId, ownedItems);
        if (result) {
            return Result.ok(null, "操作成功");
        }
        return Result.fail("操作失败");
    }


    /**
     * 删除用户表
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<Boolean> deleteUser(@RequestBody List<Long> ids) {
        return Result.ok(sysUserService.removeBatchByIds(ids));
    }


    /*---------------------------角色-----------------------------------*/

    /**
     * 分页查询角色信息
     *
     * @param sysRoleDto 查询条件
     * @return 列表
     */
    @GetMapping("/role/list")
    public Result<IPage<SysRoleVo>> getListRole(SysRoleDto sysRoleDto) {
        Page<SysRole> sysRolePage = new Page<>(sysRoleDto.getPage(), sysRoleDto.getSize());
        SysRole sysRole = this.convertMapper.toSysRoleEnetity(sysRoleDto);
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(sysRole.getRoleName()), SysRole::getRoleName, sysRole.getRoleName())
                .like(StringUtils.hasText(sysRole.getTitle()), SysRole::getTitle, sysRole.getTitle())
                .orderBy(true, false, SysRole::getCreatedTime);
        return Result.ok(this.sysRoleService.page(sysRolePage, queryWrapper).convert(this.convertMapper::toSysRoleVo));
    }

    /**
     * 通过用户id查询角色信息
     *
     * @param id 角色id
     * @return 角色信息
     */
    @GetMapping("/role/get")
    public Result<SysRoleVo> getRoleInformation(@RequestParam Serializable id) {
        return Result.ok(
                this.convertMapper.toSysRoleVo(this.sysRoleService.getById(id))
        );
    }

    /**
     * 添加角色
     *
     * @param sysRoleDto 角色属性
     * @return 是否成功
     */
    @PostMapping("/role")
    public Result<Boolean> insert(@RequestBody SysRoleDto sysRoleDto) {
        SysRole sysRole = this.convertMapper.toSysRoleEnetity(sysRoleDto);
        return Result.ok(this.sysRoleService.save(sysRole));
    }

    /**
     * 通过角色id修改角色信息
     *
     * @param sysRoleDto 要修改的角色信息
     * @return 是否成功
     */
    @PutMapping("/role")
    public Result<Boolean> updatedRole(@RequestBody SysRoleDto sysRoleDto) {
        SysRole sysRole = this.convertMapper.toSysRoleEnetity(sysRoleDto);
        return Result.ok(this.sysRoleService.updateById(sysRole));
    }

    /**
     * 批量或者单独删除角色信息
     *
     * @param idList 角色id
     * @return 是否成功
     */
    @DeleteMapping("/role")
    public Result<Boolean> removeRoles(@RequestBody List<Long> idList) {
        return Result.ok(sysRoleService.removeRoleByRoleIds(idList));
    }
}
