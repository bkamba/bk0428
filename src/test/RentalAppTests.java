import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RentalAppTests {

    @Test
    void test1() {
        Tool tool =  new Tool("JAKR");
        Checkout checkout = new Checkout();
        RentalAgreement agreement;

        checkout.setCheckedOutTool(tool);
        checkout.setCheckoutDate(LocalDate.of(2015,9,3));
        checkout.setRentalDayCount(5);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkout.setDiscount(101);
        });

    }


    @Test
    void test2() {
        Tool tool =  new Tool("LADW");
        Checkout checkout = new Checkout();
        RentalAgreement agreement;

        checkout.setCheckedOutTool(tool);
        checkout.setCheckoutDate(LocalDate.of(2020,7,2));
        checkout.setRentalDayCount(3);
        checkout.setDiscount(10);
        agreement = checkout.generateRentalAgreement(checkout);
        Assertions.assertEquals(checkout.getRentalDayCount(),3);
        Assertions.assertEquals(checkout.getCheckoutDate().format(checkout.getFormatter()), "7/2/20");
        Assertions.assertEquals(agreement.calculateDueDate().format(checkout.getFormatter()), "7/5/20");
        Assertions.assertEquals(agreement.finalChargeAmount(), BigDecimal.valueOf(3.58));
        Assertions.assertEquals(checkout.getCheckedOutTool().getCode(), "LADW");
        Assertions.assertEquals(checkout.getCheckedOutTool().getType(), "Ladder");
        Assertions.assertEquals(agreement.dailyRentalCharge(),BigDecimal.valueOf(1.99));
        Assertions.assertEquals(checkout.getCheckedOutTool().getBrand(), "Werner");
        Assertions.assertEquals(agreement.calculateDiscountAmount(), BigDecimal.valueOf(0.40).setScale(2, RoundingMode.HALF_UP));
        Assertions.assertEquals(checkout.getDiscount(), 10);
    }

    @Test
    void test3() {
        Tool tool =  new Tool("CHNS");
        Checkout checkout = new Checkout();
        RentalAgreement agreement;

        checkout.setCheckedOutTool(tool);
        checkout.setCheckoutDate(LocalDate.of(2015,7,2));
        checkout.setRentalDayCount(5);
        checkout.setDiscount(25);
        agreement = checkout.generateRentalAgreement(checkout);
        Assertions.assertEquals(checkout.getRentalDayCount(),5);
        Assertions.assertEquals(checkout.getCheckoutDate().format(checkout.getFormatter()), "7/2/15");
        Assertions.assertEquals(agreement.calculateDueDate().format(checkout.getFormatter()), "7/7/15");
        Assertions.assertEquals(agreement.finalChargeAmount(), BigDecimal.valueOf(3.35));
        Assertions.assertEquals(checkout.getCheckedOutTool().getCode(), "CHNS");
        Assertions.assertEquals(checkout.getCheckedOutTool().getType(), "Chainsaw");
        Assertions.assertEquals(agreement.dailyRentalCharge(),BigDecimal.valueOf(1.49));
        Assertions.assertEquals(checkout.getCheckedOutTool().getBrand(), "Stihl");
        Assertions.assertEquals(agreement.calculateDiscountAmount(), BigDecimal.valueOf(1.12).setScale(2, RoundingMode.HALF_UP));
        Assertions.assertEquals(checkout.getDiscount(), 25);

    }

    @Test
    void test4() {
        Tool tool =  new Tool("JAKD");
        Checkout checkout = new Checkout();
        RentalAgreement agreement;

        checkout.setCheckedOutTool(tool);
        checkout.setCheckoutDate(LocalDate.of(2015,9,3));
        checkout.setRentalDayCount(6);
        checkout.setDiscount(0);
        agreement = checkout.generateRentalAgreement(checkout);
        Assertions.assertEquals(checkout.getRentalDayCount(),6);
        Assertions.assertEquals(checkout.getCheckoutDate().format(checkout.getFormatter()), "9/3/15");
        Assertions.assertEquals(agreement.calculateDueDate().format(checkout.getFormatter()), "9/9/15");
        Assertions.assertEquals(agreement.finalChargeAmount(), BigDecimal.valueOf(8.97));
        Assertions.assertEquals(checkout.getCheckedOutTool().getCode(), "JAKD");
        Assertions.assertEquals(checkout.getCheckedOutTool().getType(), "Jackhammer");
        Assertions.assertEquals(agreement.dailyRentalCharge(),BigDecimal.valueOf(2.99));
        Assertions.assertEquals(checkout.getCheckedOutTool().getBrand(), "DeWalt");
        Assertions.assertEquals(agreement.calculateDiscountAmount(), BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP));
        Assertions.assertEquals(checkout.getDiscount(), 0);


    }

    @Test
    void test5() {
        Tool toolOne =  new Tool("JAKR");
        Checkout checkout = new Checkout();
        RentalAgreement agreement;

        checkout.setCheckedOutTool(toolOne);
        checkout.setCheckoutDate(LocalDate.of(2015,7,2));
        checkout.setRentalDayCount(9);
        checkout.setDiscount(0);
        checkout.generateRentalAgreement(checkout);
        agreement = checkout.generateRentalAgreement(checkout);
        Assertions.assertEquals(checkout.getRentalDayCount(),9);
        Assertions.assertEquals(checkout.getCheckoutDate().format(checkout.getFormatter()), "7/2/15");
        Assertions.assertEquals(agreement.calculateDueDate().format(checkout.getFormatter()), "7/11/15");
        Assertions.assertEquals(agreement.finalChargeAmount(), BigDecimal.valueOf(14.95));
        Assertions.assertEquals(checkout.getCheckedOutTool().getCode(), "JAKR");
        Assertions.assertEquals(checkout.getCheckedOutTool().getType(), "Jackhammer");
        Assertions.assertEquals(agreement.dailyRentalCharge(),BigDecimal.valueOf(2.99));
        Assertions.assertEquals(checkout.getCheckedOutTool().getBrand(), "Ridgid");
        Assertions.assertEquals(agreement.calculateDiscountAmount(), BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP));
        Assertions.assertEquals(checkout.getDiscount(), 0);

    }

    @Test
    void test6() {
        Tool tool =  new Tool("JAKR");
        Checkout checkout = new Checkout();
        RentalAgreement agreement;

        checkout.setCheckedOutTool(tool);
        checkout.setCheckoutDate(LocalDate.of(2020,7,2));
        checkout.setRentalDayCount(4);
        checkout.setDiscount(50);
        agreement = checkout.generateRentalAgreement(checkout);
        Assertions.assertEquals(checkout.getRentalDayCount(),4);
        Assertions.assertEquals(checkout.getCheckoutDate().format(checkout.getFormatter()), "7/2/20");
        Assertions.assertEquals(agreement.calculateDueDate().format(checkout.getFormatter()), "7/6/20");
        Assertions.assertEquals(agreement.finalChargeAmount(), BigDecimal.valueOf(1.49));
        Assertions.assertEquals(checkout.getCheckedOutTool().getCode(), "JAKR");
        Assertions.assertEquals(checkout.getCheckedOutTool().getType(), "Jackhammer");
        Assertions.assertEquals(agreement.dailyRentalCharge(),BigDecimal.valueOf(2.99));
        Assertions.assertEquals(checkout.getCheckedOutTool().getBrand(), "Ridgid");
        Assertions.assertEquals(agreement.calculateDiscountAmount(), BigDecimal.valueOf(1.50).setScale(2, RoundingMode.HALF_UP));
        Assertions.assertEquals(checkout.getDiscount(), 50);

    }

    // This test is to check the validity of a rentalDayCount
    @Test
    void test7() {
        Tool tool =  new Tool("JAKR");
        Checkout checkout = new Checkout();
        RentalAgreement agreement;

        checkout.setCheckedOutTool(tool);
        checkout.setCheckoutDate(LocalDate.of(2015,9,3));
        checkout.setDiscount(50);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkout.setRentalDayCount(0);
        });
    }



}
