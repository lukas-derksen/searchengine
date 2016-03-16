/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoekmachine;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

/**
 *
 * @author lukas
 */
public class ZoekmachineGUIController implements Initializable {
    @FXML private Button btnBrowse;
    @FXML private Button btnSearch;
    @FXML private TextField txtBrowse;
    @FXML private ListView lvResults;
    @FXML private TextField txtSearch;
    @FXML private Button btnSave;
    
    String path;
    
    @FXML
    private void openDirectoryBrowser(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        String path = dc.showDialog(btnBrowse.getScene().getWindow()).toString();
        
        txtBrowse.setText(path);
        this.path = path;
    }
    
    @FXML
    private void search(ActionEvent event) {
        String word = txtSearch.getText();
        Search search = new Search(word, path);
        
        search.clearList();
        
        try {
            search.searchLoop(path);
            
            ObservableList results = search.getList();
            if (results.size() != 0)
                lvResults.setItems(search.getList());
            else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Geen resultaat");
                alert.setContentText("Er zijn geen resultaten gevonden van de gevraagde zoekopdracht.");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
