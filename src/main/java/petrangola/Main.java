package petrangola;

import javafx.application.Application;
import javafx.stage.Stage;
import petrangola.views.ViewFactory;
import petrangola.views.ViewFactoryImpl;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Petrangola");

        ViewFactory viewFactory = new ViewFactoryImpl(primaryStage);
        viewFactory.createActionView();
    }
}
