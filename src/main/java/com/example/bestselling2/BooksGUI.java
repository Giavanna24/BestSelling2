package com.example.bestselling2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BooksGUI extends Application {
    BooksController myController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BooksGUI.class.getResource("Books-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        myController = fxmlLoader.getController();
        stage.setTitle("Books");
        stage.setScene(scene);
        stage.show();
    }
        public void stop () throws Exception {
            myController.saveData();
        }
        public static void main (String[]args){
            launch();
        }
    }

