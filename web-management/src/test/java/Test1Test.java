import com.proj.core.utils.DateTimeUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * 测试类
 *
 * @author dong.ning
 */
@SpringBootTest
public class Test1Test {

    /**
     * 测试1
     */
    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 1, 20, 0, 0, 0);//.now();
        int day = localDateTime.getDayOfMonth();
        int month = localDateTime.getMonthValue();
        int year = localDateTime.getYear();

        int we = localDateTime.getDayOfWeek().getValue();

        System.out.println("d=" + day);
        System.out.println("month=" + month);
        System.out.println("y=" + year);
        System.out.println("week=" + we);

        System.out.println("--------");
    }

    /**
     * 测试取季度
     */
    @Test
    public void testDatetimeQuartly() {
        for (int i = 1; i <= 12; i++) {
            System.out.print("----" + i + "...");
            LocalDateTime localDateTime = LocalDateTime.of(2022, i, 1, 0, 0, 0);
            System.out.println(DateTimeUtil.getQuarterly(localDateTime));
        }
    }

}
