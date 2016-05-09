package ExceptionHandling;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Customer_info {

	static Connection conn = null; 
	static Scanner scan = new Scanner(System.in);
	
	static String Name = null, Surname = null, Health = null, Medical_Aid = null, que = null;
	static int CustomerID = 0, Age = 0;
	
	
	
	
		
	public static void MainMenu()  {
		try{
		int men = 0;
		
		String what = JOptionPane.showInputDialog("Database for Customer: " + "\n1 To add record of user " + "\n2 to diplay record of user " + "\n3 to delete record of user ");
		
		men = Integer.parseInt(what);
		
		
		switch(men)
		{
		
		case 1:
			EnterData();
			break;
		case 2:
			DisplayData();
			break;
			
		case 3:
			DeleteData();
			break;
			default: System.out.println("You Wrong");
		}
		
		}catch(Exception e)
		{
		}
	}
	
	
	
	
	public static void con()throws SQLException, IOException{//Checked
		
		try{// try-catch block that alter the normal flow of programming
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/topic8","root","letmein");
			
			if(conn !=null)
			{
				JOptionPane.showMessageDialog(null, "Connected","Error",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}catch(NullPointerException e)//UnChecked
			{
			e.printStackTrace();
			
			}catch(ClassCastException e)// common exception classes
		
		
		{
			e.printStackTrace();
			

		}catch(SQLException e)
		{
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "System disconnected","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static void EnterData()throws SQLException //Create and Invoke method that throws exception
	
	{
	 
		try
		{		
				String name = null, surname = null, q = null, Htype, Mtype = null;
				String [] health = {"Good","Average","Bad"};
				String [] medical_aid = {"Gems","Discovery","Bonitas","None"};
				int age = 0, customerid = 0;
						
				CustomerID = customerid;
				Name = name;
				Surname = surname;
				Age = age;
				
				
				q = JOptionPane.showInputDialog("Please insert new Customer ID___ ");
				CustomerID = Integer.parseInt(q);
				
				Name = JOptionPane.showInputDialog("Please insert new Customer Name___ ");
				
				Surname = JOptionPane.showInputDialog("Please insert new Customer Surname___ ");
				
				q = JOptionPane.showInputDialog("Please insert new Customer Age___ ");
				Age = Integer.parseInt(q);
				
				
				Htype = JOptionPane.showInputDialog("Please select your health type:" + "\n1) Good " + "\n2) Average" + "\n3) Bad ");
			
				switch(Htype)
				
				{
				
				case "1":
					
					Health = health[0];
					break;
				case "2":
					
					Health = health[1];
					break;
				case "3":
					
					Health = health[2];
					break;
					
				case "4":
					
					Health = health[3];
				break;
					
				}
				
				
				Mtype = JOptionPane.showInputDialog("Please select your medical aid type:" + "\n1) Gems " + "\n2) Discovery" + "\n3) Bonitas " + "\n4) None");
						switch(Mtype)
						
						{
						
						case "1":
							Medical_Aid = medical_aid[0];
						break;
						case "2":
							Medical_Aid = medical_aid[1];
							break;
						case "3":
							Medical_Aid = medical_aid[2];
							break;
						case "4":
							Medical_Aid = medical_aid[3];
							break;
							
							default: 
								JOptionPane.showMessageDialog(null, "Invalid value","Error", JOptionPane.ERROR_MESSAGE);
								EnterData();
								break;
						
						}
			
						
				
			
		} 
		
		catch(NullPointerException e)// commom unchecked exceptions
		
		{
			
		}
		
		catch(ArrayIndexOutOfBoundsException e)//common Excpetions
		
		{
			e.printStackTrace();
			
		} 
		
		catch(Exception e)
	
		{
			System.out.println("System not connected");
		}finally
		
		
		{
			ins();
		}
		
		
	}
		
	public static void ins()throws SQLException //Create and Invoke method that throws exception
	{
		try{
			
			con();
	
		que = "INSERT INTO customer_info (CustomerID, Name, Surname, Age, Health, Medical_Aid)VALUES(?,?,?,?,?,?)";
		
		PreparedStatement state = conn.prepareStatement(que);
		
		
			state.setInt(1, CustomerID);
		 
			
		state.setString(2, Name);
		state.setString(3, Surname);
		state.setInt(4, Age);
		state.setString(5, Health);
		state.setString(6, Medical_Aid);
		
		state.executeUpdate();
		conn.close();
		System.out.println("System Updated");
		
	
}catch(Exception e)
	{
	 System.out.println("Eish");
	}
}


 public static void DeleteData()throws SQLException, IOException //Create and Invoke method that throws exception
 
 {

	 con(); 
	 
	 int customerid = 0;
		String q = null;
		CustomerID = customerid;
		
		q = JOptionPane.showInputDialog("Please insert ID of record you wish to delete__ ");
		CustomerID = Integer.parseInt(q);
		que = "DELETE FROM customer_info WHERE CustomerID = '" + CustomerID + "'  ";
		PreparedStatement state = conn.prepareStatement(que);
		
		state.executeUpdate();
		
		JOptionPane.showMessageDialog(null, "Record Succcessfully Deleted");
 
 }
 
 public static void DisplayData()throws SQLException, IOException
 
 {
	con(); 
	
	String name = null, surname = null, q = null, Htype = null, Mtype = null;
	int age = 0, customerid = 0;
			
	CustomerID = customerid;
	Name = name;
	Surname = surname;
	Age = age;
	Health = Htype;
	Medical_Aid = Mtype;
	
	 PreparedStatement state = conn.prepareStatement("SELECT * from customer_info");
	 ResultSet rs = state.executeQuery();
	 
	 while(rs.next())
	 {
		 CustomerID = rs.getInt("CustomerID");
		 Name = rs.getString("Name");
		 Surname = rs.getString("Surname");
		 Age = rs.getInt("Age");
		 Health = rs.getString("Health");
		 Medical_Aid = rs.getString("Medical_Aid");
		 
		 
		 System.out.println("==== Customer Information ====" +
				 			"\n Customer id : " + CustomerID 
				 			+"\n Name : " + Name
				 			+"\n Surname : " + Surname
				 			+"\n Age : " + Age 
				 			+ "\n Health : " + Health
				 			+"\n Medical Aid : " + Medical_Aid
				 			+"\n ===============================");
	
 }
	
	
 }
}
