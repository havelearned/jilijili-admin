package wang.jilijili.common.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import wang.jilijili.common.pojo.entity.SuperEntity;

import java.io.Serializable;

/**
 * @author admin
 * @TableName operation_log
 */
@TableName(value = "operation_log")
@Data
public class OperationLog extends SuperEntity implements Serializable {

    /**
     * 操作人 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 操作人 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 登录ip
     */
    @TableField(value = "last_login_ip")
    private String lastLoginIp;

    /**
     * 操作类型: 1:添加 2:修改 3:删除 4:查询
     */
    @TableField(value = "operation_type")
    private Integer operationType;

    /**
     * 操作内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 请求路径
     */
    @TableField(value = "request_url")
    private String requestUrl;

    /**
     * 包名+类名+方法名
     */
    @TableField(value = "method_execution")
    private String methodExecution;

    /**
     * 传参的数据
     */
    @TableField(value = "request_data")
    private String requestData;

    /**
     * 模块名称
     */
    @TableField(value = "module_name")
    private String moduleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}