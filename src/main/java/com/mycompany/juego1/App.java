package com.mycompany.juego1;

import java.util.Random;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    short hormigaX[] = new short[5];
    byte hormigaSpeedX[]= new byte [5];
    byte hormigaDirectionX[] = new byte [5];
    short hormigaY[] = new short[5];
    byte hormigaSpeedY[]= new byte [5];
    byte hormigaDirectionY[] = new byte [5];
            

    @Override
    public void start(Stage stage) {

        //POSICION
        //hormiga 0
        hormigaX[0] = 0;
        hormigaY[1] = 0;
        
        // VELOCIDAD
        //hormiga 0
        hormigaSpeedX[0]= 3;
        hormigaSpeedY[0]= 3;
        
        
        // DIRECCION
        //hormiga 0
        hormigaDirectionX[0]= 1; 
        hormigaDirectionY[0]= 1; 

        Pane root = new Pane();
        var scene = new Scene (root, 640, 480);
        stage.setScene(scene);
        stage.show();
        
        Image fondo = new Image(getClass().getResourceAsStream("/Images/background.png"));
        Image imgHormiga0 = new Image(getClass().getResourceAsStream("/Images/walk.png"));
        Image imgHormiga2 = new Image(getClass().getResourceAsStream("/Images/walkroja.png"));
        
        ImageView  imageView1 = new ImageView (fondo);
        ImageView  imageView2 = new ImageView (fondo);
        ImageView  imageView3 = new ImageView (fondo);
        ImageView  imageView4 = new ImageView (fondo);
        ImageView  imageView5 = new ImageView (fondo);
        ImageView  imageView6 = new ImageView (fondo);
        
        ImageView imgViewHormiga[] = new ImageView[3];
        imgViewHormiga[0] = new ImageView (imgHormiga0);
        imgViewHormiga[2] = new ImageView (imgHormiga2);
        
        
        root.getChildren().add(imageView1);
        root.getChildren().add(imageView2);
        root.getChildren().add(imageView3);
        root.getChildren().add(imageView4);
        root.getChildren().add(imageView5);
        root.getChildren().add(imageView6);
        
        root.getChildren().add(imgViewHormiga[0]);
        root.getChildren().add(imgViewHormiga[2]);
        
        imageView1.setX(ladoImagen);
        imageView2.setX(2* ladoImagen);
        imageView3.setY(ladoImagen);
        imageView4.setX(ladoImagen);
        imageView4.setY(ladoImagen);
        imageView6.setY(ladoImagen);
        imageView6.setX(2*ladoImagen);
        imgViewHormiga[0].setX(2*ladoImagen);
        imgViewHormiga[0].setFitWidth(50);
        imgViewHormiga[0].setFitHeight(58);
        imgViewHormiga[2].setFitWidth(60);
        imgViewHormiga[2].setFitHeight(70);
        
        Timeline timeline;
        timeline = new Timeline(
            // 0.017 ~= 60 FPS
           new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ae) { 
                    //ANIMACION DE LA HORMIGA
                    imgViewHormiga[0].setY(hormigaY[0]);
                    imgViewHormiga[0].setX(hormigaX[0]);
                    hormigaX[0] +=hormigaSpeedX[0] * hormigaDirectionX[0];
                    hormigaY[0] +=hormigaSpeedY[0] * hormigaDirectionY[0];
                    
                    

                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
// Pulsaciones del ratón       
    scene.setOnMouseClicked(
            new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent e)
                {
                    if ( imgViewHormiga[0].contains( e.getX(), e.getY() ) ){
                        imgViewHormiga[0].setX(-50);
                        imgViewHormiga[0].setY(30);
                        System.out.println("Le doy");
                        Random random = new Random();
                        int numero = random.nextInt(3);
                        System.out.println(numero);
                        // meter sonido y sangre

                    }
                    if ( imgViewHormiga[2].contains( e.getX(), e.getY() ) ){
                        System.out.println("Le doy");
                        imgViewHormiga[2].setX(-50);
                        imgViewHormiga[2].setY(30);
                        Random random = new Random();
                        int numero = random.nextInt(4);
                        System.out.println(numero);
                        switch(numero) {
                            case 0:
                              // mandar la hormiga a un punto aleatorio entre 0 y 640 de la Y
                              // mandar la hormiga a un punto negativo de la X
                              int position = random.nextInt(640);  
                              System.out.println(position);
                              
                              break;
                            case 1:
                              // mandar la hormiga a un punto aleatorio entre 0 y 640 de la Y 
                              break;
                            
                            case 2:
                              // code block
                              break;
                             
                            case 3:
                              // code block
                              break;
                            default:
                              // code block
                        }
                        // meter sonido y sangre

                    }
                }
            });
    }

    public static void main(String[] args) {
        launch();
    }   
}

// Lógica del juego:
//Habrá un número determinado de hormigas que siempre será el mismo
//Se usará las posiciones negativas para jugar gracias a que no se verá
//Cuando una hormiga sea aplastada, viajará a una parte aleatoria de la superficie
//que no se ve, sumando un punto y volviendo a la zona de juego

//Cosas por hacer: 
// - Detectar pulsaciones de raton
// - Hacer que la hormiga se diriga hacia la zona de juego cuando se mate
// * Cuando la hormiga muera se generará un numero aleatorio del 0 al 3 y cada uno 
//   sera un borde de la pantalla
// - Contador de puntos
// - Variedad de dificultad
// - Cuando se pierde?
// - Cuando una hormiga se muere, se genera un numero aleatorio 
//-  Segun el numero hay que hacer un switch con 4 casos: en los que cada uno de los casos sea 
//   un lado de la pantalla al que se teletransporta la hormiga.
