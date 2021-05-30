package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Callback;

import javax.swing.text.DateFormatter;
import javax.swing.text.TextAction;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PublishLivePageController implements Initializable {
    @FXML
    private DatePicker liveDatePicker;
    @FXML
    private ChoiceBox<String> liveTimeCB;
    @FXML
    private TextArea descriptionTA;

    private GUIDriver driver;
    private String userId;
    private Scene thisScene;
    private Scene lastScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(
                                        LocalDate.now().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        liveDatePicker.setDayCellFactory(dayCellFactory);
        liveTimeCB.getItems().addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00");
    }

    public void initData(String userId, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.userId = userId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;

        liveDatePicker.setValue(LocalDate.now().plusDays(1));
        liveTimeCB.getSelectionModel().select(0);
    }

    public void publishLive(ActionEvent event) {
        String liveDate = liveDatePicker.getValue().toString();
        String liveTime = liveTimeCB.getValue().substring(0, 2);
        String liveDescription = descriptionTA.getText();

        String[] live = new String[5];

        live[2] = liveDate + " " + liveTime;
        if(liveDescription.equals(""))
            live[3] = "None";
        else
            live[3] = liveDescription;

        switch(driver.publishLive(live)) {
            case 1:
                backToLastScene();
                break;
            case -1:
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Duplicate");
                alert1.setHeaderText("You have already publish that day's live class!");
                alert1.setContentText("Please refill the date and time!");

                alert1.showAndWait();
                break;
            case -2:
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Time Traveller");
                alert2.setHeaderText("The Date must after today");
                alert2.setContentText("Please refill the date and time!");

                alert2.showAndWait();
                break;
        }

    }

    // for back button
    public void backToLastScene() {
        SceneTransform.ToScene(lastScene);
    }

}
