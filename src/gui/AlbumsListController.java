package gui;


import javafx.scene.control.Button;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import api.Albums;
import api.AlbumsService;
import frameworkProject.Main;

public class AlbumsListController implements Initializable{	
	

    @FXML
    private Button buttonMenu;
    
    @FXML
    private TableView<Albums> tableViewAlbums;

    @FXML
    private TableColumn<Albums, Integer> tableColumnUserId;

    @FXML
    private TableColumn<Albums, Integer> tableColumnId;

    @FXML
    private TableColumn<Albums, String> tableColumnTitle;
    
    private AlbumsService service;
    
    private ObservableList<Albums>obsList;

    public void setAlbumsService(AlbumsService service) {
  		this.service = service;
  	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  		initializeNodes();  		
  		
  	}    
 
  	private void initializeNodes() {
 
  		tableColumnUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
  		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
  		tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));   		
  		
  		Stage stage = (Stage) Main.getMainScene().getWindow();
  		tableViewAlbums.prefHeightProperty().bind(stage.heightProperty());

  	}
  	
  	public void updateTableView() {
  		if (service == null) {
  			throw new IllegalStateException("Serviço nulo");
  		}
  		List<Albums> list = service.findAll();
  		obsList = FXCollections.observableArrayList(list);
  		tableViewAlbums.setItems(obsList);
  		
  	} 	  	

    @FXML
    void onBtMenuAction(ActionEvent event) {
    	Stage stage = (Stage) Main.getMainScene().getWindow();
    	Main main = new Main();
		main.start(stage);
    }

}
