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

public class PostsService {

	private static HttpURLConnection connection;	
	public ArrayList<Integer> id = new ArrayList();	
	public ArrayList<Integer> userId = new ArrayList();
	public ArrayList<String> title = new ArrayList();
	public ArrayList<String>body = new ArrayList();
	public int len;

	
	public void connection() {
		
		BufferedReader reader;
		 String line;
		 StringBuffer responseContent = new StringBuffer();
		 try {
			 URL url = new URL("https://jsonplaceholder.typicode.com/posts");
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
		
		JSONArray posts = new JSONArray(responseBody);
		len = posts.length();
		for(int i = 0; i < posts.length(); i++) {
			JSONObject post = posts.getJSONObject(i);
			id.add(post.getInt("id"));
			userId.add(post.getInt("userId"));
			title.add(post.getString("title"));	
			body.add(post.getString("body"));
			
			
		}
	
	}
		

	public List<Posts>findAll(){
		connection();
		List<Posts> list = new ArrayList();	
		for(int i = 0; i<len;i++) {
		list.add(new Posts(userId.get(i),id.get(i),title.get(i),body.get(i)));		
		}
		
		return list;
	}
}
