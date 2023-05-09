package wang.jilijili.common.core.pojo.vo;

import lombok.Data;

/**
 * @author Amani
 * @date 2023年05月09日 16:59
 */
@Data
public class OperationLogVO {

    private String operationLogId;

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


    private String userId;
    private String username;
    private String nickname;
}
