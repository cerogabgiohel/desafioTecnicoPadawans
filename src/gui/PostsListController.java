package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import api.Posts;
import api.PostsService;
import frameworkProject.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PostsListController implements Initializable {

	@FXML
    private Button buttonMenu;
	
    @FXML
    private TableView<Posts> tableViewPosts;

    @FXML
    private TableColumn<Posts, Integer> tableColumnUserId;

    @FXML
    private TableColumn<Posts, Integer> tableColumnId;

    @FXML
    private TableColumn<Posts, String> tableColumnTitle;

    @FXML
    private TableColumn<Posts, String> tableColumnBody;
    
    private PostsService service;
    
    private ObservableList<Posts>obsList;   
    
    public void setPostsService(PostsService service) {
  		this.service = service;
  	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  		tableColumnUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
  		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
  		tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title")); 
  		tableColumnBody.setCellValueFactory(new PropertyValueFactory<>("body"));  		
  		
  		Stage stage = (Stage) Main.getMainScene().getWindow();
  		tableViewPosts.prefHeightProperty().bind(stage.heightProperty());		
  		
  	}    
  	
  	public void updateTableView() {
  		if (service == null) {
  			throw new IllegalStateException("Serviço nulo");
  		}
  		List<Posts> list = service.findAll();
  		obsList = FXCollections.observableArrayList(list);
  		tableViewPosts.setItems(obsList);
  		
  	} 
  	
    @FXML
    void onBtMenuAction(ActionEvent event) {
    	Stage stage = (Stage) Main.getMainScene().getWindow();
    	Main main = new Main();
		main.start(stage);
    }
}