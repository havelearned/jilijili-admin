package wnag.jilijili.music.pojo.entity;

import lombok.Data;
import wang.jilijili.music.common.enums.MusicStatus;

/**
 * @author Amani
 * @date 2023年03月07日 16:34
 */
@Data
public class Music {

    String name;

    MusicStatus musicStatus;
}
