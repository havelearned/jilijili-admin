package wang.jilijili.common.constant;

/**
 * @author Amani
 * @date 2023年03月21日 14:07
 */
public final class SecurityConstant {
    private SecurityConstant() {
    }

    /**
     * 密钥
     */
    public static final String SECRET = "Jilijili-Music";
    /**
     * 10days
     */
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/tokens/";
    public static final String[] SWAGGER_UP_URL = {
            // 文件上传
            "/temp/tmp/upload/**",

            // 测试
            "/test/**",

            // 微信接口
            "/weChat/**",

            // 多文件上传
            "/multipleMusic/**",

            // 文件上传
            "/upload/**",

            // 验证码
            "/tokens/randomImage/**",

            // 错误返回
            "/error",

            // admin 监控
            "/admin/**", "/admin", "/actuator", "/actuator/**",

            // 接口文档
            "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html"
    };
}
