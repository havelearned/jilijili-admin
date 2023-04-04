package wang.jilijili.common.core.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wang.jilijili.common.core.mapper.FileManageMapper;
import wang.jilijili.common.core.pojo.bo.FileManageConvertBo;
import wang.jilijili.common.core.pojo.dto.FileManageDto;
import wang.jilijili.common.core.pojo.entity.FileManage;
import wang.jilijili.common.core.service.FileManageService;

/**
 * @author admin
 * @description 针对表【file_manage】的数据库操作Service实现
 * @createDate 2023-03-23 09:30:14
 */
@Service
public class FileManageServiceImpl extends ServiceImpl<FileManageMapper, FileManage>
        implements FileManageService {

    FileManageMapper fileManageMapper;

    FileManageConvertBo fileManageConvertBo;

    public FileManageServiceImpl(FileManageMapper fileManageMapper, FileManageConvertBo fileManageConvertBo) {
        this.fileManageMapper = fileManageMapper;
        this.fileManageConvertBo = fileManageConvertBo;
    }

    @Override
    public IPage<FileManageDto> list(IPage<FileManage> iPage, FileManageDto fileManageDto) {
        FileManage fileManage = this.fileManageConvertBo.toFileManageEntity(fileManageDto);
        IPage<FileManage> fileManageIpage = this.fileManageMapper
                .selectPage(iPage, new QueryWrapper<FileManage>(fileManage));
        return fileManageIpage.convert(item -> this.fileManageConvertBo.toFileManageDto(item));
    }
}




