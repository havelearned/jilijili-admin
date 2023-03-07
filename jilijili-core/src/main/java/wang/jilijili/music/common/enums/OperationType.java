package wang.jilijili.music.common.enums;

/**
 * @author admin
 */

public enum OperationType {
    /**
     * 添加,创建操作
     * */
    ADD(1, "添加"),

    /**
     *  修改操作
     **/
    UPDATE(2, "修改"),

    /**
     *  删除操作
     **/
    DELETED(3, "删除"),

    /**
     *  查询操作
     **/
    SELECT(4, "查询"),

    /**
     *  导入操作
     **/
    IMPORT(5, "导入"),

    /**
     *  导出操作
     **/
    EXPORT(6, "导出");

    private final Integer num;
    private final String type;

    OperationType(Integer num, String type) {
        this.num = num;
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public String getType() {
        return type;
    }
}
