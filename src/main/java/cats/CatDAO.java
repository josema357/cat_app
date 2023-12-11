package cats;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import utilities.ImageIconAdapter;

public class CatDAO {
	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(ImageIcon.class, new ImageIconAdapter())
			.create();
	/**
	 * This method gets data from the API
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
            Image scaledImage = image.getScaledInstance(300, 237, Image.SCALE_SMOOTH);
            ImageIcon resizedImage=new ImageIcon(scaledImage);
			cat.setImage(resizedImage);
		} catch (Exception e) {
			System.out.println(e);
		}
		return cat;
	}
	
	/**
	 * This method is used to mark a cat as a favorite
	 * */
	public static void postFavoriteCat(Cat cat) {
		try {
			OkHttpClient client = new OkHttpClient().newBuilder()
					  .build();
					MediaType mediaType = MediaType.parse("application/json");
					RequestBody body = createRequestBody(mediaType, "{\n\t\"image_id\": \""+cat.getId()+"\"\n}");
					Request request = new Request.Builder()
					  .url("https://api.thecatapi.com/v1/favourites")
					  .method("POST", body)
					  .addHeader("Content-Type", "application/json")
					  .addHeader("x-api-key", cat.getApikey())
					  .build();
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * This method is used to get a list of favorite kittens
	 * */
	public static CatFavorite[] getListFavoriteCats() {
		try {
			OkHttpClient client = new OkHttpClient().newBuilder()
					  .build();
					Request request = new Request.Builder()
					  .url("https://api.thecatapi.com/v1/favourites")
					  .get()
					  .addHeader("Content-Type", "application/json")
					  .addHeader("x-api-key", System.getenv("API_KEY_CATS"))
					  .build();
			Response response = client.newCall(request).execute();
			String json=response.body().string();
			CatFavorite[] catsArray=gson.fromJson(json, CatFavorite[].class);
			for(CatFavorite kitten: catsArray) {
				//Get image to the API
				try {
					// Load the image from the URL
		            ImageIcon imageIcon = new ImageIcon(new URL(kitten.getImage().getUrl()));
		            Image image = imageIcon.getImage();
		            //Set image size
		            Image scaledImage = image.getScaledInstance(300, 237, Image.SCALE_SMOOTH);
		            ImageIcon resizedImage=new ImageIcon(scaledImage);
					kitten.getImage().setImage(resizedImage);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			return catsArray;
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * This method is used to delete a cat from the list of favorites
	 * */
	public static void deleteFavoriteCat(CatFavorite catFav) {
		try {
			OkHttpClient client = new OkHttpClient().newBuilder()
					  .build();
					MediaType mediaType = MediaType.parse("application/json");
					RequestBody body = createRequestBody(mediaType, "");
					Request request = new Request.Builder()
					  .url("https://api.thecatapi.com/v1/favourites/"+catFav.getId())
					  .method("DELETE", body)
					  .addHeader("Content-Type", "application/json")
					  .addHeader("x-api-key", System.getenv("API_KEY_CATS"))
					  .build();
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * This method is used to create the body in the postFavoriteCat method
	 * */
	private static RequestBody createRequestBody(final MediaType mediaType, final String content) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8(content);
            }
        };
    }
}
