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

public class PoziomyController {
    public void Powrot1(ActionEvent powrot) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene opisGry = new Scene(parent);
        Stage opis = (Stage) ((Node)powrot.getSource()).getScene().getWindow();

        opis.setScene(opisGry);
        opis.show();
    }

    public void GrajLatwy(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("gra.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene xd = new Scene(parent);
        Pong graPong = new Pong ();
        Pong.wysokoscGracza = 90;
        graPong.start(window);

    }

    public void GrajTrudny(ActionEvent event) throws Exception {
        Parent AktualnaGra = FXMLLoader.load(getClass().getResource("gra.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene xd = new Scene(AktualnaGra);
        Pong graPong = new Pong ();
        Pong.wysokoscGracza = 50;
        graPong.start(window);
    }
}

