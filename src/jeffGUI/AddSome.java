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
		for(int i = 1; i <= 1000; i++) {
			stuff.add("User" + i);
		}
	}
	
	public ObservableList<String> getSome () {
		return stuff;
	}

}
