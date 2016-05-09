package ExceptionHandling;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class Medicine {
	
	static Connection conn = null;
	static Scanner input = new Scanner(System.in);
	
	static String Medicine_Title = null, que = null;
	static int MedicineID = 0, QTY = 0; 
	static double Price = 0;
	
	
	
	
	
	public static void MainMenu()  {
		try{
		int men = 0;
		
		String what = JOptionPane.showInputDialog("Database for Medicine: " + "\n1 To add record of Medicine " + "\n2 to diplay record of Medicine " + "\n3 to delete record of Medicine ");
		
		men = Integer.parseInt(what);
		
		
		switch(men)
		{
		
		case 1:
			InsertData();
			break;
		case 2:
			DisplayData();
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
			System.out.println("connected");
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
	}
	}

 public static void InsertData() throws SQLException//Checked

 
 {
 
	 	try {// try-catch block that alter the normal flow of programming
	 		
	 		Class.forName("com.mysql.jdbc.Driver");
	 		conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/topic8","root","letmein");
	 		
	 		if(conn != null)
	 		
	 		{
			 			System.out.println("Connected");
			 			 
			 				//Local Variables
				 			 String medcine_Title = null, q = null;
				 			 int medicineID = 0, qty = 0; 
				 			 double price = 0;
				 			 
				 			 MedicineID = medicineID;
				 			 Medicine_Title = medcine_Title;
				 			 QTY = qty;
				 			 Price = price;
				 			 
				 			 q = JOptionPane.showInputDialog("Please Insert new Medicine ID___ ");
				 			 MedicineID = Integer.parseInt(q);
				 			 
				 			 Medicine_Title = JOptionPane.showInputDialog("Please insert the type of Medicine___ ");
				 			 
				 			 q = JOptionPane.showInputDialog("Insert the number of medicine type___ ");
				 			 QTY = Integer.parseInt(q);
				 			 
				 			 q = JOptionPane.showInputDialog("Please insert Price of Medicine");
				 			 Price = Double.parseDouble(q);
			 			 
				 			 
			 		que = "INSERT INTO medicine_info (MedicineID, Medi_Title, QTY, Price)VALUES(?,?,?,?)";
			 		PreparedStatement state = conn.prepareStatement(que);
			 		
			 					 		
					 		state.setInt(1,MedicineID );
					 		state.setString(2, Medicine_Title);
					 		state.setInt(3, QTY);
					 		state.setDouble(4, Price);
					 		
					 		state.executeUpdate();
					 	    
					 		System.out.println("System Updated");	
	 		
	 		}
	 		
	 		
	 	}catch(NullPointerException e)
	 	
	 	{
	 		e.printStackTrace();
	 	
	 	}catch(NumberFormatException e)
	 	
	 	{
	 		
	 		e.printStackTrace();
	 	
	 	}catch(Exception e)
	 	
	 	{
	 		System.out.println("System not connected");
	 	
	 	}finally
	 	{
	 		conn.close();
	 	}
	 
 }
 
 
 
 public static void DeleteData()throws SQLException, IOException//in

 {
	 try{
	 conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/topic8","root","letmein");
   
   		if(conn != null)
   			
   		{
   			JOptionPane.showMessageDialog(null, "Connected","Error",JOptionPane.INFORMATION_MESSAGE);
   			String q = null;
   			int medicineID = 0;
   			
   			MedicineID = medicineID;
   			
   			q = JOptionPane.showInputDialog("Please insert the MedicineID of record you wish to delete___ ");
   			MedicineID = Integer.parseInt(q);
   			
   			que = "DELETE FROM medicine_info WHERE MedicineID = '" + MedicineID +"' ";
   			PreparedStatement state = conn.prepareStatement(que);
   			state.executeUpdate();
   			
   			System.out.println("Record Deleted");
   		}
 
	}catch(NullPointerException e)
	 
	 {
		System.out.println("Null Pointer");
	 
	 }catch(NumberFormatException e)
	 
	 {
		 System.out.println("Should be a number");
		 
	 }catch(Exception e)
	 {
		 System.out.println("System disconnected");
	 }finally
	 {
		 conn.close();
	 }
 }
 
 public static void DisplayData()throws SQLException, IOException
 
 {
	 conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/topic8","root","letmein");
	 if(conn != null)
	 {
		 JOptionPane.showMessageDialog(null, "Connected","Error",JOptionPane.INFORMATION_MESSAGE);
		 
		 String medcine_Title = null, q = null;
			 int medicineID = 0, qty = 0; 
			 double price = 0;
			 
			 MedicineID = medicineID;
			 Medicine_Title = medcine_Title;
			 QTY = qty;
			 Price = price;
			 
			 PreparedStatement state = conn.prepareStatement("SELECT * from medicine_info");
			 ResultSet rs = state.executeQuery();
			 
			 while(rs.next())
			 {
				 MedicineID = rs.getInt("MedicineID");
				 Medicine_Title = rs.getString("Medi_Title");
				 QTY = rs.getInt("QTY");
				 Price = rs.getDouble("Price");
				 
				 System.out.println("==== Medicine Information ====" +
						 			"\n Medicine ID : " + MedicineID 
						 			+"\n Medicine Title : " + Medicine_Title
						 			+"\n Quantity : " + QTY
						 			+"\n Price R : " + Price +
						 			"\n ===============================");
			 }
	 }
 }
 
}

	


