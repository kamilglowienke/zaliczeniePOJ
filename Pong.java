package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.*;
import java.io.File;


public class Pong {

    //zmienne
    private static final int szerokoscOkna = 800;
    private static final int wysokoscOkna = 600;
    public static int wysokoscGracza = 80;
    private static final int szerokoscGracza = 15;
    private static final double bokPilki = 15;
    private int predkoscPilkiY = 1;
    private int predkoscPilkiX = 1;
    private double pozycjaGraczaY = wysokoscOkna / 2;
    private double pozycjaBotaY = wysokoscOkna / 2;
    private double pozycjaPilkiX = szerokoscOkna / 2;
    private double pozycjaPilkiY = wysokoscOkna / 2;
    private int wynikGracza = 0;
    private int wynikBota = 0;
    private boolean graTrwa;
    private int pozycjaGraczaX = 0;
    private double pozycjaBotaX = szerokoscOkna - szerokoscGracza;

    public void start(Stage stage) {
        stage.setTitle("Projekt s21683 - Pong");
        //tworzenie okna gry
        Canvas canvas = new Canvas(szerokoscOkna, wysokoscOkna);
        GraphicsContext grafika = canvas.getGraphicsContext2D();

        //ta linia jest od animacji. nie wiem jak dokładnie działa - wziąłem ją z internetu
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(grafika)));

        //animacje się nie kończą i można grać bez zawieszek
        tl.setCycleCount(Timeline.INDEFINITE);

        //kontrolki
        canvas.setOnMouseMoved(e -> pozycjaGraczaY = e.getY());
        canvas.setOnMouseClicked(e -> graTrwa = true);

        
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
        tl.play();

    }


    private void run(GraphicsContext gc) {
        //grafika
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, szerokoscOkna, wysokoscOkna);

        //tekst
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        //wynik
        gc.fillText(wynikGracza + "\t\t\t\t\t\t\t\t" + wynikBota, szerokoscOkna / 2, 100);

        //gracz i bot
        gc.fillRect(pozycjaBotaX, pozycjaBotaY, szerokoscGracza, wysokoscGracza);
        gc.fillRect(pozycjaGraczaX, pozycjaGraczaY, szerokoscGracza, wysokoscGracza);

        if (graTrwa) {
            //tworze pilke
            gc.fillRect(pozycjaPilkiX, pozycjaPilkiY, bokPilki, bokPilki);

            //ruch pilki
            pozycjaPilkiX += predkoscPilkiX;
            pozycjaPilkiY += predkoscPilkiY;

            //bot followujący piłkę
            if (pozycjaPilkiX < szerokoscOkna - szerokoscOkna / 4) {
                pozycjaBotaY = pozycjaPilkiY - wysokoscGracza / 2;
            } else {
                pozycjaBotaY = pozycjaPilkiY > pozycjaBotaY + wysokoscGracza / 2 ? pozycjaBotaY += 1 : pozycjaBotaY - 1;
            }
        } else {
            //tekst
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Kliknij aby rozpocząć", szerokoscOkna / 2, wysokoscOkna / 2);

            //reset pozycji piłki
            pozycjaPilkiX = szerokoscOkna / 2;
            pozycjaPilkiY = wysokoscOkna / 2;

            //reset kierunku i prędkości piłki
            predkoscPilkiX = new Random().nextInt(2) == 0 ? 1 : -1;
            predkoscPilkiY = new Random().nextInt(2) == 0 ? 1 : -1;
        }

        //upewniam się, że piłka nie opuszcza okna
        if (pozycjaPilkiY > wysokoscOkna || pozycjaPilkiY < 0) predkoscPilkiY *= -1;

        //przydzielenie punktu dla bota
        if (pozycjaPilkiX < pozycjaGraczaX - szerokoscGracza) {
            wynikBota++;
            graTrwa = false;
        }

        //i dla gracza
        if (pozycjaPilkiX > pozycjaBotaX + szerokoscGracza) {
            wynikGracza++;
            graTrwa = false;
        }

        //przyśpieszenie piłki po jej uderzeniu
        if (((pozycjaPilkiX + bokPilki > pozycjaBotaX) && pozycjaPilkiY >= pozycjaBotaY && pozycjaPilkiY <= pozycjaBotaY + wysokoscGracza) ||
                ((pozycjaPilkiX < pozycjaGraczaX + szerokoscGracza) && pozycjaPilkiY >= pozycjaGraczaY && pozycjaPilkiY <= pozycjaGraczaY + wysokoscGracza)) {
            predkoscPilkiY += 1 * Math.signum(predkoscPilkiY);
            predkoscPilkiX += 1 * Math.signum(predkoscPilkiX);
            int los = new Random().nextInt(2) == 0 ? 1 : -1;
            predkoscPilkiY *= los * 1;
            predkoscPilkiX *= -1;
        }
    }
}