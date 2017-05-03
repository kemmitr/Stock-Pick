//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import java.util.Scanner;

public class MainMenu {
    private static Administrator administrator = new Administrator();
    private static Citizen citizen = new Citizen();
    private static Scanner input;

    public MainMenu() {
    }

    public static void main(String[] args) {
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("                 ****Stock Tool Main Menu****                      ");
        System.out.println("                                                                    ");
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("Are you an Authorized Administrator?   ");
        System.out.println("        PLease Enter yes or no:         ");
        String admin = input.nextLine();
        if(admin.equals("yes")) {
            Administrator.admin();
        } else {
            Citizen.cit();
        }

    }

    static {
        input = new Scanner(System.in);
    }
}
