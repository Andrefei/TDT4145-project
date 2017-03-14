/**
 * Created by matsj on 13.03.2017.
 */
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class beskrivelseController {

    private informationHolder informationHolder;
    public informationHolder getInformationHolder() {
        return informationHolder;
    }

    public void setInformationHolder(informationHolder informationHolder) {
        this.informationHolder = informationHolder;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button lukk;

    @FXML
    private Button deleteEx;

    @FXML
    private TextArea beskrivelse;

    @FXML
    private TextArea goalBox;

    @FXML
    private Text tittel;

    @FXML
    void initialize() {

        beskrivelse.setEditable(false);
        goalBox.setEditable(false);

        tittel.setText(informationHolder.getTittel());
        beskrivelse.setText(informationHolder.getBeskrivelse());
        goalBox.setText(informationHolder.getGoalData());

        lukk.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Stage stage = (Stage) lukk.getScene().getWindow();
                stage.close();
            }
        });

        deleteEx.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Ja, dette var en ettertanke
                Connection conn = informationHolder.getConn();
                try {
                    Statement delEx = conn.createStatement();
                    delEx.executeUpdate("DELETE FROM `workoutapp`.`exercise` WHERE `name`='"+tittel.getText()+"';");

                }catch (Exception e){

                }


                Stage stage = (Stage) deleteEx.getScene().getWindow();
                stage.close();
            }
        });



    }

    public beskrivelseController(informationHolder informationHolder){
        this.informationHolder = informationHolder;
    }
}
