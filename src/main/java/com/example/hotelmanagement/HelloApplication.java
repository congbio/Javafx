////package com.example.hotelmanagement;
////
////import javafx.application.Application;
////import javafx.event.ActionEvent;
////import javafx.event.EventHandler;
////import javafx.fxml.FXMLLoader;
////import javafx.geometry.HPos;
////import javafx.geometry.Insets;
////import javafx.geometry.Pos;
////import javafx.scene.Node;
////import javafx.scene.Scene;
////import javafx.scene.control.*;
////import javafx.scene.layout.*;
////import javafx.scene.text.Font;
////import javafx.scene.text.FontWeight;
////import javafx.stage.Stage;
////import javafx.stage.Window;
////
////import java.io.IOException;
////import java.util.ArrayList;
//
//public class Home extends Application {
//
//    private Scene scene;
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) {
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.TOP_CENTER);
//        grid.setVgap(10);
//        grid.setHgap(10);
//        DBConnect DB = new DBConnect();
//        ArrayList<Painting> paintingList = DB.getPaintings();
//
//        //show
//        for(int i = 0; i < paintingList.size(); i++){
//
//            var btnBuy = new Button("Buy Now");
//            Image image = new Image(paintingList.get(i).getImage());
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            imageView.setFitWidth(200);
//            imageView.setFitHeight(200);
//            Text textName = new Text(paintingList.get(i).getName());
//            textName.setStyle("-fx-font: normal bold 15px 'serif'");
//
//            grid.add(imageView, 0, i+0);
//            grid.add((textName), 1, i+0);
//            grid.add(new Label ("Price: $"+String.valueOf(paintingList.get(i).getPrice())), 2, i+0);
//            grid.add((btnBuy), 3, i+0);
//        }
//
//        scene = new Scene(grid, 800, 1000);
//        stage.setTitle("Painting");
//        stage.setScene(scene);
//        stage.show();
//    }
//}