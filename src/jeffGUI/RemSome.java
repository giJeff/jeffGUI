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
		try {
		if(stuff.size() != 0) {
			stuff.remove(stuff.size()-1);			
		}
		} catch (Exception e) {
			
		}
		System.out.println("++++++++++");
	}
	
	public ObservableList<String> getSome () {
		return stuff;
	}

}
