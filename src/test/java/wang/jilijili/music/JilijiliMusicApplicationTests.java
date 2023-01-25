package wang.jilijili.music;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;

@SpringBootTest
class JilijiliMusicApplicationTests {

    public static Comparator<String> getComparator() {
        return (o1, o2) -> o2.length() - o1.length();
    }

    @Test
    void contextLoads() {

    }

}
