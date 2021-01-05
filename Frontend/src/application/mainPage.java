package application;
import java.util.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.io.File; 
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.File; 
import java.io.FileNotFoundException; 
public class mainPage {

	
	@FXML
	private PasswordField password;
	@FXML
	private TextField username;
	String passwrordText;
	String textfieldText;
	
	public void pressButton1(ActionEvent event) throws IOException
	{
		
		
		passwrordText=password.getText();
		textfieldText=username.getText();
		
		int f = 0;
		File file1 = new File("/Users/nishthaahuja/Desktop/users.txt");
 	    Scanner sc = new Scanner(file1); 
 	   ArrayList<ArrayList<String> >arrli2D = new  ArrayList<ArrayList<String>>();
	   // sc.useDelimiter("\n");
	    String[] values = new String[100];
	    int ctr = 0;
	    while(sc.hasNext())
	    {
	    		String temp = sc.next();
	    		ArrayList<String> arr = new ArrayList<String>();
	    		//System.out.println(temp);
	    		String s1 = "";
	    		String s2 = "";
	    		String s3 = "";
	    		int index = 0;
	    		while(temp.charAt(index) != ',')
	    		{
	    			s1 += temp.charAt(index);
	    			index++;
	    		}
	    		index++;
	    		while(temp.charAt(index) != ',')
	    		{
	    			s2 += temp.charAt(index);
	    			index++;
	    		}
	    		index++;
	    		while(index < temp.length())
	    		{
	    			s3 += temp.charAt(index);
	    			index++;
	    		}
	    		arr.add(s1);
	    		arr.add(s2);
	    		arr.add(s3);
	    		arrli2D.add(arr);
	    }	
	    
//	    for(int i=0;i<arrli2D.size();i++)
//		{
//	    	System.out.println(arrli2D.get(i).get(0));
//		}
	    
	    int flag=0;
		for(int i=0;i<arrli2D.size();i++)
		{
			//System.out.println(arrli2D.get(i));
				if(textfieldText.equals(arrli2D.get(i).get(0))&&passwrordText.equals(arrli2D.get(i).get(1))) 
				{
			
					if(arrli2D.get(i).get(2).equals("End-User")) 
					{
	
						Parent root1 = FXMLLoader.load(getClass().getResource("EndUser1.fxml"));
						Stage signUpStage = new Stage();
						signUpStage.setScene(new Scene(root1));
						signUpStage.show();	
					}
				   if(arrli2D.get(i).get(2).equals("WarehouseAdministrator")) 
					{
						Parent root1 = FXMLLoader.load(getClass().getResource("WarehouseAdmin.fxml"));
						Stage signUpStage = new Stage();
						signUpStage.setScene(new Scene(root1));
						signUpStage.show();
						
					}
					
					if(arrli2D.get(i).get(2).equals("StoreAdministrator")) 
					{
						Parent root1 = FXMLLoader.load(getClass().getResource("StoreAdmin.fxml"));
						Stage signUpStage = new Stage();
						signUpStage.setScene(new Scene(root1));
						signUpStage.show();
						
					}
				}
		
				
			else if(!textfieldText.equals(arrli2D.get(i).get(0))||!passwrordText.equals(arrli2D.get(i).get(1))) 
			{
				flag=1;
			}
			
		}
		
		if(flag==1)
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Password/ID entered is incorrect.");
			alert.showAndWait();
		}
		else if(flag==0)
		{
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("Successfully signed in. ");
			alert.showAndWait();
		}
		   
		   
	}
	
	


	public void pressButton3(ActionEvent event) throws IOException
	{
	
			Parent root1 = FXMLLoader.load(getClass().getResource("Signup1.fxml"));
			//Scene SignUpPage = new Scene(root1);
			//Node partAddButton = null;
			Stage signUpStage = new Stage();
			signUpStage.setScene(new Scene(root1));
			signUpStage.show();
	}
	
	public void contact(ActionEvent event) throws IOException 
	{
	
			Parent root1 = FXMLLoader.load(getClass().getResource("ContactUs.fxml"));
			//Scene SignUpPage = new Scene(root1);
			//Node partAddButton = null;
			Stage signUpStage = new Stage();
			signUpStage.setScene(new Scene(root1));
			signUpStage.show();
	}
	
	
}
