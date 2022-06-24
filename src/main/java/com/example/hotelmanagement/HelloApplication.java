package com.example.hotelmanagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    Scene screenHompage, screenLogin,screenregister;
    TextField name, pass;
    Stage window;
    @Override
    public void start(Stage primaryStage) throws IOException {

        DBConnect conn = new DBConnect();
        //call admin

        VBox vBoxhompage = new VBox();


//        hompage
        vBoxhompage.setPadding(new Insets(150));
        this.getThemdisplayRoom(vBoxhompage, conn);
//      login
        VBox loginPage = new VBox();
        this.showLogin(loginPage, conn);
//      register
        VBox registerPage = new VBox();

            GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        this.addUIControls(gridPane);

        screenLogin = new Scene(loginPage, 400, 400);
        screenregister = new Scene(gridPane,400,400);
        screenHompage = new Scene(vBoxhompage, 1000, 1000);




        window = primaryStage;
        window.setScene(screenLogin);
        window.show();
    }
//    ADmin

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

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Full Name : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);


        // Add Email Label
        Label emailLabel = new Label("Email ID : ");
        gridPane.add(emailLabel, 0, 2);

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 2);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }
                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your email id");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }

                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + nameField.getText());
            }


        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    void  showLogin(VBox loginPage , DBConnect conn){
        Label labelLogin =new Label("LOGIN");
        Label Aname = new Label("Name: ");
        Label Apassword = new Label("Password: ");
        name = new TextField();
        pass= new TextField();
        HBox fieldName = new HBox();
        fieldName.getChildren().addAll(Aname,name);
        fieldName.setSpacing(10);
        fieldName.setAlignment(Pos.BASELINE_CENTER);
        HBox fieldPass = new HBox();
        fieldPass.getChildren().addAll(Apassword,pass);
        fieldPass.setSpacing(10);
        fieldPass.setAlignment(Pos.BASELINE_CENTER);
        Button btnGoBack = new Button("Register");
        btnGoBack.setOnAction(actionEvent -> {
            window.setScene(screenregister);
        });
        Button btnLogin = new Button("LOGIN");
        btnLogin.setOnAction(actionEvent -> {
            this.checkLogin(conn);
        });
        HBox btnLoginPage = new HBox();
        btnLoginPage.getChildren().addAll(btnLogin,btnGoBack);
        btnLoginPage.setSpacing(10);
        btnLoginPage.setAlignment(Pos.BASELINE_CENTER);
        loginPage.getChildren().addAll(labelLogin,fieldName,fieldPass,btnLoginPage);
        loginPage.setSpacing(15);
        loginPage.setAlignment(Pos.BASELINE_CENTER);
    }
     void checkLogin(DBConnect db){
        ArrayList<Admin> ad = new ArrayList<Admin>();
        ad = (ArrayList<Admin>) db.getAdmin();
        String inputName = name.getText();
        String inputPass = pass.getText();
        if(inputName.equals(ad.get(0).name)&& inputPass.equals(ad.get(0).password)){
            LoginSuccess();
            window.setScene(screenHompage);

        }else{
            LoginError();
        }
    }

    private void LoginError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText("Login fail!");
        alert.show();
    }
    private void LoginSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Hi "+name.getText());
        alert.setContentText("Login successfully!");
        alert.show();
    }
    void getThemdisplayRoom(VBox vBox, DBConnect conn) {
        ArrayList<Room> RoomList = conn.getRoom();
        this.addRoom(vBox, conn);
        for(int i = 0; i < RoomList.size(); ++i) {
            HBox hBoxRooms = new HBox();
            hBoxRooms.setSpacing(20.0);
            Button btnDelete = new Button("DELETE");
            btnDelete.setId(String.valueOf(((Room)RoomList.get(i)).id));
            btnDelete.setOnAction((e) -> {
                conn.deleteRoom(Integer.parseInt(btnDelete.getId()));
                vBox.getChildren().removeAll(vBox.getChildren());
                this.getThemdisplayRoom(vBox, conn);
            });


            Button btnEdit = new Button("EDIT");
            btnEdit.setId(String.valueOf(((Room)RoomList.get(i)).id));
            int finalI = i;
            btnEdit.setOnAction((e) -> {
                vBox.getChildren().removeAll(vBox.getChildren());



                Label nameRoom = new Label("Name");
                Label roomPrice = new Label("price");
                Label description = new Label("description");
                TextField textName = new TextField();
                TextField textprice = new TextField();
                TextField textdescription = new TextField();
                textName.setText(((Room)RoomList.get(finalI)).name);
                textprice.setText(String.valueOf(((Room)RoomList.get(finalI)).price));
                textdescription.setText(((Room)RoomList.get(finalI)).description);

                HBox hBoxName = new HBox();
                hBoxName.getChildren().addAll(new Node[]{nameRoom, textName});
                HBox hBoxprice = new HBox();
                hBoxprice.getChildren().addAll(new Node[]{roomPrice, textprice});
                HBox hBoxdescription = new HBox();
                hBoxdescription.getChildren().addAll(new Node[]{description, textdescription});

                Button btnAdd = new Button("SAVE");
                btnAdd.setOnAction((a) -> {
                    this.saveEditRoom(textName, textprice,textdescription, ((Room)RoomList.get(finalI)).id, conn, vBox);
                });
                vBox.getChildren().addAll(new Node[]{hBoxName, hBoxprice,hBoxdescription, btnAdd});
                this.getThemdisplayRoom(vBox, conn);
            });


            Button btnView = new Button("View");
//


            Object var10002 = RoomList.get(i);
            Label id = new Label("" + ((Room)var10002).id);
            var10002 = RoomList.get(i);
            Label name = new Label("" + ((Room)var10002).name);
            var10002 = RoomList.get(i);
            Label price = new Label("" + ((Room)var10002).price);
            var10002 = RoomList.get(i);
            Label description = new Label("" + ((Room)var10002).description);
            hBoxRooms.getChildren().addAll(new Node[]{id, name, price,description, btnDelete, btnEdit});
            vBox.getChildren().add(hBoxRooms);
        }
        Button btnadmin = new Button("go admin");
        btnadmin.setOnAction(actionEvent -> {
            window.setScene(screenLogin);
        });
        vBox.getChildren().add(btnadmin);

    }

    void addRoom(VBox vBox, DBConnect conn) {
        Label nameroom = new Label("Name");
        Label priceroom = new Label("price");
        Label description = new Label("description");
        TextField textName = new TextField();
        TextField priceRoom = new TextField();
        TextField textdescription = new TextField();

        HBox hBoxName = new HBox();
        hBoxName.getChildren().addAll(new Node[]{nameroom, textName});
        HBox hBoxprice = new HBox();
        hBoxprice.getChildren().addAll(new Node[]{priceroom, priceRoom});
        HBox hBoxdescription = new HBox();
        hBoxdescription.getChildren().addAll(new Node[]{description, textdescription});

        Button btnAdd = new Button("ADD Room");
        vBox.getChildren().addAll(new Node[]{hBoxName, hBoxprice,hBoxdescription, btnAdd});
        btnAdd.setOnAction((e) -> {
            String txtName = textName.getText();
            Float ftPrice = Float.parseFloat(priceRoom.getText());
            String txtdescription = textdescription.getText();

            conn.insertRoom(new Room(txtName, ftPrice,txtdescription));
            vBox.getChildren().removeAll(vBox.getChildren());
            this.getThemdisplayRoom(vBox, conn);
        });
    }

    void saveEditRoom(TextField textName, TextField textprice, TextField textdescription, int id, DBConnect conn, VBox vBox) {

        String editName = textName.getText();
        String editdescription = textdescription.getText();
        Float editprice = Float.valueOf(textprice.getText());
        conn.updateRoom(new Room(id, editName, editprice,editdescription));
        vBox.getChildren().removeAll(vBox.getChildren());
        this.getThemdisplayRoom(vBox, conn);
    }


    public static void main(String[] args) {
        launch();
    }
}