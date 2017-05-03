

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Administrator extends MainMenu {
    public static ArrayList<String> info = new ArrayList();

    public Administrator() {
    }
    
    public static void admin() {
    	try {
    		
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");

        Scanner input = new Scanner(System.in);
        System.out.println(" Please enter Administrator ID: ");
        String adminID = input.nextLine();
        System.out.println(" PLease enter Administrator Password ");
        String adminPassowrd = input.nextLine();
        if(adminID.equals(adminID) && adminPassowrd.equals("viper")) {
            adminStockControl();
        } else {
            System.out.println(" Administrative Password or User Id incorrect You are now being redirected to the Main Menu");
        }
        Statement st=con.createStatement();
        st.executeUpdate("insert into administrator (Admin_ID, Admin_Password)"
        		+ "value(' " +adminID+" ' , ' " +adminPassowrd+" ')");
    	}
        catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}finally{
        	
        }
    	
    }
    public static void adminStockControl() throws SQLException {
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        
        System.out.println("***Welcome to the administrative main menu***");
        System.out.println("1.) To Add user info Please Press 1             ");
        System.out.println("2.) To ALter User Info Please Press 2 ");
        System.out.println("3.) To Remove user info Please Press 3");
        System.out.println("4.) To Add new Stock Options Press 4");
        System.out.println("5.) To Alter Stock Info Please Press 5");
        System.out.println("6.) To remove Stock options please press 6  ");
        System.out.println("7.) Please press the number 7 to quit  ");
      
        while(!quit) {
            System.out.println("Now please Enter a choice from the administrative main menu: ");
            int choice = input.nextInt();
            input.nextLine();
            switch(choice) {
            case 1:
                addUserInf("", "");
                break;
            case 2:
                altUserinfo(0, "");
                break;
            case 3:
                removeusInfo();
                break;
            case 4:
                addStockOpt();
                break;
            case 5:
                alterStock("", "");
            case 6:
                removeStock();
                break;
            case 7:
                quit = true;
            }
        }

    }

    public static void removeStock() throws SQLException {
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
    	Statement st=con.createStatement();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Stock ID: ");
        int stockID = input.nextInt();
        input.nextLine();
        st= con.createStatement();
        String sql = "DELETE FROM stock " +
                     "WHERE Stock_ID  = ' " +stockID+" '";
        st.executeUpdate(sql);
    }

    public static void alterStock (String altname, String altnumberOStock) throws SQLException{
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
    	Statement st =con.createStatement();
        Scanner input = new Scanner(System.in);
        System.out.println("Please Alter name of stock if needed, if not, enter the original name");
        altname = input.nextLine();
        info.add(altname);
        System.out.println("Please Enter Stock Id to Confirm Change. ");
        altnumberOStock = input.nextLine();
        info.add(altnumberOStock);
        PreparedStatement updateEXP = (PreparedStatement) con.prepareStatement("update`stock` set `Stock_Name` =  ' " +altname+" ' ''  where `Stock_ID` = ' " +altnumberOStock+" '");
        int updateEXP_done = updateEXP.executeUpdate();
        System.out.println("Thank You! ");
    }
    public static void addStockOpt()throws SQLException {
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
        Scanner input = new Scanner(System.in);
        System.out.println("Pleas enter new Stock ID: ");
        String admin = input.nextLine();
        System.out.println("Pleas enter new Stock Name: ");
        String sname = input.nextLine();
        info.add(sname);
        System.out.println("Please enter the amount of stock that you will be adding: ");
        String numofstock = input.nextLine();
        info.add(numofstock);
        Statement st=con.createStatement();
        st.executeUpdate("insert into stock (Stock_ID, Stock_Name, Stock_Amount)"
        		+ "value(' " +admin+" ' , ' " +sname+" ' , ' " +numofstock+" ')");
        System.out.println("stock " + admin + " has now been added to the program. ");
    
    }
    public static void removeusInfo()throws SQLException{
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
    	Statement st=con.createStatement();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter User ID to remove user info: ");
        String userID = input.nextLine();
        input.nextLine();
        System.out.println("To confirm user removal, please provide admin password: ");
        String pass=input.nextLine();
        if(pass.equals("viper")){
        	 st= con.createStatement();
             String sql = "DELETE FROM users " +
                          "WHERE User_ID  = ' " +userID+" '";
             st.executeUpdate(sql);
        }else{
        	System.out.println("Password incorrect Please Try Again: ");
        }
        
    }
    public static void altUserinfo (int ID, String name) throws SQLException{
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter Full Name To Replace The Old Name  ");
        String fName = input.nextLine();
        info.add(fName);
        System.out.println("Now Please Enter your  UserID to verify the change: ");
        String userID = input.nextLine();
        info.add(userID);
        System.out.println("Your user Name has been updated to " + fName);
        PreparedStatement updateEXP = (PreparedStatement) con.prepareStatement("update`users` set `Full_Name` =  ' " +fName+" ' ''  where `User_ID` = ' " +userID+" '");
        int updateEXP_done = updateEXP.executeUpdate();
    }

    public static void addUserInf(String name, String ID)throws SQLException {
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the name of the user that you will be adding: ");
        String NameofAd = input.nextLine();
        info.add(NameofAd);
        System.out.println("Pleas enter new user ID: ");
        String sname = input.nextLine();
        info.add(sname);
        Statement st=con.createStatement();
        st.executeUpdate("insert into stock (Full_name, Stock_Name, Stock_Amount)"
        		+ "value(' " +NameofAd+" ' , ' " +sname+" ' )");
        System.out.println("stock " + NameofAd + " has now been added to the program. ");
        
    }
}
