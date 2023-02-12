package wang.jilijili.music.pojo.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @Auther: Amani
 * @Date: 2023/1/23 17:22
 * @Description:
 */
@Data
public class SuperEntity {

    @TableId(type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    protected String id;


    @TableField(fill = FieldFill.INSERT)
    protected Date createdTime;

    @TableField(fill = FieldFill.UPDATE)
    protected Date updatedTime;
}
