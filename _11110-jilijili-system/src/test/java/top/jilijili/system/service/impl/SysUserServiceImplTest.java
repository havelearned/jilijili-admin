package top.jilijili.system.service.impl;


import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.system.entity.MusicAlbum;
import top.jilijili.system.entity.vo.MusicAlbumVo;
import top.jilijili.system.entity.vo.MusicSongVo;
import top.jilijili.system.entity.vo.SysMenuVo;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.mapper.MusicAlbumMapper;
import top.jilijili.system.mapper.MusicSongMapper;
import top.jilijili.system.mapper.SysMenuMapper;
import top.jilijili.system.service.MusicSongService;
import top.jilijili.system.service.SysMenuService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private MusicAlbumMapper musicAlbumMapper;

    @Autowired
    private ConvertMapper convertMapper;

    @Autowired
    private MusicSongMapper musicSongMapper;
    @Autowired
    private MusicSongService musicSongService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Test
    public void RoleBindingMenuTest(){

    }

    @Test
    public void TestMenu() {
        List<SysMenuVo> sysMenus = sysMenuMapper.queryRoleMenuListByRoleId(1);

        System.out.println("=================================================");
        System.out.println(JSONObject.toJSONString(sysMenus));
        System.out.println("=================================================");


    }

    @Test
    public void testMusicSong() {
        MusicSongVo vo = this.musicSongService.selectSongInfoBySongId(1683422002019729410L);
        System.out.println("=================================================");
        System.out.println(JSONObject.toJSONString(vo));
        System.out.println("=================================================");

    }

    @Test
    public void test() {
        IPage<MusicAlbum> page = new Page<>(1, 10);
        QueryWrapper<MusicAlbum> queryWrapper = new QueryWrapper<>();

        IPage<MusicAlbumVo> iPage = musicAlbumMapper.queryAlbumAndSingerPage(page, queryWrapper);

        String string = JSONObject.toJSONString(iPage);
        System.out.println(string);


    }


    public static void main(String[] args) {
        List<Integer> longList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 使用流(Stream)来处理List并自定义输出格式
        String result = longList.stream()
                .map(String::valueOf) // 将整数转换为字符串
                .collect(Collectors.joining(", ")); // 用逗号和空格连接所有元素

        System.out.println(result);
    }

}