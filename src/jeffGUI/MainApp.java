package jeffGUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

	// Make some buttons to use else where
	private Button btnApply = new Button("Apply");
	private Button btnExit = new Button("Exit");

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		primaryStage.setTitle("jeffGUI");

		TabPane tabs = new TabPane();

		Tab tabHome = new Tab();
		tabHome.setText("Home");
		tabHome.setContent(home());

		Tab tabLogin = new Tab();
		tabLogin.setText("Login");
		tabLogin.setContent(login());

		tabs.getTabs().addAll(tabHome, tabLogin);

		Scene scene = new Scene(tabs, 600, 800); // Set the scene size here
		primaryStage.setTitle("jeffGUI");
		primaryStage.setScene(scene);
		primaryStage.show();
	}	

	/*
	 * create a pane named home that will serve as a simple CRUD app
	 */
	private Pane home() {

		BorderPane border = new BorderPane();
		border.setPadding(new Insets(20, 20, 20, 20));

		VBox vb = new VBox(20);

		HBox hb = new HBox();
		Label labelAdd = new Label("Add: ");
		TextField textAdd = new TextField();
		textAdd.setPrefWidth(425);
		hb.getChildren().addAll(labelAdd, textAdd);

		ListView<String> list = new ListView<String>(); 
		ObservableList<String> stuff = FXCollections.observableArrayList (
				"Data", "Data1", "Data2", 
				"Data3", "Data4");
		list.setItems(stuff);
		list.setMaxHeight(Control.USE_PREF_SIZE);
		list.setPrefWidth(450.0);

		vb.getChildren().addAll(hb, list);	

		Image img = new Image("http://mikecann.co.uk/wp-content/uploads/2009/12/javafx_logo_color_1.jpg");
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(550);
		imgView.setPreserveRatio(true); // keep picture ratio
		imgView.setSmooth(true);
		imgView.setCache(true); //cached for speed

		border.setTop(imgView); 
		BorderPane.setMargin(imgView, new Insets(0, 0, 20, 0));
		border.setLeft(vb);    
		border.setRight(createRightMenu());  
		border.setBottom(createBottomMenu()); 

		return border;
	}

	/*
	 * Creates login page because I think its required but not 100% sure? 
	 * will need to get creative with formatting to make it look good
	 */
	private Pane login() {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);  
		grid.setHgap(10);
		grid.setVgap(12);

		ColumnConstraints c1 = new ColumnConstraints();
		c1.setHalignment(HPos.RIGHT);  
		grid.getColumnConstraints().add(c1); 

		ColumnConstraints c2 = new ColumnConstraints();
		c2.setHalignment(HPos.LEFT);  
		grid.getColumnConstraints().add(c2); 

		HBox hb = new HBox();
		hb.setSpacing(10.0);
		hb.setAlignment(Pos.CENTER);

		//make buttons
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button exit = new Button("Exit");

		//make login area
		Label labelName = new Label("Username:");
		TextField textName = new TextField();
		Label labelPass = new Label("Password:");
		PasswordField textPass = new PasswordField();

		hb.getChildren().addAll(submit, clear, exit);

		grid.add(labelName, 0, 0);
		grid.add(textName, 1, 0);
		grid.add(labelPass, 0, 1);
		grid.add(textPass, 1, 1);
		grid.add(hb, 0, 2, 2, 1);

		hb.setAlignment(Pos.BOTTOM_CENTER);

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.add(hb, 0, 0);
		grid.add(gp, 0, 2, 2, 1);

		return grid;
	}

	/*
	 * Creates buttons and sets them all to the same width
	 */
	private VBox createRightMenu() { 

		//make buttons
		Button add = new Button("Add");
		Button delete = new Button("Delete");
		Button up = new Button("Move Up");
		Button down = new Button("Move Down");

		// make all buttons the same size
		add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		delete.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		up.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		down.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		down.setMinWidth(Control.USE_PREF_SIZE);

		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(0, 20, 10, 20)); 
		vb.getChildren().addAll(add, delete, up, down);

		return vb;
	}

	/*
	 * Create buttons and set them to the same size
	 */
	private TilePane createBottomMenu() {

		btnApply.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btnExit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		TilePane tp = new TilePane(Orientation.HORIZONTAL);
		tp.setPadding(new Insets(20, 10, 20, 0));
		tp.setHgap(10.0);
		tp.getChildren().addAll(btnApply, btnExit);

		return tp;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
