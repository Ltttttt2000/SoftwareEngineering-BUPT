package fxml;

import com.iot.g89.List;
import com.iot.g89.Live;
import com.iot.g89.SceneTransform;
import com.iot.g89.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LiveCourseListPageController implements Initializable {


    @FXML
    private TableView<Live> tableView;
    @FXML
    private Button button;
    private Scene thisScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void initData(ArrayList<Live> videos, Scene thisScene){
        final ObservableList<Live> cellData = FXCollections.observableArrayList(videos);
        //ObservableList<Video> items = tableView.getItems();

        this.thisScene = thisScene;

        TableColumn<Live,String> ID = new TableColumn<>("ID");

        TableColumn<Live,String> author = new TableColumn<>("Teacher");

        TableColumn<Live,String> dateTime = new TableColumn<>("DateTime");

        TableColumn<Live,String> number = new TableColumn<>("NumberOfClient");

        TableColumn<Live,String> description = new TableColumn<>("Description");

        ID.setCellValueFactory(new PropertyValueFactory<Live,String>("liveId"));
        author.setCellValueFactory(new PropertyValueFactory<Live,String>("instructId"));
        dateTime.setCellValueFactory(new PropertyValueFactory<Live, String>("date"));
        number.setCellValueFactory(new PropertyValueFactory<Live,String>("number"));
        description.setCellValueFactory(new PropertyValueFactory<Live,String>("description"));

        button.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        showVideo();
                    }
                }
        );

        tableView.setItems(cellData);
        tableView.getColumns().addAll(ID,author,dateTime,number);
        tableView.getSelectionModel().select(0);
    }

    public void showVideo(){
        Live chosen = tableView.getSelectionModel().getSelectedItem();

//        List.closeStage();
        System.out.println(chosen);
    }
}