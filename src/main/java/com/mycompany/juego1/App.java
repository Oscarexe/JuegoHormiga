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
import static javafx.scene.paint.Color.YELLOW;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
// Lógica del juego:
//Meta: que las hormigas no toquen la galleta
//Habrá un número determinado de hormigas que siempre será el mismo
//Se usará las posiciones negativas para jugar gracias a que no se verá
//Cuando una hormiga sea aplastada, viajará a una parte aleatoria de la superficie
//que no se ve, sumando un punto y volviendo a la zona de juego
// Cuando una hormiga salga de la pantalla, se la volvera a recolocar


public class App extends Application {
    int ladoImagen= 256;
    // arrays para guardar las variables de las hormigas
    short hormigaX[] = new short[5];
    byte hormigaSpeedX[]= new byte [5];
    byte hormigaDirectionX[] = new byte [5];
    short hormigaY[] = new short[5];
    byte hormigaSpeedY[]= new byte [5];
    byte hormigaDirectionY[] = new byte [5];
    ImageView imgViewHormiga[] = new ImageView[3];
    // variables moneda
    int monedaX;
    int monedaY;     
    int monedaSpeedX;
    int monedaSpeedY;
    int monedaDirectionX;
    int monedaDirectionY;
    
    //IMAGENES
    Image imgHormiga0 = new Image(getClass().getResourceAsStream("/Images/walk.png"));
    Image imgHormiga2 = new Image(getClass().getResourceAsStream("/Images/walkroja.png")); 
    Image imgHormiga1 = new Image(getClass().getResourceAsStream("/Images/escarabajo.png"));   
    Image galleta = new Image(getClass().getResourceAsStream("/Images/cookie.png"));
    Image fondo = new Image(getClass().getResourceAsStream("/Images/background.png"));
    Image blood = new Image(getClass().getResourceAsStream("/Images/blood.png"));
    Circle moneda = new Circle(); 
    
    
  
    @Override
    public void start(Stage stage) {

            //POSICION
            //hormiga 0
            hormigaX[0] = 0;
            hormigaY[0] = 0;
            
            //hormiga 1
            hormigaX[1] = 0;
            hormigaY[1] = 0;
            
            //hormiga 2
            hormigaX[2] = 400;
            hormigaY[2] = 100;
            
            //moneda
            monedaX = 700;
            monedaY = 900;

            // VELOCIDAD
            //hormiga 0
            hormigaSpeedX[0]= 2;
            hormigaSpeedY[0]= 3;
            
            //hormiga 1
            hormigaSpeedX[1]= 2;
            hormigaSpeedY[1]= 3;
            
            //hormiga 2
            hormigaSpeedX[2]= 3;
            hormigaSpeedY[2]= 3;
            
            //moneda
            monedaSpeedX = 2;
            monedaSpeedY = 2;


            // DIRECCION
            //hormiga 0
            hormigaDirectionX[0]= 1; 
            hormigaDirectionY[0]= 1; 
            
            //hormiga 1
            hormigaDirectionX[1]= 1; 
            hormigaDirectionY[1]= 1;
            
            //hormiga 0
            hormigaDirectionX[2]= 1; 
            hormigaDirectionY[2]= 1; 
            
            //moneda
            monedaDirectionX = -1;
            monedaDirectionY = -1;
            
            //moneda         
            moneda.setRadius (15);
            moneda.setCenterX(400);
            moneda.setCenterY(300);
            moneda.setFill(YELLOW);

            // PANTALLA
            final int width= 640;
            final int height= 480;

            Pane root = new Pane();
            var scene = new Scene (root, width, height);
            stage.setScene(scene);
            stage.show();

            
            
            ImageView  imageView1 = new ImageView (fondo);
            ImageView  imageView2 = new ImageView (fondo);
            ImageView  imageView3 = new ImageView (fondo);
            ImageView  imageView4 = new ImageView (fondo);
            ImageView  imageView5 = new ImageView (fondo);
            ImageView  imageView6 = new ImageView (fondo);
            ImageView  imageView7 = new ImageView (blood);
            ImageView  imageView8 = new ImageView (galleta);
            
                        
            imgViewHormiga[0] = new ImageView (imgHormiga0);
            imgViewHormiga[1] = new ImageView (imgHormiga1);
            imgViewHormiga[2] = new ImageView (imgHormiga2);



            //Elementos pantalla
            root.getChildren().add(imageView1);
            root.getChildren().add(imageView2);
            root.getChildren().add(imageView3);
            root.getChildren().add(imageView4);
            root.getChildren().add(imageView5);
            root.getChildren().add(imageView6);
            root.getChildren().add(imageView7);
            root.getChildren().add(imageView8);
            root.getChildren().add(moneda);


            //Hormigas pantalla
            root.getChildren().add(imgViewHormiga[0]);
            root.getChildren().add(imgViewHormiga[1]);
            root.getChildren().add(imgViewHormiga[2]);

            imageView1.setX(ladoImagen);
            imageView2.setX(2* ladoImagen);
            imageView3.setY(ladoImagen);
            imageView4.setX(ladoImagen);
            imageView4.setY(ladoImagen);
            imageView6.setY(ladoImagen);
            imageView6.setX(2*ladoImagen);
            imgViewHormiga[0].setX(2*ladoImagen);
            
            // Cambiar relacion de aspecto
            imgViewHormiga[0].setFitWidth(50);
            imgViewHormiga[0].setFitHeight(58);
            imgViewHormiga[2].setFitWidth(60);
            imgViewHormiga[2].setFitHeight(70);
            imgViewHormiga[1].setFitWidth(60);
            imgViewHormiga[1].setFitHeight(70);
            imgViewHormiga[1].setVisible(false);
            
            //sangre
            imageView7.setX(500); 
            imageView7.setY(700);
            imageView7.setFitWidth(60);
            imageView7.setFitHeight(45);
            
            //galleta
            imageView8.setFitWidth(120);
            imageView8.setFitHeight(90);
            
            

            Timeline timeline;
            timeline = new Timeline(
                // 0.017 ~= 60 FPS
               new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                   //ANIMACION DE LA HORMIGA 0
                   imgViewHormiga[0].setY(hormigaY[0]);
                   imgViewHormiga[0].setX(hormigaX[0]);
                   hormigaX[0] +=hormigaSpeedX[0] * hormigaDirectionX[0];
                   hormigaY[0] +=hormigaSpeedY[0] * hormigaDirectionY[0];
                   controlarSalida(0);

                   //ANIMACION DE LA HORMIGA 2
                   imgViewHormiga[2].setY(hormigaY[2]);
                   imgViewHormiga[2].setX(hormigaX[2]);
                   hormigaX[2] +=hormigaSpeedX[2] * hormigaDirectionX[2];
                   hormigaY[2] +=hormigaSpeedY[2] * hormigaDirectionY[2];
                   controlarSalida(2);
                   
                   //ANIMACION DE LA MONEDA
                   moneda.setCenterY(monedaY);
                   moneda.setCenterX(monedaX);
                   monedaX +=monedaSpeedX * monedaDirectionX;
                   monedaY +=monedaSpeedY * monedaDirectionY;
                   controlarSalidaMoneda();
                   
                   
            })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

    // Pulsaciones del ratón       
            scene.setOnMouseClicked((MouseEvent e) -> {
                    // Hormiga 0
                    if ( imgViewHormiga[0].contains( e.getX(), e.getY() ) ){
                        // asignar un numero a numeroHormiga
                        System.out.println("le doy");
                        int numeroHormiga= 0;
                        //recolocarHormiga(numeroHormiga)
                        recolocarHormiga(numeroHormiga);
                        //sangre y sonido             
                        imageView7.setX (e.getX( ));
                        imageView7.setY (e.getY( ));
                    }        

                    // Hormiga 2  
                    else { if ( imgViewHormiga[2].contains( e.getX(), e.getY() ) ){
                        // asignar un numero a numeroHormiga
                        System.out.println("le doy a la 2");
                        int numeroHormiga= 2;
                        //recolocarHormiga(numeroHormiga)
                        recolocarHormiga(numeroHormiga);
                        //sangre y sonido
                        imageView7.setX (e.getX( ));
                        imageView7.setY (e.getY( ));
                    }
                    
                    // moneda  
                    if ( moneda.contains( e.getX(), e.getY() ) ){
                        System.out.println("le doy a la moneda");
                        recolocarMoneda();
                        // sonido 'clin'
                    }}
            });     


    }
    
// METODO ControlarSalida
        private void controlarSalida(int numeroHormiga ){
            if(hormigaX[numeroHormiga]< -300 || hormigaX[numeroHormiga] > 940 || hormigaY[numeroHormiga]< -300 || hormigaY[numeroHormiga] > 740 ){
                System.out.println("sale");
                recolocarHormiga(numeroHormiga);
            }
        }
// METODO ControlarSalidaMoneda
        private void controlarSalidaMoneda(){
            if(monedaX< -300 || monedaX > 940 || monedaY< -300 || monedaY > 740 ){
                System.out.println("sale");
                recolocarMoneda();
            }
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
                // izquierda
                int caso0PositionYImg2 = random.nextInt(640);
                int caso0PositionXImg2 = -200 ;
                hormigaY[numeroHormiga]= (short) caso0PositionYImg2;
                hormigaX[numeroHormiga]= (short) caso0PositionXImg2;
                hormigaDirectionX[numeroHormiga] = 1;



                break;
            case 1:
                // mandar la hormiga a un punto aleatorio entre 0 y 640 de la Y
                // la X vale mas de 480 para que salga
                //derecha
                int caso1PositionYImg2 = random.nextInt(640);
                int caso1PositionXImg2 = 840 ;
                hormigaY[numeroHormiga]= (short) caso1PositionYImg2;
                hormigaX[numeroHormiga]= (short) caso1PositionXImg2;
                hormigaDirectionX[numeroHormiga] = -1;
                break;

            case 2:
                // mandarla a un punto aleatorio de la X entre 0 y 480 
                // Y negativa 
                // abajo
                int caso2PositionXImg2 = random.nextInt(480);
                int caso2PositionYImg2 = 640 ;
                hormigaY[numeroHormiga]= (short) caso2PositionYImg2;
                hormigaX[numeroHormiga]= (short) caso2PositionXImg2;
                hormigaDirectionY[numeroHormiga] = -1;
                break;

            case 3:
                // mandarla a una posicion aleatoria de X entre 0 y 480
                // mandarla a una posicion negativa de Y
                //arriba
                int caso3PositionXImg2 = random.nextInt(480);
                int caso3PositionYImg2 = -200 ;
                hormigaY[numeroHormiga]= (short) caso3PositionYImg2;
                hormigaX[numeroHormiga]= (short) caso3PositionXImg2;
                hormigaDirectionY[numeroHormiga] = 1;
                break;
            default:
                // code block
        }
    }  
    
//METODO RECOLOCAR HORMIGA
    private void recolocarMoneda(){
    //            int numeroHormiga;
        Random random = new Random();
        int numero = random.nextInt(4);
        System.out.println(numero);
        switch(numero) {
            case 0:
                // izquierda
                // mandar la hormiga a un punto aleatorio entre 0 y 640 de la Y
                // mandar la hormiga a un punto negativo de la X
                // izquierda
                int caso0PositionYImg2 = random.nextInt(640);
                int caso0PositionXImg2 = -200 ;
                monedaY= (short) caso0PositionYImg2;
                monedaX= (short) caso0PositionXImg2;
                monedaDirectionX = 1;
                break;
                
            case 1:
                // mandar la hormiga a un punto aleatorio entre 0 y 640 de la Y
                // la X vale mas de 480 para que salga
                //derecha
                int caso1PositionYImg2 = random.nextInt(640);
                int caso1PositionXImg2 = 840 ;
                monedaY= (short) caso1PositionYImg2;
                monedaX= (short) caso1PositionXImg2;
                monedaDirectionX = -1;
                break;

            case 2:
                // mandarla a un punto aleatorio de la X entre 0 y 480 
                // Y negativa 
                // abajo
                int caso2PositionXImg2 = random.nextInt(480);
                int caso2PositionYImg2 = 640 ;
                monedaY= (short) caso2PositionYImg2;
                monedaX= (short) caso2PositionXImg2;
                monedaDirectionY = -1;
                break;

            case 3:
                // mandarla a una posicion aleatoria de X entre 0 y 480
                // mandarla a una posicion negativa de Y
                //arriba
                int caso3PositionXImg2 = random.nextInt(480);
                int caso3PositionYImg2 = -200 ;
                monedaY= (short) caso3PositionYImg2;
                monedaX= (short) caso3PositionXImg2;
                monedaDirectionY = 1;
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
//El objetivo será que las hormigas no toquen la galleta
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
//- Recortar la galleta y el escarabajo
//-  Poner que cada vez que las hormigas se choquen con la galleta se la coman un poco, añadiendo otra imagen de la galleta recortada
//-  Si la hormiga se choca con la galleta el contador se pone a 1 y dejo de poner visible la primera galleta y asi con la segunda
   