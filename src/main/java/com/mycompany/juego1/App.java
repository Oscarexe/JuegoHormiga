package com.mycompany.juego1;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
// Lógica del juego:
//Habrá un número determinado de hormigas que siempre será el mismo
//Se usará las posiciones negativas para jugar gracias a que no se verá
//Cuando una hormiga sea aplastada, viajará a una parte aleatoria de la superficie
//que no se ve, sumando un punto y volviendo a la zona de juego


public class App extends Application {
    int ladoImagen= 256;
    short Hormiga1X = 0;
    byte Hormiga1SpeedX = 3;
    byte Hormiga1DirectionX = 1;
    
    short Hormiga1Y = 0;
    byte Hormiga1SpeedY = 3;
    byte Hormiga1DirectionY = 1;
// for (int i = 0; i < 5; ++i) {
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        var scene = new Scene (root, 640, 480);
        stage.setScene(scene);
        stage.show();
        
        Image fondo = new Image(getClass().getResourceAsStream("/Images/background.png"));
        Image hormiga1 = new Image(getClass().getResourceAsStream("/Images/walk.png"));
        //Image hormiga2 = new Image(getClass().getResourceAsStream("/Images/walk.png"));
        
        ImageView  imageView1 = new ImageView (fondo);
        ImageView  imageView2 = new ImageView (fondo);
        ImageView  imageView3 = new ImageView (fondo);
        ImageView  imageView4 = new ImageView (fondo);
        ImageView  imageView5 = new ImageView (fondo);
        ImageView  imageView6 = new ImageView (fondo);
        ImageView  Hormiga1 = new ImageView (hormiga1);
        root.getChildren().add(imageView1);
        root.getChildren().add(imageView2);
        root.getChildren().add(imageView3);
        root.getChildren().add(imageView4);
        root.getChildren().add(imageView5);
        root.getChildren().add(imageView6);
        root.getChildren().add(Hormiga1);
        
        imageView1.setX(ladoImagen);
        imageView2.setX(2* ladoImagen);
        imageView3.setY(ladoImagen);
        imageView4.setX(ladoImagen);
        imageView4.setY(ladoImagen);
        imageView6.setY(ladoImagen);
        imageView6.setX(2*ladoImagen);
        Hormiga1.setX(2*ladoImagen);
        Hormiga1.setFitWidth(50);
        Hormiga1.setFitHeight(58);
        
        Timeline timeline;
        timeline = new Timeline(
            // 0.017 ~= 60 FPS
           new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ae) { 
                    //ANIMACION DE LA HORMIGA
                    Hormiga1.setY(Hormiga1Y);
                    Hormiga1.setX(Hormiga1X);
                    Hormiga1X +=Hormiga1SpeedX * Hormiga1DirectionX;
                    Hormiga1Y +=Hormiga1SpeedY * Hormiga1DirectionY;
                    
                    

                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

}

//Cosas por hacer: 
// - Hacer que la hormiga se diriga hacia la zona de juego cuando se mate
// - Delimitar la zona
// - Contador de puntos
// - Variedad de dificultad
// - Cuando se pierde?