package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 测试下
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/application/RegisterAndLoginPageScene.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/application/ClientStartPageScene.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("GymSystem");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
