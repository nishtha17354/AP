package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class EndUser1
{
	@FXML
	private MenuItem menuItem1,menuItem2,menuItem3;
	@FXML
	private MenuButton m,k;

	@FXML
	private MenuItem drop1,drop2,drop3;
	public void pressButton1(ActionEvent event) throws IOException
	{
		//System.out.println("I ak ");
		String x=((MenuItem)event.getSource()).getText();
		System.out.println(x);
		m.setText(x);

	}

	public void pressButton2(ActionEvent event) throws IOException
	{
		if(((MenuItem)event.getSource()).getText().equals("Log Out")) {

			Parent root1 = FXMLLoader.load(getClass().getResource("MainScreen1.fxml"));
			Stage signUpStage = new Stage();
			signUpStage.setScene(new Scene(root1));
			signUpStage.show();
		}

	}

	public void pressButton3(ActionEvent event) throws IOException
	{

			Parent root1 = FXMLLoader.load(getClass().getResource("EndUserCat.fxml"));
			Stage signUpStage = new Stage();
			signUpStage.setScene(new Scene(root1));
			signUpStage.show();


	}



}
