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

public class AlbumsService {	
	
	private static HttpURLConnection connection;	
	public ArrayList<Integer> id = new ArrayList();	
	public ArrayList<Integer> userId = new ArrayList();
	public ArrayList<String> title = new ArrayList();
	public int len;

	
	public void connection() {
		
		BufferedReader reader;
		 String line;
		 StringBuffer responseContent = new StringBuffer();
		 try {
			 URL url = new URL("https://jsonplaceholder.typicode.com/albums");
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
		
		JSONArray albums = new JSONArray(responseBody);
		len = albums.length();
		for(int i = 0; i < albums.length(); i++) {
			JSONObject album = albums.getJSONObject(i);
			id.add(album.getInt("id"));
			userId.add(album.getInt("userId"));
			title.add(album.getString("title"));				
			
		}
	
	}
		

	public List<Albums>findAll(){
		connection();
		List<Albums> list = new ArrayList();	
		for(int i = 0; i<len;i++) {
		list.add(new Albums(userId.get(i),id.get(i),title.get(i)));		
		}
		
		return list;
	}

}
 

