package com.example.hotelmanagement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomePage extends Application {

    private Scene homeScene,checkoutScene, viewDetailScene ;
    public static void main(String[] args) {
        launch(args);
    }

    void viewDetail (Stage stage,int id){
        DBConnect DB = new DBConnect();
        ArrayList<Room> listRoom = DB.getRoom();
        System.out.println(id);
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setPadding(new Insets(20));

        VBox vbRoom = new VBox();


        System.out.println(listRoom.get(id).getImage());
        Image image = new Image(listRoom.get(id).getImage());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(500);
        imageView.setFitHeight(500);
        Label lbName = new Label(listRoom.get(id).getName());
        Label lbPrice = new Label(("Price: $"+String.valueOf(listRoom.get(id).getPrice())));
        Label lbDescription = new Label(listRoom.get(id).getDescription());

        lbPrice.setPadding(new Insets(20));
        lbDescription.setPadding(new Insets(20));
        lbName.setPadding(new Insets(20));

        var btnBooking = new Button("Book now");
        btnBooking.setStyle("-fx-background-color: green");
        btnBooking.setStyle("-fx-font-size: 2em; ");
        btnBooking.setOnAction(actionEvent -> {
            formChecout( stage);

        });
        btnBooking.setPadding(new Insets(10));

        vbRoom.getChildren().addAll( lbName,lbPrice,lbDescription,btnBooking);
        grid.add((imageView), 0, 0);
        grid.add((vbRoom), 1, 0);
        VBox ViewBig = new VBox();
        var btnback = new Button("back");
        btnback.setOnAction(actionEvent -> {
            stage.setScene(homeScene);

        });
        ViewBig.getChildren().addAll(grid,btnback);
        viewDetailScene = new Scene(ViewBig,800,800);
        stage.setScene(viewDetailScene);
        stage.centerOnScreen();

    }
    void formChecout (Stage stage){
        GridPane root = new GridPane();
        Label firsName = new Label("First Name");
        Label lastName = new Label("Last Name");
        Label userName = new Label("User Name");
        Label emailId = new Label("Email Id");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();

        Button Submit = new Button("Submit");


        root.setHgap(8);
        root.setVgap(8);
        root.setPadding(new Insets(5));


        root.addRow(0, firsName, textField1);
        root.addRow(1, lastName, textField2);
        root.addRow(2, userName, textField3);
        root.addRow(3, emailId, textField4);
        root.addRow(4, Submit);
        var btnback = new Button("back");
        btnback.setOnAction(actionEvent -> {
            stage.setScene(viewDetailScene);
//            stage.hide();
        });
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(40, 20, 20,40));
        root.addRow(5, btnback);



        checkoutScene = new Scene(root, 400, 400);

        Stage newWindow = new Stage();
        newWindow.setTitle("Checkout");
        newWindow.centerOnScreen();
        newWindow.setScene(checkoutScene);
        newWindow.show();


    }



    void showListRoom(ArrayList<Room> listRoom,GridPane grid, Stage stage){
        int ygrid =0;
        int xgrid = 0;
        for(int i = 0; i < listRoom.size(); i++){
            VBox vbRoom = new VBox();
            var btnView = new Button("View");


            int finalI = i;
            btnView.setOnAction(actionEvent -> {
                viewDetail(stage, finalI);


            });
            Image image = new Image(listRoom.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            Label lbName = new Label(listRoom.get(i).getName());
            Label lbPrice = new Label(("Price: $"+String.valueOf(listRoom.get(i).getPrice())));

            vbRoom.getChildren().addAll(imageView,lbName,lbPrice, btnView);
            grid.add((vbRoom), xgrid, ygrid);
            xgrid +=1;
            if( xgrid == 3){
                xgrid = 0;
                ygrid +=1;
            }

        }
    }
    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        DBConnect DB = new DBConnect();
        ArrayList<Room> listRoom = DB.getRoom();


        //show
        showListRoom( listRoom,  grid,   stage);

        homeScene = new Scene(grid, 1000,800);
        stage.setScene(homeScene);
        stage.centerOnScreen();
        stage.show();

    }
}