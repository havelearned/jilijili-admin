package top.jilijili.common.entity;

/**
 * REST API 错误码接口
 *
 * @author hubin
 * @since 2018-06-05
 */
// 使用度较低，如果使用请及时迁移本地 3.5.0 移除
@Deprecated
public interface IErrorCode {

    /**
     * 错误编码 -1、失败 0、成功
     */
    long getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
