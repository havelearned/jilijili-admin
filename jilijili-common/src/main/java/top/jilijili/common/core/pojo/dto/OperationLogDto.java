package top.jilijili.common.core.pojo.dto;

import lombok.Data;

/**
 * @author Amani
 * @date 2023年04月04日 11:22
 */
@Data
public class OperationLogDto extends QueryDto {

    /**
     * 操作人 用户id
     */

    private String userId;

    /**
     * 操作人 用户名称
     */

    private String username;

    /**
     * 登录ip
     */

    private String lastLoginIp;

    /**
     * 操作类型: 1:添加 2:修改 3:删除 4:查询
     */

    private Integer operationType;

    /**
     * 操作内容
     */

    private String content;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 包名+类名+方法名
     */
    private String methodExecution;

    /**
     * 传参的数据
     */

    private String requestData;

    /**
     * 模块名称
     */

    private String moduleName;
}
