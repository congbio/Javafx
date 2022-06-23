package com.example.hotelmanagement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

//        VBox box = new VBox();
//        HBox hbo = new HBox();
        Button btnname0 = new Button("aDDDDDD");
        Button btnnam1= new Button("aDDDDDD");
        Button btnname2 = new Button("aDDDDDD");
        TextField btnname3 = new TextField("");
        TextField btnname4 = new TextField("");
        TextField btnname5 = new TextField("");

        VBox box1 = new VBox();
        VBox box2 = new VBox();
        box1.getChildren().addAll(btnname0,btnnam1,btnname2);
        box2.getChildren().addAll(btnname3,btnname4,btnname5);


        HBox hbo = new HBox();
        hbo.getChildren().addAll(box1,box2);


//        box.getChildren().addAll(btnnam1,btnname3,btnname2);

        Scene scen = new Scene(hbo,200,200);
        stage.setScene(scen);
        stage.show();

    }
}
