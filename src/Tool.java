import java.time.LocalDate;

public class Tool {
    private String code;
    private String type;
    private String brand;
    double[] charges = {1.99, 1.49, 2.99};
    private double rate = 0;
    // weekday charge is always true
    private boolean weekendCharge = false;
    private boolean holidayCharge = false;

    /* This constructor creates a Tool item that has an associated code
        type and brand. Then determines the rental charge based on the type.
     */
    public Tool(String code){
        this.code = code;

        switch (code) {
            case "JAKR" -> {
                this.brand = "Ridgid";
                this.type = "Jackhammer";
                rate = charges[2];
            }

            case "CHNS" -> {
                this.brand = "Stihl";
                this.type = "Chainsaw";
                rate = charges[1];
                holidayCharge = true;
            }
            case "LADW" -> {
                this.brand = "Werner";
                this.type = "Ladder";
                rate = charges[0];
                weekendCharge = true;
            }
            case "JAKD" -> {
                this.brand = "DeWalt";
                this.type = "Jackhammer";
                rate = charges[2];
            }
        }

    }


    public String getCode() {
        return this.code;
    }
    public String getType() {
        return this.type;
    }

    public double getRate() {
        return rate;
    }

    public String getBrand() {
        return this.brand;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }
}
