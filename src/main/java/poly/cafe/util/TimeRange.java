package poly.cafe.util;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@Data
public class TimeRange {

    private Date begin;
    private Date end;

    // Constructor nhận Date
    public TimeRange(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    // Constructor hỗ trợ LocalDate
    public TimeRange(LocalDate begin, LocalDate end) {
        this(java.sql.Date.valueOf(begin), java.sql.Date.valueOf(end));
    }

    public static TimeRange today() {
        LocalDate begin = LocalDate.now();
        return new TimeRange(begin, begin.plusDays(1));
    }

    public static TimeRange thisWeek() {
        LocalDate now = LocalDate.now();
        LocalDate begin = now.minusDays(now.getDayOfWeek().getValue() - 1);
        return new TimeRange(begin, begin.plusDays(7));
    }

    public static TimeRange thisMonth() {
        LocalDate now = LocalDate.now();
        LocalDate begin = now.withDayOfMonth(1);
        return new TimeRange(begin, begin.plusMonths(1));
    }

    public static TimeRange thisQuarter() {
        LocalDate now = LocalDate.now();
        int firstMonth = (now.getMonthValue() - 1) / 3 * 3 + 1;
        LocalDate begin = LocalDate.of(now.getYear(), firstMonth, 1);
        return new TimeRange(begin, begin.plusMonths(3));
    }

    public static TimeRange thisYear() {
        LocalDate begin = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        return new TimeRange(begin, begin.plusYears(1));
    }
}
