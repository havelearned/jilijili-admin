package top.jilijili.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.entity.SysDictItem;
import top.jilijili.module.entity.dto.ChooseEntityDto;
import top.jilijili.module.entity.vo.SysDictItemVo;

import java.util.List;

/**
* @author admin
* @description 针对表【sys_dict_item(字典item表)】的数据库操作Service
* @createDate 2023-10-06 14:40:49
*/
public interface SysDictItemService extends IService<SysDictItem> {

    /**
     * 通过字典类型 查询字典子项分页列表
     *
     * @param sysDictItem
     * @return 字典子项分页列表
     */
    IPage<SysDictItemVo> getDictItemList(SysDictItem sysDictItem);

    /**
     * 通过字典类型查询字典项信息
     * 适配前端选择框
     * @param dictType 字典类型
     * @return label,value result Object item
     */
    List<ChooseEntityDto> getDictItemByDictType(String dictType);


    /**
     * 添加或修改字典子项
     *
     * @param sysDictItem
     * @return 是否成功
     */
    String addOrEditDictItem(SysDictItem sysDictItem);
}
