package jeffGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RemSome implements Runnable {
	
	private ObservableList<String> stuff = FXCollections.observableArrayList();
	

	public RemSome(ObservableList<String> stuff) {
		this.stuff = stuff;
	}

	@Override
	public void run() {
		for(int i = 0; i < 1000; i++) {
			stuff.remove(stuff.size()-1);
		}
		
	}
	
	public ObservableList<String> getSome () {
		return stuff;
	}

}
