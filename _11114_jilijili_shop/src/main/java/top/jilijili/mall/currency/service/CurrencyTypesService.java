package top.jilijili.mall.currency.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.jilijili.module.pojo.dto.currency.CurrencyTypesDto;
import top.jilijili.module.pojo.entity.currency.CurrencyTypes;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【shop_currency_types】的数据库操作Service
* @createDate 2023-10-08 23:42:48
*/
public interface CurrencyTypesService extends IService<CurrencyTypes> {

    Page<CurrencyTypes> selectAll(CurrencyTypesDto dto);
}
