package top.jilijili.mall.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.jilijili.common.entity.Result;
import top.jilijili.module.entity.Promocodes;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.entity.dto.PromocodesDto;
import top.jilijili.module.entity.vo.PromocodesVo;

import java.io.Serializable;
import java.util.List;

/**
* @author admin
* @description 针对表【shop_promocodes】的数据库操作Service
* @createDate 2023-10-06 23:14:17
*/
public interface PromocodesService extends IService<Promocodes> {

    IPage<PromocodesVo> selectAllPromocodes(PromocodesDto promocodesDto);

    Result<PromocodesVo> selectOnePromocodes(Serializable id);

    Result<PromocodesVo> insertPromocodes(PromocodesDto promocodesDto);

    Result<PromocodesVo> updatePromocodes(PromocodesDto promocodesDto);

    Result<List<Promocodes>> batchPromocodes(PromocodesDto promocodesDto);
}
