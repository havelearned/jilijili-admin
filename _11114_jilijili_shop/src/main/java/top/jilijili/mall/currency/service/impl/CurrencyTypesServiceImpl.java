package top.jilijili.mall.currency.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.mall.currency.mapper.CurrencyTypesMapper;
import top.jilijili.mall.currency.service.CurrencyTypesService;
import top.jilijili.module.pojo.dto.currency.CurrencyTypesDto;
import top.jilijili.module.pojo.entity.currency.CurrencyTypes;

/**
 * @author admin
 * @description 针对表【shop_currency_types】的数据库操作Service实现
 * @createDate 2023-10-08 23:42:48
 */
@Service
@AllArgsConstructor
public class CurrencyTypesServiceImpl extends ServiceImpl<CurrencyTypesMapper, CurrencyTypes>
        implements CurrencyTypesService {


    @Override
    public Page<CurrencyTypes> selectAll(CurrencyTypesDto dto) {
        return this.lambdaQuery()
                .like(StringUtils.hasText(dto.getCurrencyName()), CurrencyTypes::getCurrencyName, dto.getCurrencyName())
                .eq(StringUtils.hasText(dto.getCurrencyCode()), CurrencyTypes::getCurrencyCode, dto.getCurrencyCode())
                .between(dto.getCreatedTime() != null && dto.getComparisonTime() != null,
                        CurrencyTypes::getCreatedTime, dto.getCreatedTime(), dto.getComparisonTime())
                .orderByDesc(CurrencyTypes::getCreatedTime)
                .page(new Page<>(dto.getPage(), dto.getSize()));
    }
}




