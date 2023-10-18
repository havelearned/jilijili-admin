package top.jilijili.mall.currency.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.pojo.entity.currency.VirtualCurrency;
import top.jilijili.module.pojo.vo.currency.VirtualCurrencyVo;

/**
* @author admin
* @description 针对表【shop_virtual_currency】的数据库操作Mapper
* @createDate 2023-10-08 23:42:48
* @Entity top.jilijili.module.entity.VirtualCurrency
*/
@Mapper
public interface VirtualCurrencyMapper extends BaseMapper<VirtualCurrency> {


    Page<VirtualCurrencyVo> selectAll(Page<VirtualCurrencyVo> page,
                                      @Param(value = Constants.WRAPPER) QueryWrapper<VirtualCurrencyVo> qw);
}




