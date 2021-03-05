package gui;

import javafx.scene.control.Button;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import api.Albums;
import api.AlbumsService;
import frameworkProject.Main;

public class AlbumsListController implements Initializable{	
	

    @FXML
    private Button buttonMenu;
    
    @FXML
    private TextField filterTitleField;

    @FXML
    private TextField filterIdField;

    @FXML
    private TextField filterUserIdField;
    
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
  		
  		FilteredList<Albums> filterData = new FilteredList<>(obsList, e-> true);
  		filterTitleField.setOnKeyReleased(e ->{    			 
  		    	
  		    	 filterTitleField.textProperty().addListener((observableValue, oldValue, newValue) ->{
  		    		
  		    		filterData.setPredicate((Predicate<? super Albums>) album ->{
  		    			
  		    			if(newValue == null || newValue.isEmpty()) {
  		    				return true;
  		    			}
  		    			
  		    			String lowerCaseFilter = newValue.toLowerCase();
  		    			
  		    			if(album.getTitle().toLowerCase().contains(lowerCaseFilter)) {
  		    				return true;
  		    			}  		    			
  		    			return false;
  		    			
  		    		});
  		    		
  		    		SortedList<Albums>sortedData = new SortedList<>(filterData);
  		    		sortedData.comparatorProperty().bind(tableViewAlbums.comparatorProperty());
  		    		tableViewAlbums.setItems(sortedData);
  		    		  		   		
  		    	}); 
  		    	
  		    	tableViewAlbums.setItems(filterData);  		  
  		});
  		
  		filterUserIdField.setOnKeyReleased(e ->{    			 
		    	
  			filterUserIdField.textProperty().addListener((observableValue, oldValue, newValue) ->{
		    		
		    		filterData.setPredicate((Predicate<? super Albums>) album ->{
		    			
		    			if(newValue == null || newValue.isEmpty()) {
		    				return true;
		    			}
		    			
		    			String lowerCaseFilter = newValue.toLowerCase();
		    			
		    			if(album.getUserId().toString().toLowerCase().contains(lowerCaseFilter)) {
		    				return true;
		    			}  		    			
		    			return false;
		    			
		    		});
		    		
		    		SortedList<Albums>sortedData = new SortedList<>(filterData);
		    		sortedData.comparatorProperty().bind(tableViewAlbums.comparatorProperty());
		    		tableViewAlbums.setItems(sortedData);
		    		  		   		
		    	}); 
		    	
		    	tableViewAlbums.setItems(filterData);  		  
		});
  		
  		filterIdField.setOnKeyReleased(e ->{    			 
	    	
  			filterIdField.textProperty().addListener((observableValue, oldValue, newValue) ->{
		    		
		    		filterData.setPredicate((Predicate<? super Albums>) album ->{
		    			
		    			if(newValue == null || newValue.isEmpty()) {
		    				return true;
		    			}
		    			
		    			String lowerCaseFilter = newValue.toLowerCase();
		    			
		    			if(album.getId().toString().toLowerCase().contains(lowerCaseFilter)) {
		    				return true;
		    			}  		    			
		    			return false;
		    			
		    		});
		    		
		    		SortedList<Albums>sortedData = new SortedList<>(filterData);
		    		sortedData.comparatorProperty().bind(tableViewAlbums.comparatorProperty());
		    		tableViewAlbums.setItems(sortedData);
		    		  		   		
		    	}); 
		    	
		    	tableViewAlbums.setItems(filterData);  		  
		});
  	} 	  	

    @FXML
    void onBtMenuAction(ActionEvent event) {
    	Stage stage = (Stage) Main.getMainScene().getWindow();
    	Main main = new Main();
		main.start(stage);
    }  
    
 

  
   

}
