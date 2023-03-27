package wang.jilijili.music.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import wang.jilijili.music.pojo.dto.AlibumDto;
import wang.jilijili.music.pojo.entity.Alibum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【alibum(专辑表)】的数据库操作Service
* @createDate 2023-03-21 15:15:44
*/
@DS("slave_1")
public interface AlibumService extends IService<Alibum> {

    AlibumDto create(AlibumDto alibumDto);

    AlibumDto update(AlibumDto alibumDto);
}
