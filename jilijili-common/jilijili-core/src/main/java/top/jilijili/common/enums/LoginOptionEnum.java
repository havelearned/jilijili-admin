package top.jilijili.common.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum LoginOptionEnum {
    DEFAULT("jilijiliService", 1, "DEFAULT"),
    QQ("qqLoginService", 2, "QQ"),
    WX("wxLoginService", 3, "WX"),
    GITEE("giteeLoginService", 4, "GITEE"),
    GITHUB("githubLoginService", 5, "GITHUB");
    @Setter
    @Getter
    private String option;
    private Integer value;
    private String label;

    LoginOptionEnum(String option, Integer value, String label) {
        this.option = option;
        this.value = value;
        this.label = label;
    }
}
