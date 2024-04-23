import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Checkout {
    private final UUID checkout_id;
    DateTimeFormatter formatter;
    private Tool checkedOutTool;
    private long discount;
    private LocalDate checkoutDate;
    private int rentalDayCount;

    public Checkout() {
        this.formatter = DateTimeFormatter.ofPattern("M/d/yy");
        this.checkout_id = UUID.randomUUID();
    }

    public UUID getCheckout_id() {
        return checkout_id;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        checkoutDate.format(formatter);
        this.checkoutDate = checkoutDate;
    }

    public long getDiscount() {
        double sol = ((double) discount  / 100) * 100;
        return (long) sol;
    }

    public void setDiscount(long discount) {
        if(discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Invalid Discount Offered must be between 0-100");
        }
        this.discount = discount;
    }

    public int getRentalDayCount() {
        return rentalDayCount;
    }

    public void setRentalDayCount(int rentalDayCount) {
        if(rentalDayCount < 1) {
            throw new IllegalArgumentException("Invalid rental day count number must be greater than 1");
        }
        this.rentalDayCount = rentalDayCount;
    }

    public Tool getCheckedOutTool() {
        return checkedOutTool;
    }

    public void setCheckedOutTool(Tool checkedOutTool) {
        this.checkedOutTool = checkedOutTool;
    }

    RentalAgreement generateRentalAgreement(Checkout checkout) {
        return new RentalAgreement(this);
    }
}
