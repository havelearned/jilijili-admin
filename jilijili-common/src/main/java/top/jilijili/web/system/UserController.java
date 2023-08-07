package top.jilijili.web.system;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.ksuid.KsuidGenerator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jilijili.common.annotation.JilJilOperationLog;
import top.jilijili.common.core.controller.BaseController;
import top.jilijili.common.core.pojo.bo.UserConvertBo;
import top.jilijili.common.core.pojo.dto.UserCreateDto;
import top.jilijili.common.core.pojo.dto.UserDto;
import top.jilijili.common.core.pojo.dto.UserQueryDto;
import top.jilijili.common.core.pojo.entity.User;
import top.jilijili.common.core.pojo.request.UserUpdateRequest;
import top.jilijili.common.core.pojo.vo.Result;
import top.jilijili.common.core.pojo.vo.UserVo;
import top.jilijili.common.core.service.UserService;
import top.jilijili.common.enums.OperationType;
import top.jilijili.common.utils.IpUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static top.jilijili.common.constant.ModuleNameConstant.USER_MANAGEMENT;
import static top.jilijili.common.constant.RoleConstant.ROLE_SUPER_ADMIN;

/**
 * 用户管理
 *
 * @author admin
 * @Date: 2023/1/24 11:19
 * @Description: 用户管理
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Tag(name = "用户管理")
public class UserController extends BaseController<User, UserService> {

    private final UserService userService;
    private final UserConvertBo userConvertBo;

    public UserController(UserService userService, UserConvertBo userConvertBo) {
        this.userService = userService;
        this.userConvertBo = userConvertBo;
    }

    /**
     * 通过用户名查询
     *
     * @param username username
     * @return wang.jilijili.music.pojo.vo.Result<?>
     * @author Amani
     * @date 2023/3/10 11:56
     */
    @GetMapping("/checkUsername/{username}")
    public Result<?> checkUsername(@PathVariable("username") String username) {
        long count = this.userService.count(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        return count <= 0 ? Result.ok() : Result.fail();

    }

    /**
     * 分页查询
     *
     * @param userQueryDto 搜索条件
     * @return wang.jilijili.music.pojo.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage < wang.jilijili.music.pojo.vo.UserVo>>
     * @author Amani
     * @date 2023/3/5 11:45
     */
    @GetMapping("/list")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    public Result<IPage<UserVo>> search(UserQueryDto userQueryDto) {
        IPage<UserVo> pageEntity = new Page<>(userQueryDto.getPage(), userQueryDto.getSize());
        IPage<UserVo> voIpage = this.userService.search(pageEntity, userQueryDto);
        return Result.ok(voIpage);
    }

    /**
     * 查询用户
     *
     * @param id id
     * @return wang.jilijili.music.pojo.vo.Result<wang.jilijili.music.pojo.vo.UserVo>
     * @author Amani
     * @date 2023/3/10 11:55
     */
    @GetMapping("/{id}")
    public Result<UserVo> get(@PathVariable("id") String id) {
        UserVo userVo = this.userConvertBo.toVo(this.userService.get(id));
        return Result.ok(userVo);

    }

    /**
     * 修改用户
     *
     * @param userUpdateRequest 修改表单
     * @return wang.jilijili.music.pojo.vo.Result<wang.jilijili.music.pojo.vo.UserVo>
     * @author Amani
     * @date 2023/3/5 11:46
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.UPDATE)
    @PutMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    public Result<UserVo> update(@Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        UserVo userVo = this.userConvertBo.toVo(this.userService.update(userUpdateRequest));
        return Result.ok(userVo);
    }

    /**
     * 删除用户
     *
     * @param ids 用户id
     * @return wang.jilijili.music.pojo.vo.Result<?>
     * @author Amani
     * @date 2023/3/5 11:46
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.DELETED)
    @DeleteMapping("/{ids}")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    public Result<Boolean> delete(@PathVariable("ids") List<String> ids) {
        return Result.ok(this.userService.removeByIds(ids));
    }

    /**
     * 创建用户
     *
     * @param userCreateDto dto
     * @param request       当前请求
     * @return wang.jilijili.music.pojo.vo.Result<wang.jilijili.music.pojo.vo.UserVo>
     * @author Amani
     * @date 2023/3/5 11:47
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.ADD)
    @PostMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    public Result<UserVo> create(@Validated @RequestBody UserCreateDto userCreateDto,
                                 HttpServletRequest request) {
        UserDto userDto = this.userService.create(userCreateDto, request);
        UserVo userVo = this.userConvertBo.toVo(userDto);
        return Result.ok(userVo);
    }

    /**
     * 查询在线用户
     *
     * @param userQueryDto 查询条件
     * @return wang.jilijili.music.pojo.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage < wang.jilijili.music.pojo.vo.UserVo>>
     * @author Amani
     * @date 2023/3/10 11:56
     */
    @GetMapping("/getOnlineUsers")
    public Result<IPage<UserVo>> getOnlineUsers(
            @RequestBody UserQueryDto userQueryDto) {
        IPage<UserVo> result = userService.getOnlineUsers(userQueryDto.getPage(), userQueryDto.getSize(), userQueryDto);
        return Result.ok(result);
    }

    /**
     * 查询当前用户
     *
     * @return wang.jilijili.music.pojo.vo.Result<wang.jilijili.music.pojo.vo.UserVo>
     * @author Amani
     * @date 2023/3/10 11:57
     */
    @GetMapping("/me")
    public Result<UserVo> me() {
        UserDto userDto = this.userService.currentUser();
        return Result.ok(userConvertBo.toVo(userDto));
    }


    /**
     * 导入
     *
     * @param file     导出条件
     * @param response 响应
     * @author Amani
     * @date 2023/3/10 11:57
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.EXPORT)
    @PostMapping("/import")
    public Result<String> importUser(@RequestParam(name = "file") MultipartFile file,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        try {
            if (file.isEmpty()) {
                Result.fail("导入失败,文件异常!!!");
            }
            ImportParams params = new ImportParams();
            params.setHeadRows(0);
            params.setTitleRows(0);
            InputStream inputStream = file.getInputStream();
            List<User> userList = ExcelImportUtil.importExcel(inputStream, User.class, params);
            PasswordEncoder passwordEncoder = SpringUtil.getBean("passwordEncoder");
            List<User> list = userList.stream().map(item -> {
                item.setId(KsuidGenerator.generate());
                item.setPassword(passwordEncoder.encode(item.getPassword()));
                item.setLastLoginIp(IpUtils.getIpAddress(request));
                return item;
            }).toList();
            this.userService.saveBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    /**
     * 导出
     *
     * @param ids      导出条件
     * @param response 响应
     * @author Amani
     * @date 2023/3/10 11:57
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.EXPORT)
    @GetMapping("/export/{ids}")
    public void export(@PathVariable(value = "ids") List<String> ids,
                       HttpServletResponse response) {
        List<User> userList = this.userService.listByIds(ids);
        if (userList.isEmpty()) {
            userList = this.userService.list();
        }
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String filename = "用户报表";
        ExportParams params = new ExportParams("用户报表", "导出人:" + username, filename);
        params.setAutoSize(true);
        params.setType(ExcelType.HSSF);

        Workbook wb = ExcelExportUtil.exportExcel(params, User.class, userList);
        OutputStream os = null;
        try {
            super.setResponseHeader(response, filename);
            os = response.getOutputStream();
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert os != null;
                os.flush();
                os.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过userId获取菜单
     *
     * @param userId
     * @return 菜单列表
     */
    @GetMapping("/menu/{userId}")
    public Result<List<Tree<String>>> getMenu(@PathVariable String userId) {
        List<Tree<String>> list = this.userService.getMenu(userId);
        return Result.ok(list);
    }


}
