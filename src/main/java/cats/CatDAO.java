package cats;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import utilities.ImageIconAdapter;

public class CatDAO {
	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(ImageIcon.class, new ImageIconAdapter())
			.create();
	/**
	 * This function gets data from the API
	 * We import IOException because a call is made outside the application to on API
	 * */
	public static Cat getCat() throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();
		Response response = client.newCall(request).execute();
		
		String json=response.body().string();
		//remove "[]" to the response
		json=json.substring(1,json.length()-1);
		
		//create object to the Gson class
		Cat cat = gson.fromJson(json, Cat.class);
		
		//Get image to the API
		try {
			// Load the image from the URL
            ImageIcon imageIcon = new ImageIcon(new URL(cat.getUrl()));
            Image image = imageIcon.getImage();
            //Set image size
            Image scaledImage = image.getScaledInstance(237, 237, Image.SCALE_SMOOTH);
            ImageIcon resizedImage=new ImageIcon(scaledImage);
			cat.setImage(resizedImage);
		} catch (Exception e) {
			System.out.println(e);
		}
		return cat;
	}
}
