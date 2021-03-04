package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TodosService {
	
	private static HttpURLConnection connection;	
	public ArrayList<Integer> id = new ArrayList();	
	public ArrayList<Integer> userId = new ArrayList();
	public ArrayList<String> title = new ArrayList();
	public ArrayList<Boolean>completed = new ArrayList();
	public int len;

	
	public void connection() {
		
		BufferedReader reader;
		 String line;
		 StringBuffer responseContent = new StringBuffer();
		 try {
			 URL url = new URL("https://jsonplaceholder.typicode.com/todos");
			 connection = (HttpURLConnection) url.openConnection();
			 
			 connection.setRequestMethod("GET");
			 connection.setConnectTimeout(5000);
			 connection.setReadTimeout(5000);
			 
			 int status = connection.getResponseCode();
			 
			 if(status>299) {
				 reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				 while((line = reader.readLine())!=null) {
					 responseContent.append(line);
				 }
				 reader.close();
			 }else {
				 reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				 while((line = reader.readLine()) != null) {
					 responseContent.append(line);
				 }
				 reader.close();
			 }
			 
			 parse(responseContent.toString());
			 
		 }catch(MalformedURLException e) {
			 e.printStackTrace();
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		}
	
	
		public void parse(String responseBody) {
		
		JSONArray todos = new JSONArray(responseBody);
		len = todos.length();
		for(int i = 0; i < todos.length(); i++) {
			JSONObject todo = todos.getJSONObject(i);
			id.add(todo.getInt("id"));
			userId.add(todo.getInt("userId"));
			title.add(todo.getString("title"));	
			completed.add(todo.getBoolean("completed"));			
			
		}
	
	}
		

	public List<Todos>findAll(){
		connection();
		List<Todos> list = new ArrayList();	
		for(int i = 0; i<len;i++) {
		list.add(new Todos(userId.get(i),id.get(i),title.get(i),completed.get(i)));		
		}
		
		return list;
	}
}
