package wang.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 歌手和专辑信息表
 * @author admin
 * @TableName singer_alibum
 */
@TableName(value ="singer_alibum")
@Data
public class SingerAlibum implements Serializable {
    /**
     * 歌手id
     */
    @TableField(value = "singer_id")
    private String singerId;

    /**
     * 专辑id
     */
    @TableField(value = "alibum_id")
    private String alibumId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}