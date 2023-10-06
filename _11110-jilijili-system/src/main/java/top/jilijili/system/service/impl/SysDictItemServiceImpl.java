package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.module.entity.SysDictItem;
import top.jilijili.module.entity.dto.ChooseEntityDto;
import top.jilijili.system.mapper.SysDictItemMapper;
import top.jilijili.system.service.SysDictItemService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【sys_dict_item(字典item表)】的数据库操作Service实现
 * @createDate 2023-10-06 14:40:49
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {


    /**
     * 通过字典类型 查询字典子项分页列表
     *
     * @param sysDictItem
     * @return 字典子项分页列表
     */
    @Override
    public IPage<SysDictItem> getDictItemList(SysDictItem sysDictItem) {
        if (!StringUtils.hasText(sysDictItem.getDictionaryType())) {
            return null;
        }
        return this.lambdaQuery()
                .eq(sysDictItem.getDictItemId() != null, SysDictItem::getDictItemId, sysDictItem.getDictItemId())
                .eq(StringUtils.hasText(sysDictItem.getDictionaryType()), SysDictItem::getDictionaryType, sysDictItem.getDictionaryType())
                .eq(sysDictItem.getStatus() != null, SysDictItem::getStatus, sysDictItem.getStatus())
                .page(new Page<>(sysDictItem.getPage(), sysDictItem.getSize()));
    }

    /**
     * 通过字典类型查询字典项信息
     * 适配前端选择框
     *
     * @param dictType 字典类型
     * @return label, value result Object item
     */
    @Override
    public List<ChooseEntityDto> getDictItemByDictType(String dictType) {
        List<SysDictItem> list = this.lambdaQuery()
                .eq(StringUtils.hasText(dictType), SysDictItem::getDictItemId, dictType).list();

        return list.stream().map(item -> ChooseEntityDto.builder()
                .label(item.getItemLabel())
                .value(item.getItemValue()).build()).collect(Collectors.toList());


    }

    /**
     * 添加或修改字典子项
     *
     * @param sysDictItem
     * @return 是否成功
     */
    @Override
    public String addOrEditDictItem(SysDictItem sysDictItem) {
        return this.saveOrUpdate(sysDictItem) ? "操作成功" : "操作失败";
    }
}




