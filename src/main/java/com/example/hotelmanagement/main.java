package com.example.hotelmanagement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class main extends Application {
    private Scene login, homePage ;
    public  TextField name, pass;
    private Stage window;
    private static final String EMPTY = "";
    private static final DBConnect con = new DBConnect();
    public static void main(String[] args) {
        launch(args);
    }

    Scene showLogin(){
        VBox loginPage = new VBox();
        Label labelLogin =new Label("LOGIN ACCOUNT");
        Label Aname = new Label("Name: ");
        Label Apassword = new Label("Password: ");
        name = new TextField();
        pass= new TextField();
        HBox fieldName = new HBox();

        fieldName.getChildren().addAll(Aname,name);
        fieldName.setSpacing(20);
        fieldName.setAlignment(Pos.BASELINE_CENTER);
        HBox fieldPass = new HBox();
        fieldPass.getChildren().addAll(Apassword,pass);
        fieldPass.setSpacing(10);
        fieldPass.setAlignment(Pos.BASELINE_CENTER);

        Button btnGoBack = new Button("GO HOME");
        btnGoBack.setOnAction(actionEvent -> {
            window.setScene(homePage);
            window.centerOnScreen();
        });
        Button btnLogin = new Button("LOGIN");
        btnLogin.setOnAction(actionEvent -> {
            this.checkLogin();
        });
        HBox btnLoginPage = new HBox();
        btnLoginPage.getChildren().addAll(btnLogin, btnGoBack );
        btnLoginPage.setSpacing(10);
        btnLoginPage.setAlignment(Pos.BASELINE_CENTER);
        loginPage.getChildren().addAll(labelLogin,fieldName,fieldPass,btnLoginPage);
        loginPage.setSpacing(15);
        loginPage.setAlignment(Pos.BASELINE_CENTER);

        return new Scene(loginPage, 300,300);
    }
    private void checkLogin(){
        ArrayList<Admin> ad = (ArrayList<Admin>) con.getAdmin();
        String inputName = name.getText();
        String inputPass = pass.getText();
        if(inputName.equals(ad.get(0).name)&& inputPass.equals(ad.get(0).password)){
            LoginSuccess();
            window.setScene(homePage);
            window.centerOnScreen();
        }else{
            LoginError();
        }
    }
    private void LoginSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Hi "+name.getText());
        alert.setContentText("Login successfully!");
        alert.show();
    }
    private void LoginError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText("Login fail. Please enter again!");
        alert.show();
    }

    Scene showProduct(){
        VBox home = new VBox();
        Label labelProduct =new Label("Danh Sách Phòng Khách Sạn ");
        labelProduct.setAlignment(Pos.CENTER);
        labelProduct.setStyle("-fx-font-size: 20px; -fx-text-fill: cyan;");
        ArrayList<Room> ntList = con.getRoom();
        GridPane grid =new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        //

        grid.add(new Label("Name:"), 0, 0);
        var tfName = new TextField();
        grid.add(tfName, 0, 1);
        //
        grid.add(new Label("Price:"), 1, 0);
        var tfPrice = new TextField();
        grid.add(tfPrice, 1, 1);
        //
        grid.add(new Label("Description:"), 2, 0);
        var tfDescription = new TextField();
        grid.add(tfDescription, 2, 1);
        //

        //
        grid.add(new Label("Images:"), 3, 0);
        var tfImages = new TextField();
        grid.add(tfImages, 3, 1);

        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            Float price = Float.parseFloat(tfPrice.getText());
            String description = tfDescription.getText();


            String images = tfImages.getText();

            if (!name.equals(EMPTY) && !price.equals(EMPTY)  && !description.equals(EMPTY)  && !images.equals(EMPTY) ) {
                try {
                    System.out.println("Thành công");
                    con.insertRoom(new Room(name, price, description, images));
                    homePage = showProduct();
                    window.setScene(homePage);
                    window.centerOnScreen();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank!");
            alert.showAndWait();
        });
        grid.add(btnAdd, 5, 1);


//Show
        for (int i = 0; i < ntList.size(); i++) {
            Image image = new Image(ntList.get(i).getImage());
            System.out.print(image);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            grid.add(new Label(ntList.get(i).getName()), 0, i + 2);
            grid.add(new Label("" +ntList.get(i).getPrice()), 1, i + 2);
            grid.add(new Label("" + ntList.get(i).getDescription()), 2, i + 2);

            grid.add((imageView), 3, i + 2);


// Update
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            ArrayList<Room> finalNtList = ntList;
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int idNew = Integer.parseInt(btnUpdate.getId());
                System.out.println(idNew);

                TextField name = (TextField) grid.getChildren().get(1);
                name.setText(finalNtList.get(idNew).getName());

                TextField price = (TextField) grid.getChildren().get(3);
                price.setText("" + finalNtList.get(idNew).getPrice());

                TextField description = (TextField) grid.getChildren().get(5);
                description.setText(finalNtList.get(idNew).getDescription());



                TextField images = (TextField) grid.getChildren().get(7);
                images.setText(finalNtList.get(idNew).getImage());

                var newbtnAdd = new Button("Update");
                newbtnAdd.setPadding(new Insets(5, 15, 5, 15));
                newbtnAdd.setOnAction(event -> {
                    int newID = finalNtList.get(idNew).id;
                    System.out.println(newID);
                    String newName = name.getText();
                    String neDescripition=description.getText();



                    float newPrice = 0;
                    try {
                        newPrice = Float.parseFloat(price.getText());
                    }
                    catch (Exception ex){}

                    String newImages = images.getText();



                    if (!newName.equals(EMPTY) && !neDescripition.equals(EMPTY)   && (newPrice!=0) && !newImages.equals(EMPTY)) {
                        try {

                            con.updateRoom(new Room(newID, newName, newPrice, neDescripition, newImages));
                            homePage = showProduct();
                            window.setScene(homePage);
                            window.centerOnScreen();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        return;
                    }else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill all blank!");
                        alert.showAndWait();
                    }
                });
                grid.add(newbtnAdd, 5, 1);
            });
            grid.add(btnUpdate, 6, i + 2);
// Delete

            Button btnDelete = new Button("Delete");
            btnDelete.setId(String.valueOf((ntList.get(i)).id ));
            btnDelete.setOnAction(e -> {
                int idDelete =Integer.parseInt(btnDelete.getId());
                con.deleteRoom(idDelete);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    homePage = showProduct();
                    window.setScene(homePage);
                    window.centerOnScreen();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 5, i + 2);
        }
        home.getChildren().addAll(labelProduct, grid);

        return new Scene(home, 1200,800);
    }
    @Override
    public void start(Stage primaryStage) {
        homePage = this.showProduct();
        login = this.showLogin();
        window = primaryStage;
        window.setScene(login);
        window.show();
    }
}
