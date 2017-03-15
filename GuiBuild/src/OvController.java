package GuiBuild.src;

/**
 * Created by matsj on 10.03.2017.
 */
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.security.provider.ConfigFile;

public class OvController {

    private Connection conn;
    private informationHolder informationHolder;
    private List<String> currWOE = new ArrayList<>();

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
    private Button trenGjennomfort;

    @FXML
    private Button ovGjennomfort;

    @FXML
    private Button nyOv;

    @FXML
    private ComboBox<String> velgOv;

    @FXML
    private Spinner<Integer> set;

    @FXML
    private Spinner<Integer> repetisjoner;

    @FXML
    private Button visBeskrivelse;

    @FXML
    private Spinner<Double> vekt;

    @FXML
    private Spinner<Double> avstandKm;

    @FXML
    private Spinner<Double> varighetTimer;

    @FXML
    private ComboBox<String> vaerforhold;

    @FXML
    private Spinner<Integer> temperature;

    @FXML
    void initialize() {
        //Velg Øvelse setup
        updateOvs();


        //Beskriv Øvelse setup
        visBeskrivelse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (velgOv.getValue() != null){
                    informationHolder = new informationHolder();

                    //informationholder får få en conn og da
                    informationHolder.setConn(conn);

                    try{
                        //SQL for henting av beskrivelse
                        Statement getBesk = conn.createStatement();
                        ResultSet beskGot = getBesk.executeQuery("SELECT description FROM exercise WHERE name = '" + velgOv.getValue() + "'");
                        //Henter inn verdien
                        beskGot.next();

                        //SQL for henting av mål for øvelsen
                        Statement getGoal = conn.createStatement();
                        ResultSet goal = getGoal.executeQuery("select goalDesc, goalDate from goal where exercise = '"+velgOv.getValue()+"';");
                        goal.next();

                        //Setting av beskrivelse og tittel i lokal beskrivelseholder
                        informationHolder.setTittel(velgOv.getValue());
                        informationHolder.setBeskrivelse(beskGot.getString(1));

                        //Ikke alle øvelser har mål, så her må vi bruke try/catch
                        try {
                            informationHolder.setGoalData(goal.getString(1) + "\nMålet skal nås innen: " + goal.getString(2));
                        }catch (Exception e){
                            informationHolder.setGoalData("Ingen mål satt.");
                        }

                        //Setter opp vinduet for beskrivelse av øvelse
                        FXMLLoader beskrivelseLoader = new FXMLLoader(getClass().getResource("beskrivelsePopup.fxml"));
                        beskrivelseLoader.setController(new beskrivelseController(informationHolder));
                        Parent beskUI = beskrivelseLoader.load();

                        Scene scene = new Scene(beskUI);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Beskrivelse " + velgOv.getValue());
                        stage.showAndWait();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    updateOvs(); //I tilfelle en øvelse har blitt slettet
                }

            }
        });

        //Ny Øvelse setup
        nyOv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    //Setter opp vinduet for beskrivelse av øvelse
                    FXMLLoader nyOvLoader = new FXMLLoader(getClass().getResource("ny_ovelse.fxml"));
                    nyOvLoader.setController(new NyOvController(conn));
                    Parent nyOvUI = nyOvLoader.load();

                    Scene scene = new Scene(nyOvUI);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Ny Øvelse");
                    stage.showAndWait();
                } catch (Exception e){
                    e.printStackTrace();
                }
                updateOvs();//I tilfelle en ny øvlse har blitt lagt til
            }
        });

        //Set setup
        set.setEditable(false);

        //Repetisjoner setup
        repetisjoner.setEditable(false);
        //Vekt setup
        vekt.setEditable(true);

        //Km. Avstand setup
        avstandKm.setEditable(true);

        //Timer varighet setup
        varighetTimer.setEditable(true);

        //Værforhold setup
        ObservableList<String > vaerListe = FXCollections.observableArrayList("Sol","Regn","Snø","Kuling");
        vaerforhold.setItems(vaerListe);

        //Temperatursetup
        temperature.setEditable(false);
        temperature.increment(50);

        //Øvelse gjennomført setup
        ovGjennomfort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (velgOv.getValue() != null){
                    //legger til øvelse i current workout exerciselisten
                    currWOE.add(velgOv.getValue());

                    //Nuller ut alle felt
                    velgOv.setValue(null);
                    set.decrement(set.getValue());
                    repetisjoner.decrement(repetisjoner.getValue());
                    Double vektDec = vekt.getValue()*4;
                    vekt.decrement(vektDec.intValue());

                    Double kmDec = avstandKm.getValue()*10;
                    avstandKm.decrement(kmDec.intValue());

                    Double timDec = varighetTimer.getValue()*10;
                    varighetTimer.decrement(timDec.intValue());

                    vaerforhold.setValue(null);
                    if (temperature.getValue() < 0){
                        temperature.increment(temperature.getValue()*(-1));
                    }
                    else{
                        temperature.decrement(temperature.getValue());
                    }

                }

            }
        });
        //Trening gjennomført knapp
        trenGjennomfort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currWOE.isEmpty() != true){
                    informationHolder infoToWorkout = new informationHolder();
                    infoToWorkout.setExercises(currWOE);

                    try{
                        //Setter opp vinduet for beskrivelse av øvelse.
                        //Sender informationholder for å holde ovesikt over øvelser, conn for connection og dette vinduet så det kan lukkes.
                        FXMLLoader nyOvLoader = new FXMLLoader(getClass().getResource("treningsokt.fxml"));
                        nyOvLoader.setController(new workoutController(infoToWorkout,conn,(Stage) trenGjennomfort.getScene().getWindow()));
                        Parent nyOvUI = nyOvLoader.load();

                        Scene scene = new Scene(nyOvUI);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Treningsøkt");
                        stage.show();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    public OvController(Connection conn){
        this.conn = conn;
    }

    private void updateOvs(){
        try{
            Statement getOvs = conn.createStatement();
            ResultSet ovsGot = getOvs.executeQuery("SELECT name FROM exercise");
            ObservableList<String> ovsList = FXCollections.observableArrayList();

            while (ovsGot.next()){
                ovsList.add(ovsGot.getString(1));
            }
            velgOv.setItems(ovsList);

        }catch (Exception e){

        }
    }

}
