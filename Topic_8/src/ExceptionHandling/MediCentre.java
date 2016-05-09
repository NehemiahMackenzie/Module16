package ExceptionHandling;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;


/*
 * @Author Nehemiah MacKeznie  
 * 
 * Topic 8: Handling Exceptions
 * 
 * objectives
 * - Differentiate between checked exceptions, unchecked exception and errors
 * - Create Try-catch block and determine how exceptions alter normal program flow
 * - Describe advantages of exceptions
 * - Create and Invoke method that throws exception
 * - Recognize common exception classes (NullPointerException, ArithmeticExcetoption, ArrayIndexOutOfBoundException)   
*/

public class MediCentre {

	public static void main(String[] args) throws SQLException, NullPointerException, ClassNotFoundException, IOException{
		
		try {// try-catch block that alter the normal flow of programming

			int men = 0;
			JOptionPane.showMessageDialog(null, "welcome to MEDICENTER!!!");
			String what = JOptionPane.showInputDialog("Select what type of action you want please: " + "\n1 Customer " + "\n2 Medicine");
			
			men = Integer.parseInt(what);
			
			
			switch(men)
			{
			
			case 1:
				
				Customer_info.MainMenu();
				
				break;
			case 2:
				Medicine.MainMenu();
				break;
			
				default: System.out.println("You Wrong");
			}
			
		} catch(NullPointerException e)
		{
			e.printStackTrace();
			
		}catch(NoClassDefFoundError e)
		{
			//Error
		}
		
	} 
	
}
