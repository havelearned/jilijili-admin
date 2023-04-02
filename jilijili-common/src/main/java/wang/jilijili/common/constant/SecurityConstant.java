package wang.jilijili.common.constant;

/**
 * @author Amani
 * @date 2023年03月21日 14:07
 */
public class SecurityConstant {
    /**
     * 密钥
     * */
    public static final String SECRET = "Jilijili-Music";
    /**
     * 10days
     * */
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/tokens/";
    public static final String[] SWAGGER_UP_URL = {
            "/test/**", "/weChat/**","/multipleMusic/**","/upload/**",
            "/error", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html"
    };
}
