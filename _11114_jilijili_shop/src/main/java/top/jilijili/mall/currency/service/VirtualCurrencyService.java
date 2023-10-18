package top.jilijili.mall.currency.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.pojo.dto.currency.VirtualCurrencyDto;
import top.jilijili.module.pojo.entity.currency.VirtualCurrency;
import top.jilijili.module.pojo.vo.currency.VirtualCurrencyVo;

import java.io.Serializable;

/**
* @author admin
* @description 针对表【shop_virtual_currency】的数据库操作Service
* @createDate 2023-10-08 23:42:48
*/
public interface VirtualCurrencyService extends IService<VirtualCurrency> {

    Page<VirtualCurrencyVo> selectAll(VirtualCurrencyDto dto);



    VirtualCurrencyVo selectOne(Serializable id);
}
