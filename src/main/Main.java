import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Tools available for rental
        ArrayList<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("CHNS"));
        tools.add(new Tool("LADW"));
        tools.add(new Tool("JAKD"));
        tools.add(new Tool("JAKR"));
        Checkout checkout = new Checkout();

        System.out.println("Hello and Welcome to the Rental Application Store!");

        System.out.println("Please select a item that you would like to rent out by entering the number line the tool is listed on");
        System.out.println("\n");
        System.out.println("=======================================");

        int i = 1;
        for(Tool tool : tools) {
            System.out.println(i + ")  Tool: " + tool.getCode() +" Type: " + tool.getType() + " Brand: " + tool.getBrand() + " Rate: " + tool.getRate() );
            i++;
        }

        Scanner scanner = new Scanner(System.in);
        int code = scanner.nextInt();

        System.out.println("You have chosen tool " + tools.get(code - 1).getCode() + " Please enter how many days you would like to rent it");

        int dayCount = scanner.nextInt();

        checkout.setCheckedOutTool(tools.get(code - 1));
        checkout.setCheckoutDate(LocalDate.now());
        checkout.setRentalDayCount(dayCount);
        checkout.setDiscount(10);

        System.out.println("Please Read over the rental agreement below");

        RentalAgreement agreement = checkout.generateRentalAgreement(checkout);
        agreement.printRentalAgreement();

        System.out.println("If you agree with the details above enter yes if not enter no");

        String answer = scanner.next();

        if(answer.equals("yes")) {
            System.out.println("Thank you for using our Application! Please return the tool by the specified due date in your rental agreement");
        }else if(answer.equals("no")){
            System.out.println("Sorry we couldn't help you, thanks for checking out our Application please come again");
        }


    }
}