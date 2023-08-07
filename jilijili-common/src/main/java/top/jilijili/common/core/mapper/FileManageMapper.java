package top.jilijili.common.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jilijili.common.core.pojo.entity.FileManage;
import top.jilijili.common.core.pojo.vo.FileTypeTreeVO;

/**
 * @author admin
 * @description 针对表【file_manage】的数据库操作Mapper
 * @createDate 2023-03-23 09:30:14
 * @Entity wang.jilijili.common.core.pojo.entity.FileManage
 */

@Mapper
public interface FileManageMapper extends BaseMapper<FileManage> {


    /**
     * 通过字典类型查询
     *
     * @param dictType
     * @return 树形结构
     */
    FileTypeTreeVO queryByDict(String dictType);
}




