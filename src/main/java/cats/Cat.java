package cats;

import javax.swing.ImageIcon;

public class Cat {
	private String id;
	private String url;
	private String apikey=System.getenv("API_KEY_CATS");
	private ImageIcon image;
	
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
