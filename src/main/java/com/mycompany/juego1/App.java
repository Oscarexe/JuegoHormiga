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
    
    Image imgHormiga0 = new Image(getClass().getResourceAsStream("/Images/walk.png"));
    Image imgHormiga2 = new Image(getClass().getResourceAsStream("/Images/walkroja.png"));

    ImageView imgViewHormiga[] = new ImageView[3];
    
    
  
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
        
        // PANTALLA
        final int width= 640;
        final int height= 480;

        Pane root = new Pane();
        var scene = new Scene (root, width, height);
        stage.setScene(scene);
        stage.show();
        
        Image fondo = new Image(getClass().getResourceAsStream("/Images/background.png"));
        
        Image blood = new Image(getClass().getResourceAsStream("/Images/blood.png"));
        ImageView imageView7 = new ImageView (blood);

        ImageView  imageView1 = new ImageView (fondo);
        ImageView  imageView2 = new ImageView (fondo);
        ImageView  imageView3 = new ImageView (fondo);
        ImageView  imageView4 = new ImageView (fondo);
        ImageView  imageView5 = new ImageView (fondo);
        ImageView  imageView6 = new ImageView (fondo);
        
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
           new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
               //ANIMACION DE LA HORMIGA
               imgViewHormiga[0].setY(hormigaY[0]);
               imgViewHormiga[0].setX(hormigaX[0]);
               hormigaX[0] +=hormigaSpeedX[0] * hormigaDirectionX[0];
               hormigaY[0] +=hormigaSpeedY[0] * hormigaDirectionY[0];
        })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
// Pulsaciones del ratón       
        scene.setOnMouseClicked((MouseEvent e) -> {
                if ( imgViewHormiga[0].contains( e.getX(), e.getY() ) ){
                    // asignar un numero a numeroHormiga
                    System.out.println("le doy");
                    int numeroHormiga= 0;
                    //recolocarHormiga(numeroHormiga)
                    recolocarHormiga(numeroHormiga);
                }        

                // me quedo aqui    
                else { if ( imgViewHormiga[2].contains( i.getX(), i.getY() ) ){
                    // asignar un numero a numeroHormiga
                    System.out.println("le doy");
                    int numeroHormiga= 2;
                    //recolocarHormiga(numeroHormiga)
                    recolocarHormiga(numeroHormiga);
                }
        });
            
            
            
    }
    



//METODO RECOLOCAR HORMIGA
    private void recolocarHormiga(int numeroHormiga){
    //            int numeroHormiga;
        Random random = new Random();
        int numero = random.nextInt(4);
        System.out.println(numero);
        switch(numero) {
            case 0:
                // izquierda
                // mandar la hormiga a un punto aleatorio entre 0 y 640 de la Y
                // mandar la hormiga a un punto negativo de la X
                // meter sonido y sangre
                int caso0PositionYImg2 = random.nextInt(640);
                int caso0PositionXImg2 = -100 ;
                imgViewHormiga[numeroHormiga].setX(caso0PositionXImg2);
                imgViewHormiga[numeroHormiga].setY(caso0PositionYImg2);

                break;
            case 1:
                // mandar la hormiga a un punto aleatorio entre 0 y 640 de la Y
                // la X vale mas de 480 para que salga
                int caso1PositionYImg2 = random.nextInt(640);
                int caso1PositionXImg2 = 600 ;
                imgViewHormiga[numeroHormiga].setX(caso1PositionXImg2);
                imgViewHormiga[numeroHormiga].setY(caso1PositionYImg2);
                break;

            case 2:
                // mandarla a un punto aleatorio de la X entre 0 y 480 
                // Y negativa 
                int caso2PositionXImg2 = random.nextInt(480);
                int caso2PositionYImg2 = -100 ;
                imgViewHormiga[numeroHormiga].setX(caso2PositionXImg2);
                imgViewHormiga[numeroHormiga].setY(caso2PositionYImg2);
                break;

            case 3:
                // mandarla a una posicion aleatoria de X entre 0 y 480
                // mandarla a una posicion negativa de Y
                int caso3PositionXImg2 = random.nextInt(480);
                int caso3PositionYImg2 = -100 ;
                imgViewHormiga[numeroHormiga].setX(caso3PositionXImg2);
                imgViewHormiga[numeroHormiga].setY(caso3PositionYImg2);
                break;
            default:
                // code block
        }
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
//-  Moneda cada cierto tiempo con geometria

   