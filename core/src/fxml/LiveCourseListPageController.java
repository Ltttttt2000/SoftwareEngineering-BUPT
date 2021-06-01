package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.List;
import com.iot.g89.Live;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * <p>Boundary.</p>
 * <p>LiveCourseListPageController class.</p>
 * <p>For Client</p>
 *
 * @version 0.5
 * @author S0range
 */

public class LiveCourseListPageController implements Initializable {
    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Live> tableView;
    private Scene thisScene;
    private GUIDriver driver;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void initData(ArrayList<Live> videos, Scene thisScene, GUIDriver driver){
        final ObservableList<Live> cellData = FXCollections.observableArrayList(videos);
        //ObservableList<Video> items = tableView.getItems();

        this.driver = driver;
        this.thisScene = thisScene;

        TableColumn<Live,String> ID = new TableColumn<>("ID");

        TableColumn<Live,String> author = new TableColumn<>("Teacher");

        TableColumn<Live,String> dateTime = new TableColumn<>("DateTime");

        TableColumn<Live,String> number = new TableColumn<>("NumberOfClient");

        TableColumn<Live,String> description = new TableColumn<>("Description");

        ID.setCellValueFactory(new PropertyValueFactory<Live,String>("liveId"));
        author.setCellValueFactory(new PropertyValueFactory<Live,String>("instructorId"));
        dateTime.setCellValueFactory(new PropertyValueFactory<Live, String>("date"));
        number.setCellValueFactory(new PropertyValueFactory<Live,String>("number"));
        description.setCellValueFactory(new PropertyValueFactory<Live,String>("description"));

        deleteButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        deleteLive();
                    }
                }
        );

        tableView.setItems(cellData);
        tableView.getColumns().addAll(ID,author,dateTime,number, description);
        tableView.getSelectionModel().select(0);
    }

    public void deleteLive(){
        Live chosen = tableView.getSelectionModel().getSelectedItem();
        if(driver.delete(chosen.getLiveId())){
            showAlert("Delete Successful",
                    null,
                    "You have successfully deleted the live course " + chosen.getLiveId(),
                    Alert.AlertType.INFORMATION);
        }else{
            showAlert("Delete Failed",
                    "You cannot delete this live course!",
                    "Please choose another.",
                    Alert.AlertType.ERROR);
        }
        List.closeStage();
    }

    public void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}