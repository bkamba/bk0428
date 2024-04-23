import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Tools available for rental
        ArrayList<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("CHNS"));
        tools.add(new Tool("LADW"));
        tools.add(new Tool("JAKD"));
        tools.add(new Tool("JAKR"));

        System.out.println("Hello and welcome!");

        for (Tool tool : tools) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println(tool.getType());
            System.out.println(tool.getRate());
        }
    }
}