package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import api.AlbumsService;
import api.PostsService;
import api.TodosService;
import frameworkProject.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {
	 
	  @FXML
	    private MenuItem menuItemPosts;

	    @FXML
	    private MenuItem menuItemTodos;

	    @FXML
	    private MenuItem menuItemAlbums;

	    @FXML
	    void onMenuItemAlbumsAction() {
	    	loadView("/gui/AlbumsList.fxml", (AlbumsListController controller) -> {
				controller.setAlbumsService(new AlbumsService());
				controller.updateTableView();
	    		});
	    }

	 @FXML
	    void onMenuItemPostsAction() {
		 loadView("/gui/PostsList.fxml", (PostsListController controller) -> {
				controller.setPostsService(new PostsService());
				controller.updateTableView();
	    		});
	    
	    }

	    @FXML
	    void onMenuItemTodosAction() {
	    	loadView("/gui/TodosList.fxml", (TodosListController controller) -> {
				controller.setTodosService(new TodosService());
				controller.updateTableView();
	    		});
	    }
		   
	    
    @Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}
	
	private synchronized <T> void loadView (String absoluteName, Consumer<T> initializingAction ) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
		 
			Node mainMenu = mainVBox.getChildren().get(0);
		
			mainVBox.getChildren().clear();
			
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			
						
		}
		catch (IOException e) {
			Alerts.showAlert("Exceção de E/S", "Erro ao carregar tela", e.getMessage(), AlertType.ERROR);
		
		}
		
	}


}
