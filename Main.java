import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader dsf = new FXMLLoader(getClass().getResource("ovelse.fxml"));

        /*
        try{
            AnchorPane root = FXMLLoader.load(getClass().getResource("ovelse.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Øvelse");
            primaryStage.show();
        } catch (Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
