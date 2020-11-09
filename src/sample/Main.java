package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.Database;

import java.sql.*;
import java.util.Properties;

public class Main extends Application {
    private static final String url = "jdbc:mysql://localhost:3306/second_lab?useUnicode=true&serverTimezone=Europe/Moscow";
    private static final String user = "root";
    private static final String password = "KsenBantsevich";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private Properties p;


    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Connection succesfull!");
            Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
            primaryStage.setScene(new Scene(root, 650, 350));
            primaryStage.setTitle("Лабораторная работа №2");
            primaryStage.show();
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }



    }




    public static void main(String[] args) {
        launch(args);
    }
}
