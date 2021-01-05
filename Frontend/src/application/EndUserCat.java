package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EndUserCat {

    public void contact(ActionEvent event) throws IOException
    {


        Parent root1 = FXMLLoader.load(getClass().getResource("EndUserSubCat.fxml"));
        Stage signUpStage = new Stage();
        signUpStage.setScene(new Scene(root1));
        signUpStage.show();
    }


}
