/**
 * Created by matsj on 13.03.2017.
 */
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class beskrivelseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label tittel;

    @FXML
    private TextArea tekstfelt;

    @FXML
    private Button lukk;

    @FXML
    void initialize() {

        lukk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) lukk.getScene().getWindow();
                stage.close();
            }
        });


    }
}
