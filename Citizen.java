

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Citizen {
    public static ArrayList<String> info = new ArrayList();

    public Citizen() {
    }

    public static void cit() {
    	 try {
    			
    			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
    			
        Scanner input = new Scanner(System.in);
        System.out.println("Please create an UserID: ");
        String userID = input.nextLine();
        info.add(userID);
        System.out.println("Please Enter your Full Name: ");
        String fName = input.nextLine();
        info.add(fName);
        System.out.println("Please Create a Password: ");
        String password = input.nextLine();
        info.add(fName);
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("                 ****User Main Menu****                    ");
        System.out.println("Hello " + fName + " and Thank you for providing us with your UserID ");
        System.out.println("+-------------------------------------------------------------------+");
        
        Statement st=con.createStatement();
        st.executeUpdate("insert into users (User_ID, Full_Name, User_Password)"
        		+ "value(' " +userID+" ' , ' " +fName+" ' , ' " +password+" ')");
		
        boolean quit = false;
       
        System.out.println("\nPlease make a selection: ");
        System.out.println("1.) Review Stock");
        System.out.println("2.) Add Stock");
        System.out.println("3.) Liquidate Stock");
        System.out.println("4.) Edit User Info");
        System.out.println("5.) Exit");
     
        while(!quit) {
            System.out.println("Now please Enter another choice from the main menu: ");
            int choice = input.nextInt();
            input.nextLine();
            switch(choice) {
            case 1:
                revStock();
                break;
            case 2:
                addStook("", "");
                break;
            case 3:
                liqui("", "");
                break;
            case 4:
                editUInf("");
                break;
            case 5:
                quit = true;
            }
         
        }
    

    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
    	
    }
    
    }
    public static void editUInf(String fName) throws SQLException {
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter Your Full Name To Replace The Old Name  ");
        fName = input.nextLine();
        info.add(fName);
        System.out.println("Now Please Enter your  UserID to verify the change: ");
        String userID = input.nextLine();
        info.add(userID);
        System.out.println("Your user Name has been updated to " + fName);
        PreparedStatement updateEXP = (PreparedStatement) con.prepareStatement("update`users` set `Full_Name` =  ' " +fName+" ' ''  where `User_ID` = ' " +userID+" '");
        int updateEXP_done = updateEXP.executeUpdate();

    }

    public static void addStook(String NameofAd, String numofadd) throws SQLException {
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the name of the stock that you would like to purchase follwed by the amount, When done please press Eneter!: ");
        NameofAd = input.nextLine();
        info.add(NameofAd);
        System.out.println("To Verify the purchase Please enter your User ID: ");
        String userID = input.nextLine();
        info.add(userID);
        PreparedStatement updateEXP = (PreparedStatement) con.prepareStatement("update`users` set `Stock` =  ' " +NameofAd+" ' ''  where `User_ID` = ' " +userID+" '");
        int updateEXP_done = updateEXP.executeUpdate();
    
        System.out.println("stock " + NameofAd + " has now been added to your portfolio. ");
        
    }

  

    public static void liqui(String NameofAd, String numofadd)  throws SQLException {
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmain", "student" , "student");
    	Statement st=con.createStatement();
        Scanner input = new Scanner(System.in);
        System.out.println("To Liquidate, Please Enter The Name and Ammount of Stock:");
        NameofAd = input.nextLine();
        info.add(NameofAd);
        System.out.println("To Verify Please enter your User ID: ");
        String userID = input.nextLine();
        info.add(userID);
        st= con.createStatement();
        String sql = "DELETE FROM users " +
                     "WHERE Stock  = ' " +NameofAd+" '";
        st.executeUpdate(sql);

    }

    public static void revStock() throws SQLException {

        
    	System.out.println("Stock List");

        for(int i = 0; i < info.size(); ++i) {
            System.out.println(i + 1 + "." + (String)info.get(i) + "->" + (String)info.get(i));
        }
 	 

    }
}
