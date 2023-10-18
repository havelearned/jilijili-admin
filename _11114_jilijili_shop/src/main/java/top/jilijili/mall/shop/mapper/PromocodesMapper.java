package top.jilijili.mall.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.pojo.entity.shop.Promocodes;
import top.jilijili.module.pojo.vo.shop.PromocodesVo;

/**
 * @author admin
 * @description 针对表【shop_promocodes】的数据库操作Mapper
 * @createDate 2023-10-06 23:14:17
 * @Entity top.jilijili.module.entity.Promocodes
 */
@Mapper
public interface PromocodesMapper extends BaseMapper<Promocodes> {

    IPage<PromocodesVo> selectAllPromocodes(IPage<PromocodesVo> page, @Param(Constants.WRAPPER) QueryWrapper<PromocodesVo> qw);
}




