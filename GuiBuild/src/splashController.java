import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class splashController {

    private Connection conn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button newWorkout;

    @FXML
    private Button workoutLog;

    @FXML
    void initialize() {

        newWorkout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //Sett opp øvelsespanelet
                    FXMLLoader ovelseLoader = new FXMLLoader(getClass().getResource("ovelse.fxml"));
                    ovelseLoader.setController(new OvController(conn));
                    Parent ovUI = ovelseLoader.load();

                    //Stter opp og åpner vindu
                    Scene scene = new Scene(ovUI);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Øvelse");
                    stage.show();

                    //Lukker vinduet
                    closeWindow(newWorkout.getScene());

                }catch (Exception e){

                }
            }
        });

        workoutLog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //Sett opp øvelsespanelet
                    FXMLLoader logLoader = new FXMLLoader(getClass().getResource("workoutLog.fxml"));
                    logLoader.setController(new logController(conn));
                    Parent logUI = logLoader.load();

                    //Stter opp og åpner vindu
                    Scene scene = new Scene(logUI);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Treningslogg");
                    stage.show();


                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });

    }

    public splashController(Connection conn){
        this.conn = conn;
    }

    private void closeWindow(Scene scene){
        Stage currwindow = (Stage) scene.getWindow();
        currwindow.close();
    }
}
