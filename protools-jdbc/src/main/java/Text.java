import com.google.common.base.Strings;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author : HouGY
 * @since : 2021/3/16
 */
public class Text {

    public static void main(String[] args) {
        Duration d = Duration.ofSeconds(536000);
       LocalDateTime localDateTime = LocalDateTime.now();
        int dayOfMonth = localDateTime.getDayOfMonth();
        Strings.isNullOrEmpty("");
        System.out.println(Strings.isNullOrEmpty(""));
        System.out.println(Strings.isNullOrEmpty(null));
        LocalDateTime dateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println(dateTime.toString());
        LocalDateTime newDateTime = dateTime.plusHours(-1);
        System.out.println(newDateTime.toString());
    }
}
