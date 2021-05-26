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

import com.iot.g89.List;
import com.iot.g89.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserListPageController implements Initializable {
    @FXML
    private TableView<User> tableView;

    @FXML
    private Button button;

    String[] IDs, types, sexes, tels;
    int[] ages, recharges;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initData(ArrayList<User> users){
//        int i = 0;
//        for(User u: users){
//            IDs[i] = u.userid;
//            types[i] = u.userType;
//            sexes[i] = u.sex;
//            tels[i] = u.phoneNumber;
//            ages[i] = u.age;
//            recharges[i] = u.rechargeAmount;
//            i++;
//        }

        TableColumn<User,String> ID = new TableColumn<>("userid");
        TableColumn<User,String> password = new TableColumn<>("password");
        TableColumn<User,String> sex = new TableColumn<>("sex");
        TableColumn<User,String> tel = new TableColumn<>("phoneNumber");
        TableColumn<User,Integer> age = new TableColumn<>("age");
        TableColumn<User,Integer> recharge = new TableColumn<>("rechargeAmount");


        final ObservableList<User> cellData = FXCollections.observableArrayList(users);

//        for(User u:users){
//            System.out.println(u.userid+"\n");
//        }

            ID.setCellValueFactory(new PropertyValueFactory<>("userid"));
            password.setCellValueFactory(new PropertyValueFactory<>("password"));
            sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
            tel.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            age.setCellValueFactory(new PropertyValueFactory<>("age"));
            recharge.setCellValueFactory(new PropertyValueFactory<>("rechargeAmount"));
            button.setOnAction(
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            User chosen = tableView.getSelectionModel().getSelectedItem();
                            System.out.println(chosen.getUserid());
                            System.out.println("clicked!");
                        }
                    }
            );

//            buttons.setCellFactory(c ->{
//                TableCell<User, String> cell = new TableCell<User, String>(){
//                    protected void updateItem(String item, boolean empty){
//                        super.updateItem(item,empty);
//                        Button button = new Button("GO!");
//                        button.setOnAction(e ->{
//                            System.out.println("CLICKED!");
//                        });
//                        if(empty){
//                            setText(null);
//                            setGraphic(null);
//                        }else{
//                            this.setGraphic(button);
//                        }
//
//                    }
//                };
//                return cell;
//            });
                tableView.setItems(cellData);
                tableView.getColumns().addAll(ID,password,sex,tel,age,recharge);
                tableView.getSelectionModel().select(0);



        //tableView.setItems(cellData);

        //tableView.getItems().addAll();
    }
}


//buttons.setCellFactory(c -> new TableCell<User,User>(){
//private final Button button = new Button("GO!");
//
//protected void updateItem(User u, boolean empty){
//        super.updateItem(u, empty);
//
//        if(u == null){
//        setGraphic(null);
//        return;
//        }else{
//        setGraphic(button);
//        }
//        button.setOnAction(event ->{
//        System.out.println("CLICKED!");
//        });
//
//
//
//        }
//
//        });