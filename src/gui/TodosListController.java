package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import api.Todos;
import api.TodosService;
import frameworkProject.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class TodosListController implements Initializable{	
	
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
    
    private TodosService service;
    
    private ObservableList<Todos>obsList;

    public void setTodosService(TodosService service) {
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
  		
  	} 	

}