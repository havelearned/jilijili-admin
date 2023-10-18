package top.jilijili.mall.currency.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.mall.currency.mapper.VirtualCurrencyMapper;
import top.jilijili.mall.currency.service.VirtualCurrencyService;
import top.jilijili.module.pojo.dto.currency.VirtualCurrencyDto;
import top.jilijili.module.pojo.entity.currency.VirtualCurrency;
import top.jilijili.module.pojo.vo.currency.VirtualCurrencyVo;

import java.io.Serializable;

/**
 * @author admin
 * @description 针对表【shop_virtual_currency】的数据库操作Service实现
 * @createDate 2023-10-08 23:42:48
 */
@Service
@AllArgsConstructor
public class VirtualCurrencyServiceImpl extends ServiceImpl<VirtualCurrencyMapper, VirtualCurrency>
        implements VirtualCurrencyService {
    private VirtualCurrencyMapper virtualCurrencyMapper;


    @Override
    public Page<VirtualCurrencyVo> selectAll(VirtualCurrencyDto dto) {
        QueryWrapper<VirtualCurrencyVo> qw = new QueryWrapper<>();
        qw
                .eq(StringUtils.hasText(dto.getCurrencyId()), "svc.currency_id", dto.getCurrencyId())
                .eq(StringUtils.hasText(dto.getUserId()), "su.user_id", dto.getUserId())
                .like(StringUtils.hasText(dto.getCurrencyName()), "sct.currency_name", dto.getCurrencyName())
                .like(StringUtils.hasText(dto.getUsername()), "su.username", dto.getUsername())
                .between(dto.getMax() != null && dto.getMin() != null,
                        "svc.balance", dto.getMin(), dto.getMax())
                .between(dto.getCreatedTime() != null && dto.getComparisonTime() != null,
                        "svc.created_time", dto.getCreatedTime(), dto.getComparisonTime())
                .orderByDesc("svc.created_time");
        Page<VirtualCurrencyVo> page = new Page<>(dto.getPage(), dto.getSize());

        return this.virtualCurrencyMapper.selectAll(page, qw);
    }


    @Override
    public VirtualCurrencyVo selectOne(Serializable id) {
//        VirtualCurrencyDto query = VirtualCurrencyDto.builder()
//                .currencyId(String.valueOf(id))
//                .page(1).size(1000).build();
//        Page<VirtualCurrencyVo> page = this.selectAll(query);
        return null;
    }
}




