package fxml;

import com.iot.g89.List;
import com.iot.g89.SceneTransform;
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

import com.iot.g89.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * <p>Boundary.</p>
 * <p>UserInfoPageController class.</p>
 *
 * @version 0.5
 * @author xiaox
 */

public class UserListPageController implements Initializable {
    @FXML
    private TableView<User> tableView;

    @FXML
    private Button button;

    String[] IDs, types, sexes, tels;
    int[] ages, recharges;
    private Scene thisScene;
    private ArrayList<User> users;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initData(ArrayList<User> users, Scene thisScene){
        this.thisScene = thisScene;
        this.users = users;

        TableColumn<User,String> ID = new TableColumn<>("userId");
        TableColumn<User,String> password = new TableColumn<>("password");
        TableColumn<User,String> sex = new TableColumn<>("sex");
        TableColumn<User,String> tel = new TableColumn<>("phoneNumber");
        TableColumn<User,Integer> age = new TableColumn<>("age");
        TableColumn<User,Integer> recharge = new TableColumn<>("rechargeAmount");


        final ObservableList<User> cellData = FXCollections.observableArrayList(users);

        ID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        tel.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        recharge.setCellValueFactory(new PropertyValueFactory<>("rechargeAmount"));
        button.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        showUser();
                    }
                }
        );

        tableView.setItems(cellData);
        tableView.getColumns().addAll(ID,password,sex,tel,age,recharge);
        tableView.getSelectionModel().select(0);
    }

    public void showUser() {
        User chosen = tableView.getSelectionModel().getSelectedItem();
        SceneTransform.ToUserInfoPage(chosen.getUserId());
        List.closeStage();
    }
}
