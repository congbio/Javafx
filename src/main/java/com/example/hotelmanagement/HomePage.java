package com.example.hotelmanagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.stage.Window;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomePage extends Application {
    TextField accountUser;

    private Scene homeScene,checkoutScene, viewDetailScene,loginScence,screenregister ;
    public static void main(String[] args) {
        launch(args);
    }
    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }


    private void LoginError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText("Login fail. Please enter again!");
        alert.show();
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

            lbPrice.setPadding(new Insets(5));
            lbName.setPadding(new Insets(5));

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
        grid.setVgap(20);
        grid.setHgap(20);
        DBConnect DB = new DBConnect();
        ArrayList<Room> listRoom = DB.getRoom();

        accountUser = new TextField();


        //show
        showListRoom( listRoom,  grid,   stage);
        GridPane gridPane = createRegistrationFormPane();

        loginScence = this.showLogin(stage);
        homeScene = new Scene(grid, 1000,800);
        this.addUIControls(gridPane,stage);
        screenregister = new Scene(gridPane,400,400);
        stage.setScene(homeScene);
        stage.centerOnScreen();
        stage.show();




    }
}