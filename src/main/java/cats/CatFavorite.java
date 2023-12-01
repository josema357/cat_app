package cats;

import utilities.FavoriteImage;

public class CatFavorite {
	private int id;
	private String image_id;
	private FavoriteImage image;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	public FavoriteImage getImage() {
		return image;
	}
	public void setImage(FavoriteImage image) {
		this.image = image;
	}
	
}
