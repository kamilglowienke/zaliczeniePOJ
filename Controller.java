package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static java.lang.System.exit;

public class Controller {

    public void wyjdzZGry(ActionEvent wyjdz) throws Exception {

        exit(0);
    }

    public void Opis(ActionEvent opis) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("OpisGry.fxml"));
        Scene event1 = new Scene(parent);
        Stage event2 = (Stage) ((Node) opis.getSource()).getScene().getWindow();

        event2.setScene(event1);
        event2.show();
    }

    public void poziomyTrudnosci(ActionEvent opis) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("PoziomyTrudnosci.fxml"));
        Scene event1 = new Scene(parent);
        Stage event2 = (Stage) ((Node) opis.getSource()).getScene().getWindow();

        event2.setScene(event1);
        event2.show();
    }

}
