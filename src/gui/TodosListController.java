package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import api.Todos;
import api.TodosService;
import frameworkProject.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class TodosListController implements Initializable{	
	
    @FXML
    private Button buttonMenu;
	
    @FXML
    private TableView<Todos> tableViewTodos;

    @FXML
    private TableColumn<Todos, Integer> tableColumnUserId;

    @FXML
    private TableColumn<Todos, Integer> tableColumnId;

    @FXML
    private TableColumn<Todos, String> tableColumnTitle;
    
    @FXML    
    private TableColumn<Todos, Boolean> tableColumnCompleted;
    
    @FXML
    private TextField filterUserIdField;

    @FXML
    private TextField filterIdField;

    @FXML
    private TextField filterTitleField;

    @FXML
    private TextField filterCompletedField;

    
    private TodosService service;
    
    private ObservableList<Todos>obsList;

    public void setTodosService(TodosService service) {
  		this.service = service;
  	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	 
  		tableColumnUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
  		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
  		tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title")); 
  		tableColumnCompleted.setCellValueFactory(new PropertyValueFactory<>("completed"));   		
  		
  		Stage stage = (Stage) Main.getMainScene().getWindow();
  		tableViewTodos.prefHeightProperty().bind(stage.heightProperty());		
 
  	}     
  	
  	public void updateTableView() {
  		if (service == null) {
  			throw new IllegalStateException("Serviço nulo");
  		}
  		List<Todos> list = service.findAll();
  		obsList = FXCollections.observableArrayList(list);
  		tableViewTodos.setItems(obsList);
  		
 		
  		FilteredList<Todos> filterData = new FilteredList<>(obsList, e-> true);
  		
  		filterTitleField.setOnKeyReleased(e ->{    			 
  		    	
  		    	 filterTitleField.textProperty().addListener((observableValue, oldValue, newValue) ->{
  		    		
  		    		filterData.setPredicate((Predicate<? super Todos>) todo ->{
  		    			
  		    			if(newValue == null || newValue.isEmpty()) {
  		    				return true;
  		    			}
  		    			
  		    			String lowerCaseFilter = newValue.toLowerCase();
  		    			
  		    			if(todo.getTitle().toLowerCase().contains(lowerCaseFilter)) {
  		    				return true;
  		    			}  		    			
  		    			return false;
  		    			
  		    		});
  		    		
  		    		SortedList<Todos>sortedData = new SortedList<>(filterData);
  		    		sortedData.comparatorProperty().bind(tableViewTodos.comparatorProperty());
  		    		tableViewTodos.setItems(sortedData);
  		    		  		   		
  		    	}); 
  		    	
  		    	tableViewTodos.setItems(filterData);  		  
	});
  		
  		filterUserIdField.setOnKeyReleased(e ->{    			 
		    	
		    	 filterUserIdField.textProperty().addListener((observableValue, oldValue, newValue) ->{
		    		
		    		filterData.setPredicate((Predicate<? super Todos>) todo ->{
		    			
		    			if(newValue == null || newValue.isEmpty()) {
		    				return true;
		    			}
		    			
		    			String lowerCaseFilter = newValue.toLowerCase();
		    			
		    			if(todo.getUserId().toString().toLowerCase().contains(lowerCaseFilter)) {
		    				return true;
		    			}  		    			
		    			return false;
		    			
		    		});
		    		
		    		SortedList<Todos>sortedData = new SortedList<>(filterData);
		    		sortedData.comparatorProperty().bind(tableViewTodos.comparatorProperty());
		    		tableViewTodos.setItems(sortedData);
		    		  		   		
		    	}); 
		    	
		    	tableViewTodos.setItems(filterData);  		  
		});
  		
  		filterIdField.setOnKeyReleased(e ->{    			 
		    	
  			filterIdField.textProperty().addListener((observableValue, oldValue, newValue) ->{
		    		
		    		filterData.setPredicate((Predicate<? super Todos>) todo ->{
		    			
		    			if(newValue == null || newValue.isEmpty()) {
		    				return true;
		    			}
		    			
		    			String lowerCaseFilter = newValue.toLowerCase();
		    			
		    			if(todo.getId().toString().toLowerCase().contains(lowerCaseFilter)) {
		    				return true;
		    			}  		    			
		    			return false;
		    			
		    		});
		    		
		    		SortedList<Todos>sortedData = new SortedList<>(filterData);
		    		sortedData.comparatorProperty().bind(tableViewTodos.comparatorProperty());
		    		tableViewTodos.setItems(sortedData);
		    		  		   		
		    	}); 
		    	
		    	tableViewTodos.setItems(filterData);  		  
		});
  		
  		filterCompletedField.setOnKeyReleased(e ->{    			 
		    	
  			filterCompletedField.textProperty().addListener((observableValue, oldValue, newValue) ->{
		    		
		    		filterData.setPredicate((Predicate<? super Todos>) todo ->{
		    			
		    			if(newValue == null || newValue.isEmpty()) {
		    				return true;
		    			}
		    			
		    			String lowerCaseFilter = newValue.toLowerCase();
		    			
		    			if(todo.getCompleted().toString().toLowerCase().contains(lowerCaseFilter)) {
		    				return true;
		    			}  		    			
		    			return false;
		    			
		    		});
		    		
		    		SortedList<Todos>sortedData = new SortedList<>(filterData);
		    		sortedData.comparatorProperty().bind(tableViewTodos.comparatorProperty());
		    		tableViewTodos.setItems(sortedData);
		    		  		   		
		    	}); 
		    	
		    	tableViewTodos.setItems(filterData);  		  
		});
  		
  		
  		
  	} 	
  	
    @FXML
    void onBtMenuAction(ActionEvent event) {
    	Stage stage = (Stage) Main.getMainScene().getWindow();
    	Main main = new Main();
		main.start(stage);
    }

}