
package main;

import java.sql.Connection;


import menu.Menu;
import util.DBConnection;

public class MainModule {
    public static void main(String[] args) {
    	
    	System.out.println("Hello");
    	
    	Connection conn=  DBConnection.getConnection();
    	
    	Menu.menu();
       
    }
}
