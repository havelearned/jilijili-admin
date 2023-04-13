package wang.jilijili.music.enums;

import lombok.Getter;

/**
 * @author Amani
 * @date 2023年04月13日 11:56
 */
@Getter
public enum MusicStatus {

    /**
     * 草稿
     */
    DRAFT("草稿", 1),

    /**
     * 上架
     */
    PUBLISHED("上架", 2),

    /**
     * 下架
     */
    CLOSED("下架", 0);


    MusicStatus(String status, Integer value) {
        this.status = status;
        this.value = value;
    }

    final String status;
    final Integer value;

    public static MusicStatus fromValue(Integer value) {
        if (value != null) {
            for (MusicStatus musicStatus : MusicStatus.values()) {
                Integer value1 = musicStatus.getValue();
                if (value1.equals(value)) {
                    return musicStatus;
                }
            }
        }
        return null;
    }

}

