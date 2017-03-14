import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.swing.plaf.nimbus.State;

public class NyKategoriController {

    private Connection conn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button avbryt;

    @FXML
    private Button nyKategori;

    @FXML
    private TextArea kateogriBeskrivelse;

    @FXML
    void initialize() {

        //Avbrytbutton
        avbryt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) avbryt.getScene().getWindow();
                stage.close();
            }
        });

        //Lagring av ny kategori

        nyKategori.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Boolean catAlreadyExists = true;
                try {
                    Statement checkCat = conn.createStatement();
                    ResultSet doesExist = checkCat.executeQuery("SELECT * FROM category WHERE name ='" + kateogriBeskrivelse.getText() + "'");
                    if (!doesExist.next()){
                        catAlreadyExists = false;
                    }
                }catch (Exception e){

                }

                //Setter inn en ny kategori hvis den ikke allerede finnes og noe er fyllt inn i navnefeltet.
                if (kateogriBeskrivelse.getText() != null && !catAlreadyExists){
                    try {
                        Statement updateTable = conn.createStatement();
                        updateTable.executeUpdate("INSERT INTO `workoutapp`.`category` (`name`) VALUES ('"+kateogriBeskrivelse.getText()+"');");

                        Stage stage = (Stage) nyKategori.getScene().getWindow();
                        stage.close();
                    }catch (Exception e){
                        System.out.println(e);

                    }
                }

            }
        });

    }

    public NyKategoriController(Connection conn){
        this.conn = conn;
    }
}
