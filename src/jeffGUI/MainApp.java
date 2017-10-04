package jeffGUI;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
		ObservableList<String> stuff = FXCollections.observableArrayList ();
		// list.setItems(stuff);
		list.setMaxHeight(Control.USE_PREF_SIZE);
		list.setPrefWidth(450.0);
		
		for(int i = 1; i<=1000; i++) {
		AddSome addSome = new AddSome(stuff);
		RemSome remSome = new RemSome(stuff);
		Thread t1 = new Thread(addSome);
		Thread t2 = new Thread(remSome);
		t1.start();	
		t2.start();
		}
		
		list.setItems(stuff);
				
		vb.getChildren().addAll(hb, list);	

		Image img = new Image("http://mikecann.co.uk/wp-content/uploads/2009/12/javafx_logo_color_1.jpg");
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(550);
		imgView.setPreserveRatio(true); // keep picture ratio
		imgView.setSmooth(true);
		imgView.setCache(true); //cached for speed

		spin(imgView);

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

		VBox right = new VBox();
		right.setSpacing(10);
		right.setPadding(new Insets(0, 20, 10, 20)); 
		right.getChildren().addAll(add, delete, up, down);

		crud(textAdd, list, stuff, add, delete, up, down);

		border.setTop(imgView); 
		BorderPane.setMargin(imgView, new Insets(0, 0, 20, 0));
		border.setLeft(vb);    
		border.setRight(right);  
		border.setBottom(createBottomMenu()); 

		return border;
	}

	private void spin(ImageView imgView) {
		/*
		 * Start rotation code
		 * This is part of homework 2 part 1
		 * Anonymous inner class stuff not lambdas
		 */
		RotateTransition rotate = new RotateTransition(Duration.seconds(1), imgView);
		rotate.setByAngle(360);
		rotate.setCycleCount(Animation.INDEFINITE);
		rotate.setInterpolator(Interpolator.LINEAR);

		//On mouse hover imgView will spin.
		imgView.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				rotate.play();
			}
		});

		imgView.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				rotate.stop();
				imgView.setRotate(0);
			}
		});
	}

	private void crud(TextField textAdd, ListView<String> list, ObservableList<String> stuff, Button add, Button delete,
			Button up, Button down) {
		/*
		 * Start CRUD code
		 * This is part of homework 2 part 2
		 * lambdas!!!
		 * 
		 * in a normal world I would make each of these a method but to keep it together I did an all in one
		 */
		add.setOnAction(e-> {
			String s = textAdd.getText();
			if(!s.isEmpty()) {
				stuff.add(s);
			} 
			textAdd.clear();

		});

		delete.setOnAction(e-> {
			boolean check = list.getSelectionModel().isEmpty();
			if(stuff.size()>=1 && !check) {
				int index = list.getSelectionModel().getSelectedIndex();
				list.getItems().remove(index);
			}

		});

		up.setOnAction(e-> {
			int index = list.getSelectionModel().getSelectedIndex();
			boolean check = list.getSelectionModel().isEmpty();
			if(index>=1 && !check) {
				String hold = list.getSelectionModel().getSelectedItem();
				list.getItems().remove(index);
				stuff.add(index-1, hold);
				list.getSelectionModel().select(index-1);
			}
		});

		down.setOnAction(e-> {
			int index = list.getSelectionModel().getSelectedIndex();
			boolean check = list.getSelectionModel().isEmpty();
			if(index<stuff.size()-1 && !check) {
				String hold = list.getSelectionModel().getSelectedItem();
				list.getItems().remove(index);
				stuff.add(index+1, hold);
				list.getSelectionModel().select(index+1);
			}
		});
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