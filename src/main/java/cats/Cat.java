package cats;

import javax.swing.ImageIcon;

public class Cat {
	String id;
	String url;
	String apikey=System.getenv("API_KEY_CATS");
	ImageIcon image;
	
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getApikey() {
		return apikey;
	}
}
