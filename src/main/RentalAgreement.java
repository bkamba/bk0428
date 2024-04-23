import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.UUID;

public class RentalAgreement {
    private final Checkout checkout;

    public RentalAgreement(Checkout checkout) {
        this.checkout = checkout;
        System.out.println(printRentalAgreement());
    }

    public LocalDate calculateDueDate() {
        return checkout.getCheckoutDate().plusDays(checkout.getRentalDayCount());
    }

    public BigDecimal dailyRentalCharge() {
        return BigDecimal.valueOf(checkout.getCheckedOutTool().getRate()).setScale(2,RoundingMode.HALF_UP);
    }

    public long chargeDays() {
        long rentalDays = ChronoUnit.DAYS.between(checkout.getCheckoutDate(), calculateDueDate());

        // check for independence day
        LocalDate independenceDay = LocalDate.of(checkout.getCheckoutDate().getYear(),
                Month.JULY, 4);

        if(!checkout.getCheckedOutTool().isHolidayCharge()) {
            if (independenceDay.isAfter(checkout.getCheckoutDate()) && independenceDay.isBefore(calculateDueDate())) {
                rentalDays--;
            }
        }
        if(!checkout.getCheckedOutTool().isHolidayCharge()) {
            LocalDate laborTemp = checkout.getCheckoutDate();
            while(laborTemp.getDayOfMonth() <= 7) {
                if (laborTemp.getMonth() == Month.SEPTEMBER && laborTemp.getDayOfWeek() == DayOfWeek.MONDAY) {
                    rentalDays--;
                }
                laborTemp = laborTemp.plusDays(1);
            }
        }

        if(!checkout.getCheckedOutTool().isWeekendCharge()) {
            LocalDate temp = checkout.getCheckoutDate();
            while( temp.isBefore(calculateDueDate()) || temp.isEqual(calculateDueDate())) {
                if(temp.getDayOfWeek() == DayOfWeek.SATURDAY || temp.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    rentalDays--;
                }
                temp = temp.plusDays(1);

            }
        }
        return rentalDays;
    }

    public BigDecimal preDiscountCharge() {
        double total = (double) chargeDays() * checkout.getCheckedOutTool().getRate();
        return BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateDiscountAmount() {
        double percentageAmount = (double) checkout.getDiscount() / 100;
        BigDecimal discount = BigDecimal.valueOf(percentageAmount);
        return discount.multiply(preDiscountCharge()).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal finalChargeAmount() {
        return BigDecimal.valueOf(preDiscountCharge().doubleValue() - calculateDiscountAmount().doubleValue()).setScale(2,RoundingMode.HALF_UP);
    }

    public String printRentalAgreement() {
        String id = checkout.getCheckout_id().toString();
        String code = checkout.getCheckedOutTool().getCode();
        String type = checkout.getCheckedOutTool().getType();
        String brand = checkout.getCheckedOutTool().getBrand();
        int rentalDays = checkout.getRentalDayCount();
        String checkOutDate = checkout.getCheckoutDate().format(checkout.getFormatter());
        String dueDate = calculateDueDate().format(checkout.getFormatter());
        BigDecimal dailyCharge = dailyRentalCharge();
        long chargeDays = chargeDays();
        BigDecimal preDiscount = preDiscountCharge();
        BigDecimal discount = new BigDecimal(Long.toString(checkout.getDiscount()));
        BigDecimal discountAmount = calculateDiscountAmount();
        BigDecimal finalCharge = finalChargeAmount();

        String agreement = """
                Rental Agreement: %s
                                
                Tool code: %s
                Tool type: %s
                Tool brand: %s
                Rental days: %d
                Check out date: %s
                Due Date: %s
                Daily rental charge: %s
                Charge days: %d
                Pre-discount charge: %s
                Discount percent: %s%%
                Discount amount: %s
                Final Charge: %s
                """;

        return String.format(agreement, id, code, type, brand, rentalDays, checkOutDate,
                dueDate, dailyCharge, chargeDays, preDiscount, discount, discountAmount, finalCharge);

    }

}
