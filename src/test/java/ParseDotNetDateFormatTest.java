import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ericliu on 6/01/2016.
 */

public class ParseDotNetDateFormatTest {


    String dateStr = "\\/Date(1233323754523+0100)\\/";


    @Test
    public void testParsingMSDate() throws IOException {
        // Not anchored since it will be used with the (misnamed) .matches() method
        final Pattern pattern
                = Pattern.compile("\\\\/Date\\((\\d*)[+-](\\d*)\\)\\\\/");


        // Try and match the input.
        final Matcher matcher = pattern.matcher(dateStr);

        if (!matcher.matches()) {
            System.err.println("Bad pattern!"); // Yuck
            System.exit(1);
        }

        // The first group capture the milliseconds, the second one the time zone

        final long millis = Long.parseLong(matcher.group(1));
        String tz = matcher.group(2);
        if (tz.isEmpty()) // It can happen, in which case the default is assumed to be...
            tz = "+0000";

        // Instantiate a date object...
        final Date date = new Date(millis);

        // And print it using an appropriate date format
        System.out.printf("Date: %s %s\n",
                new SimpleDateFormat("yyyy/MM/dd HH:MM:ss").format(date), tz);
    }

}



