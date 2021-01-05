package application;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Signup {
	
	@FXML
	private PasswordField password,confirmP;
	@FXML
	private TextField username;
	@FXML
	private MenuItem menuItem1,menuItem2,menuItem3;
	@FXML
	private MenuButton m;
	
	public void pressButton1(ActionEvent event) throws IOException
	
	{
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

	    
	    		for(int i=0;i<arrli2D.size();i++) {
	    			String k=arrli2D.get(i).get(0);
	    			if(username.getText().equals(k)) {
	    				f = 1;
	    				break;
	    				
	    			}
	    		}
	
		
		
		
		if(!password.getText().equals(confirmP.getText())) 
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Password doesnt match, Kindky re-enter. ");
			alert.showAndWait();
		}
		else if (username.getText().isEmpty()||password.getText().isEmpty()||confirmP.getText().isEmpty())
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please fill all required fields. ");
			alert.showAndWait();
		}
		else if(f == 1)
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("UsenameAlready exists ");
			alert.showAndWait();
		}	
		
	
	}
	 
	
	
	
        public void handle(ActionEvent e) throws IOException 
        { 
            String x=((MenuItem)e.getSource()).getText(); 
           // System.out.println(x);
            m.setText(x);
            
            if (password.getText().equals(confirmP.getText()))
    		{
    			
    			try 
    			{
    				 File file = new File("/Users/nishthaahuja/Desktop/users.txt");
    				 if (!file.exists()) {
    			            file.createNewFile();
    			        }
    				   FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
    			        BufferedWriter bw = new BufferedWriter(fw);
    			        bw.write(username.getText()+","+password.getText()+","+x+"\n");
    			        bw.close();

    			}
    			catch(IOException e1)
    			{
    				 e1.printStackTrace();
    			}

    			Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setHeaderText(null);
    			alert.setContentText("Successfully signed up. ");
    			alert.showAndWait();
    		}
            
           
              
        } 
     
	public void pressButton3(ActionEvent event) throws IOException 
	{
		 
 	Parent root1 = FXMLLoader.load(getClass().getResource("MainScreen1.fxml"));
 	Stage signUpStage = new Stage();
	signUpStage.setScene(new Scene(root1));
	signUpStage.show();
	}
}
