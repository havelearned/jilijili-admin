package wang.jilijili.common.enums;

import lombok.Getter;

/**
 * 歌曲状态
 * @author admin
 */

@Getter
public enum MusicStatus{
    /**
     * 草稿
     * */
    DRAFT,

    /**
     * 上架
     * */
    PUBLISHED,

    /**
     * 下架
     * */
    CLOSED;



}
