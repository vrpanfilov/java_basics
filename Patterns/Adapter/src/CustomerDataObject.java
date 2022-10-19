import java.util.Date;
import java.util.List;

public class CustomerDataObject implements DataObject {
    private Customer customer;

    public static final long millisecondsInMonth = 30 * 24 * 3600 * 1000L;
    public static final long millisecondsInYear =  365 * 24 * 3600 * 1000L;

    public CustomerDataObject(Customer customer) {
        this.customer = customer;
    }

    public double getValue(String fieldName) {
        switch (fieldName) {
            case "callsForMonth":
                return calculateCallsForLastMonth();
            case "callsForYear":
                return calculateCallsForLastYear();
            case "minutesForMonth":
                return calculateMinutesForLastMonth();
            default:
                throw new IllegalArgumentException(
                        "Field " + fieldName + " doesn't exist!");
        }
    }

    private double calculateCallsForLastMonth() {
        Date now = new Date();
        Date monthAgo = new Date(now.getTime() - millisecondsInMonth);
        return customer.getCallCount(monthAgo, now);
    }

    private double calculateCallsForLastYear() {
        Date now = new Date();
        Date yearAgo = new Date(now.getTime() - millisecondsInYear);
        return customer.getCallCount(yearAgo, now);
    }

    private double calculateMinutesForLastMonth() {
        Date now = new Date();
        Date monthAgo = new Date(now.getTime() - millisecondsInMonth);
        List<Call> calls = customer.getCalls(monthAgo, now);
        int minutes = 0;
        for (Call call : calls) {
            minutes += call.getDuration();
        }
        return minutes;
    }
}
