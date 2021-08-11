package main.java.petrangola;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.petrangola.views.ViewFactory;
import main.java.petrangola.views.ViewFactoryImpl;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Petrangola");
        new ViewFactoryImpl(primaryStage).createActionView();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
