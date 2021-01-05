package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EndUserItem
{


    @FXML
    private static Label item1,item2,units1,units2,price1,price2;
    @FXML
    private static TextField qty1,qty2;


    public static void pressButton1(ActionEvent event) throws IOException
    {


    String itemName1=item1.getText();
    String itemName2=item2.getText();
    String unitsAvailable1=units1.getText();
    String unitsAvailable2=units2.getText();
    String qtyreq1=qty1.getText();
    String qtyreq2=qty2.getText();
    String priceOf1=price1.getText();
    String priceOf2=price2.getText();

//System.out.println(itemName1+itemName2+unitsAvailable1+" "+unitsAvailable2+qtyreq1+qtyreq2+priceOf1+priceOf2);


    }

    public void pressButton2(ActionEvent event) throws IOException

    {

        Parent root1 = FXMLLoader.load(getClass().getResource("Cart.fxml"));
        //Scene SignUpPage = new Scene(root1);
        //Node partAddButton = null;
        Stage signUpStage = new Stage();
        signUpStage.setScene(new Scene(root1));
        signUpStage.show();


    }






}
