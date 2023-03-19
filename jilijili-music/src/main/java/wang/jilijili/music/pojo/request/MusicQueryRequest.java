package wang.jilijili.music.pojo.request;

import lombok.Data;

/**
 * 查询表单
 * @author Amani
 * @date 2023年03月09日 11:01
 */
@Data
public class MusicQueryRequest {


    private String name;
    private Integer status;
    
        private Integer page = 0;
        private Integer size = 5;
}
