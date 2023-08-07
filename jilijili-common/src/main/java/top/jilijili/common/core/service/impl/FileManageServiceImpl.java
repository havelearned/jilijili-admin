package top.jilijili.common.core.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.jilijili.common.core.mapper.FileManageMapper;
import top.jilijili.common.core.pojo.bo.FileManageConvertBo;
import top.jilijili.common.core.pojo.dto.FileManageDto;
import top.jilijili.common.core.pojo.entity.FileManage;
import top.jilijili.common.core.pojo.vo.FileTypeTreeVO;
import top.jilijili.common.core.service.FileManageService;

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
    public IPage<FileManage> list(IPage<FileManage> iPage, FileManageDto fileManageDto) {
        FileManage fileManage = this.fileManageConvertBo.toFileManageEntity(fileManageDto);
        return  this.fileManageMapper
                .selectPage(iPage, new QueryWrapper<>(fileManage));
    }

    @Override
    public FileTypeTreeVO queryByDict(String dictType) {
        FileTypeTreeVO fileTypeTreeVO = this.fileManageMapper.queryByDict(dictType);

//        Map<String, Map<String, List<String>>> treeMap = new TreeMap<>();
//        dictTypeVO.getSysDictDataList().forEach(item -> {
//
//            String[] fileType = item.getDictLabel().split("/");
//            String category = fileType[0];
//            String subCategory = fileType.length > 1 ? fileType[1] : "";
//            if (!treeMap.containsKey(category)) {
//                treeMap.put(category, new TreeMap<>());
//            }
//            if (!treeMap.get(category).containsKey(subCategory)) {
//                treeMap.get(category).put(subCategory, new ArrayList<>());
//            }
//            treeMap.get(category).get(subCategory).add(item.getDictValue());
//
//        });
//        treeMap.forEach((item, map) -> {
//            System.out.printf("第一层:%s", item);
//            for (String s : map.keySet()) {
//                map.get(s).forEach(item2 -> System.out.printf("%s\n", item2));
//            }
//            System.out.println();
//        });
        return fileTypeTreeVO;
    }
}



