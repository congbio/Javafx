package com.example.hotelmanagement;

import javafx.application.Application;

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
import java.util.ArrayList;

public class HomePage extends Application {
    TextField accountUser;
    boolean accountCustommer;

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
    private void addUIControls(GridPane gridPane, Stage stage) {
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
        TextField  nameuser = new TextField();
        nameuser.setPrefHeight(40);
        gridPane.add(nameuser, 1,1);


        // Add Email Label
        Label emailLabel = new Label("Email ID : ");
        gridPane.add(emailLabel, 0, 2);

        // Add Email Text Field
        TextField  emailuser = new TextField();
          emailuser.setPrefHeight(40);
        gridPane.add(emailuser, 1, 2);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        TextField passuser = new TextField();
        passuser.setPrefHeight(40);

        gridPane.add(passuser, 1, 3);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        Button btnback = new Button("Back");
        btnback.setOnAction(actionEvent -> {
                    stage.setScene(loginScence);
                    stage.centerOnScreen();
        });
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 4, 2, 1);
        gridPane.add(btnback, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(10, 0,20,0));

        submitButton.setOnAction(actionEvent -> {
            this.checkRegister(stage, gridPane,nameuser,passuser,emailuser);
        });
    }
    private  void checkRegister(Stage stage,GridPane gridPane,TextField nameuser, TextField passuser, TextField emailuser){
        DBConnect con = new DBConnect();
        ArrayList<customers> ad = (ArrayList<customers>) con.getCustomers();

        String inputName = nameuser.getText();
        String inputpass = passuser.getText();
        String inputemail = emailuser.getText();


        if(inputName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
            return;
        }
        if(inputemail.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your email id");
            return;
        }
        if(inputpass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
            return;
        }


        for (int i = 0; i < ad.size(); i++) {
            if( inputemail.equals(ad.get(i).email)){
                showAlertErroRegister();
                this.ClearFormRegister( nameuser,  emailuser,  passuser);
                return;

            }

        }
        con.insertCustomer(new customers(inputName,inputemail,inputpass));
        RegisterSuccess();
        stage.setScene(loginScence);
    }
    void showAlertErroRegister(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registger");
        alert.setHeaderText("Hi You " );
        alert.setContentText("Tài khoản đã tồn  tại vui lòng đăng ky tài khoản khác");
        alert.show();
    }
    private void RegisterSuccess( ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setContentText("Bạn đã Đăng ký thành công");
        alert.setContentText("Mời bạn đăng nhập để xem phòng !");
        alert.show();
    }
    private void ClearFormRegister(TextField inputName, TextField inputEmail, TextField inputPass){
        inputEmail.clear();
        inputPass.clear();
        inputName.clear();

    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }









    private void resetInputLogin(TextField nameuser, TextField passuser){
        nameuser.clear();
        passuser.clear();

    }
    Scene showLogin(Stage stage){
        VBox loginPage = new VBox();
        Label labelLogin =new Label("LOGIN ACCOUNT");
        Label Aname = new Label("Name: ");
        Label Apassword = new Label("Password: ");
        TextField  nameuser = new TextField();
        TextField passuser= new TextField();
        HBox fieldName = new HBox();

        fieldName.getChildren().addAll(Aname,nameuser);
        fieldName.setSpacing(20);
        fieldName.setAlignment(Pos.BASELINE_CENTER);
        HBox fieldPass = new HBox();
        fieldPass.getChildren().addAll(Apassword,passuser);
        fieldPass.setSpacing(10);
        fieldPass.setAlignment(Pos.BASELINE_CENTER);

        Button btnGoBack = new Button("Register");
        btnGoBack.setOnAction(actionEvent -> {
            stage.setScene(screenregister);
            stage.centerOnScreen();
        });
        Button btnLogin = new Button("LOGIN");
        btnLogin.setOnAction(actionEvent -> {
            this.checkLogin(stage,nameuser,passuser);
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
    private void checkLogin(Stage stage,TextField nameuser, TextField passuser){
        DBConnect con = new DBConnect();
        ArrayList<customers> ad = (ArrayList<customers>) con.getCustomers();
        String inputName =  nameuser.getText();
        String inputPass =  passuser.getText();
        boolean check = false;
        for (int i = 0; i < ad.size(); i++) {

            if(inputName.equals(ad.get(i).cusName)&& inputPass.equals(ad.get(i).password)){

                accountCustommer = true;
                LoginSuccess(inputName);
                stage.setScene(homeScene);
                stage.centerOnScreen();
                check = true;
                resetInputLogin(nameuser,passuser);
                break;
            }

        }
        if( !check ){
            LoginError();
        }

    }
    private void LoginSuccess(String inputName ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Hi "+inputName );
        alert.setContentText("Login successfully!");
        alert.show();
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
            if(accountCustommer){
                formChecout( stage,id);
            }
             else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error alert");
                alert.setHeaderText("Đăng nhập");
                alert.setContentText("vui lòng đăng nhập trước khi đặt phòng");
                alert.showAndWait();
                stage.setScene(loginScence);
                stage.centerOnScreen();
            }

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




    void bookingroom(TextField tfName, TextField tfphone, TextField tfemail, TextField tfDateInput, TextField tfDateOutput, int id){
        DBConnect DB = new DBConnect();
        ArrayList<Room> listRoom = DB.getRoom();
        String name = tfName.getText();
        String phone = tfphone.getText();
        String email =tfemail.getText();
        String dateInput = tfDateInput.getText();
        String dateOutput= tfDateOutput.getText();
        int IdRoom =  listRoom.get(id).getId();


        if(name.isEmpty() || phone.isEmpty() || email.isEmpty()||dateInput.isEmpty()||dateOutput.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error alert");
            alert.setHeaderText("Ô in put điền tên trống");
            alert.setContentText("vui lòng điền thêm tên của bạn");
            alert.showAndWait();
            return;
        }

        else {
            DBConnect con = new DBConnect();
            con.insertBooking(new BookingRoom(name,phone,email,dateInput,dateOutput, IdRoom) );
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Success alert");
            alert.setHeaderText("Thành Công");
            alert.setContentText("bạn đã đạt phòng thành công");
            alert.showAndWait();
        }

    }
//    give id Room to booking
    void formChecout (Stage stage, int id){
        GridPane root = new GridPane();
        Label lbName = new Label("Nhập tên ");
        Label lbNumber = new Label("Nhập số điện thoại");
        Label lbemail = new Label("Nhập email.");
        Label lbDateInput = new Label("Nhập ngày nhận  Phòng");
        Label lbDateOutPut = new Label("Nhập ngày trả phòng");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();

        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(actionEvent -> {

                bookingroom(textField1,textField2,textField3,textField4,textField5,id);



        });
        root.setHgap(8);
        root.setVgap(8);
        root.setPadding(new Insets(5));


        root.addRow(0, lbName, textField1);
        root.addRow(1, lbNumber, textField2);
        root.addRow(2, lbDateInput, textField3);
        root.addRow(3, lbDateOutPut, textField4);
        root.addRow(4, lbemail,textField5);
        root.addRow(5, btnSubmit);
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

        accountCustommer =false;
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
//        DB.insertBooking(new BookingRoom("cong", "cong", "cong", "2022-07-08", "2022-07-08",7));




    }
}




