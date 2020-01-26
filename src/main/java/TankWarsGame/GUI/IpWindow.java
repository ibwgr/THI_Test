package TankWarsGame.GUI;

import TankWarsGame.Field.Cell;
import TankWarsGame.Field.FieldOccupiedException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class IpWindow extends Application {

    public static String enemyIpAdress; //TODO Philipp use this variable
    public static int playerChoice;
    // main window
    Stage ipwindow;
    // scenes
    Scene scene;

    private Scene createScene(){
        /*******************************************************************************/
        // gridpane
        /*******************************************************************************/
        GridPane gridpaneIpSetting = new GridPane();
        //set Background
        gridpaneIpSetting.setStyle("-fx-background-color: lightgray");
        //TODO Doesn't work yet and buttons/textfields function @rade
        //TODO make WINDOW NOT RESIZABLE!!!!!!!!!!!!!!!!!!!!@rade
        gridpaneIpSetting .setVgap(10);
        gridpaneIpSetting .setHgap(10);
        gridpaneIpSetting .setPadding(new Insets (50, 50, 50, 50));
        //DEBUG
        gridpaneIpSetting.setGridLinesVisible(false);

        /*******************************************************************************/
        // buttons, textfields
        /*******************************************************************************/
        TextField textfieldEnemyIp = new TextField();
        textfieldEnemyIp.setPrefSize(500, 40);
        //set pre Text in Textfield and style
        textfieldEnemyIp.setPromptText("Enter enemy IP Adress");

        TextField textfieldOwnIp = new TextField();
        textfieldOwnIp.setPrefSize(500, 40);
        //set pre Text in Textfield and style
        textfieldOwnIp.setPromptText("Your Own Adress will be displayed here");
        try {
            textfieldOwnIp.setText(String.valueOf(InetAddress.getLocalHost()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Label labelEnemyConnect = new Label("Connect with your Enemy:");
        labelEnemyConnect.setStyle("-fx-font-size: 21; -fx-text-fill: #000; -fx-font-family: Monospaced");
        Label labelOwn = new Label("Your IP Adress:");
        labelOwn.setStyle("-fx-font-size: 21; -fx-text-fill: #000; -fx-font-family: Monospaced");

        Button buttonConnect= new Button();
        buttonConnect.setPrefSize(150, 40);
        buttonConnect.setText("Connect");

        Button buttonIpCancel= new Button();
        buttonIpCancel.setPrefSize(150, 40);
        buttonIpCancel.setText("Cancel");

        Image imageTank = new Image("File:images/tank.png");
        ImageView imageviewTank = new ImageView(imageTank);
        imageviewTank.setFitWidth(300);
        imageviewTank.setFitHeight(200);
        gridpaneIpSetting.getChildren().add(imageviewTank);

        ComboBox<String> comboBoxPlayer = new ComboBox<>();
        comboBoxPlayer.getItems().addAll("Player 1", "Player 2");
        comboBoxPlayer.setPrefSize(500, 40);
        comboBoxPlayer.setPromptText("Choose a Player your enemy must choose the oposite:");

        /*******************************************************************************/
        //gridpane add children and place
        /*******************************************************************************/
        gridpaneIpSetting.getChildren().addAll(textfieldEnemyIp, textfieldOwnIp, labelEnemyConnect, labelOwn, buttonConnect, buttonIpCancel, comboBoxPlayer);

        GridPane.setConstraints(labelEnemyConnect, 0,0);
        GridPane.setHalignment(labelEnemyConnect, HPos.LEFT);

        GridPane.setConstraints(textfieldEnemyIp, 0, 1);
        GridPane.setHalignment(textfieldEnemyIp, HPos.CENTER);

        GridPane.setConstraints(labelOwn, 0,2);
        GridPane.setHalignment(labelOwn, HPos.LEFT);

        GridPane.setConstraints(textfieldOwnIp, 0, 3);
        GridPane.setHalignment(textfieldOwnIp, HPos.CENTER);

        GridPane.setConstraints(comboBoxPlayer, 0,5);
        GridPane.setHalignment(comboBoxPlayer, HPos.LEFT);

        GridPane.setConstraints(buttonConnect, 0,6);
        GridPane.setHalignment(buttonConnect, HPos.LEFT);

        GridPane.setConstraints(buttonIpCancel, 2,8);
        GridPane.setHalignment(buttonIpCancel, HPos.CENTER);

        GridPane.setConstraints(imageviewTank, 2,7);
        GridPane.setHalignment(imageviewTank, HPos.CENTER);

        /*******************************************************************************/
        //enemy Ip Listener
        /*******************************************************************************/
        buttonConnect.setOnMouseClicked(mouseEvent -> {
            label:
            try {
                enemyIpAdress = String.valueOf(textfieldEnemyIp);
                System.out.println(playerChoice);
                MainWindow screen = new MainWindow();
               screen.start(ipwindow);
            } catch (Exception e) {
                textfieldEnemyIp.setText("HELP: You need the IP adress of your enemy.");
                break label;
            }
        });

        /*******************************************************************************/
        //Combobox and connect listener
        /*******************************************************************************/
        comboBoxPlayer.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (Objects.equals(t1, "Player 1")) {
                    playerChoice = 1;
                } else if (Objects.equals(t1, "Player 2")) {
                    playerChoice = 2;
                } else {
                    playerChoice = 0;
                }
            }
        });

        /*******************************************************************************/
        // Cancel Button (Restart)
        /*******************************************************************************/
        buttonIpCancel.setOnMouseClicked(mouseEvent -> {
            try{
                StartScreen screen = new StartScreen();
                screen.start(ipwindow);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        scene = new Scene(gridpaneIpSetting, 800, 600);
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*******************************************************************************/
        // Main Window - show
        /*******************************************************************************/
        ipwindow = primaryStage;
        ipwindow.setScene(createScene());
        ipwindow.setTitle("TANK WARS");
        ipwindow.show();


    }

}
