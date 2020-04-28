package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Out;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> boxNerc;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    void cerca(ActionEvent event) {
    	
    	int id = boxNerc.getValue().getId();
    	Integer maxY = Integer.parseInt(txtYears.getText());
    	Integer maxH = Integer.parseInt(txtHours.getText());
    	
    	List<Out> result = model.calcolaOutages(id, maxY, maxH);
    	
    	if(result != null) {
    		txtResult.appendText("Tot people affected: "+model.getNumAffected()+"\n");
    		txtResult.appendText("Tot hours of outage: "+model.calcolaHours(result)+"\n");
    		
    		for(Out o : result) {
    			txtResult.appendText(o.toString());
    		}
    	} else {
    		txtResult.appendText("Non ho trovato alcun risultato.\n");
    	}
    }

    @FXML
    void initialize() {
        assert boxNerc != null : "fx:id=\"boxNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
     }

	public void setModel(Model model) {
		this.model = model;
		ObservableList<Nerc> listaNerc = FXCollections.observableArrayList(model.getNercList());
		boxNerc.setItems(listaNerc);		
	}
}
