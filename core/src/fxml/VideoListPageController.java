package fxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.iot.g89.Video;

public class VideoListPageController implements Initializable {


    @FXML
    private TableView<Video> tableView;
    @FXML
    private Button button;
//    @FXML
//    private TableColumn<Video,String> ID;
//    @FXML
//    private TableColumn<Video,String> type;
//    @FXML
//    private TableColumn<Video,String> author;
//    @FXML
//    private TableColumn<Video,String> client;
//    @FXML
//    private TableColumn<Video,String> buttons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void initData(ArrayList<Video> videos){
        final ObservableList<Video> cellData = FXCollections.observableArrayList(videos);
        //ObservableList<Video> items = tableView.getItems();


        TableColumn<Video,String> ID = new TableColumn<>("id");

        TableColumn<Video,String> type = new TableColumn<>("Type");

        TableColumn<Video,String> author = new TableColumn<>("Author");

        TableColumn<Video,String> client = new TableColumn<>("Client");

        //TableColumn<Video,String> buttons = new TableColumn<>("Buttons");



            ID.setCellValueFactory(new PropertyValueFactory<Video,String>("videoId"));
            type.setCellValueFactory(new PropertyValueFactory<Video,String>("videoType"));
            author.setCellValueFactory(new PropertyValueFactory<Video,String>("author"));
            client.setCellValueFactory(new PropertyValueFactory<Video,String>("specificClient"));

        button.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Video chosen = tableView.getSelectionModel().getSelectedItem();
                        System.out.println(chosen.getVideoId());
                        System.out.println("clicked!");
                    }
                }
        );

            //buttons.setCellFactory(new PropertyValueFactory<Button,String>());
//            buttons.setCellFactory(c ->{
//                TableCell<Video, String> cell = new TableCell<Video, String>(){
//                    protected void updateItem(String item, boolean empty){
//                        super.updateItem(item,empty);
//                        Button button = new Button("GO!");
//                        button.setOnAction(e ->{
//                            System.out.println("CLICKED!");
//                            Video chosen = tableView.getSelectionModel().getSelectedItem();
//                            System.out.println(chosen.getVideoId());
//                        });
//                        if(empty){
//                            setText(null);
//                            setGraphic(null);
//                        }else{
//                            this.setGraphic(button);
//                        }
//                    }
//                };
//                return cell;
//            });
            //items.add(v);

        tableView.setItems(cellData);
        tableView.getColumns().addAll(ID,type,author,client);
        tableView.getSelectionModel().select(0);

    }
}