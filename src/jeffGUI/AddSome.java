package jeffGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddSome implements Runnable {
	
	private ObservableList<String> stuff = FXCollections.observableArrayList();
	
	

	public AddSome(ObservableList<String> stuff) {
		this.stuff = stuff;
	}

	@Override
	public void run() {
		
			try {
				stuff.add("User" + stuff.size());				
			} catch (Exception e) {
				
			}
			System.out.println("Yolo");
		
	}
	
	public ObservableList<String> getSome () {
		return stuff;
	}

}
