package com.jdojo.media;

import com.jdojo.util.ResourceUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.net.URL;

public class QuickMediaPlayer extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        URL mediaUrl = ResourceUtil.getResourceURL("media/earth.mp4");
        String mediaStringUrl = mediaUrl.toExternalForm();
        Media media = new Media(mediaStringUrl);
        MediaPlayer player = new MediaPlayer(media);
        player.setAutoPlay(true);
        MediaView mediaView = new MediaView(player);
        mediaView.setFitHeight(300);
        mediaView.setFitWidth(400);
        Button playBtn = new Button("Play");
        playBtn.setOnAction(e -> {
            if(player.getStatus()==MediaPlayer.Status.PLAYING){
                player.stop();
                player.play();
            }else {
                player.play();
            }
        });
        Button stopBtn = new Button("Stop");
        stopBtn.setOnAction(e -> {
            player.stop();
        });
        player.setOnError(()->{
            System.out.println(player.getError().getMessage());
        });
        HBox controlBox = new HBox(5, playBtn, stopBtn);
        BorderPane root = new BorderPane();
        root.setCenter(mediaView);
        root.setBottom(controlBox);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Quick MediaPlayer");
        stage.show();
    }
}
