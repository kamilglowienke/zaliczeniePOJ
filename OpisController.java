package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class OpisController {
            public void Powrot(ActionEvent powrot) throws Exception {
                Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Scene opisGry = new Scene(parent);
                Stage opis = (Stage) ((Node)powrot.getSource()).getScene().getWindow();

                opis.setScene(opisGry);
                opis.show();
        }
    }

