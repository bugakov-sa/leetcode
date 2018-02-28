import java.time.*;

public class TimeUtil {
    public static void main(String[] args) {
        System.out.println("utc " + System.currentTimeMillis());
        System.out.println("msk " + LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
