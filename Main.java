
import javafx.application.Application;
import javafx.stage.Stage;
import ui.Dashboard;

/**
 * Main class for StartupConnect GUI application.
 * This launches the JavaFX Dashboard interface.
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        try {
            // Launch the main Dashboard UI
            Dashboard.show(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
