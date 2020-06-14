package sample;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.nio.file.Paths;

public class MusicPlayer {
    public static void play(){
        String path = "D:\\Programming\\JAVA\\PONG\\src\\sample\\test.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        AudioClip audioClip = new AudioClip(media.getSource());
        audioClip.setVolume(0.20);
        audioClip.setRate(1);
        audioClip.setCycleCount(AudioClip.INDEFINITE);
        audioClip.play();
    }
}