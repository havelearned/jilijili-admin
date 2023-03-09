package wnag.jilijili.music.pojo.request;

import lombok.Data;

/**
 * @author Amani
 * @date 2023年03月09日 11:01
 */
@Data
public class MusicQueryRequest {

    String name;

    Integer page = 0;
    Integer size = 5;
}
