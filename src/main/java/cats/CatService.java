package cats;

import java.io.IOException;

import javax.swing.JFrame;

import views.ViewFavoritesCats;
import views.ViewPhotoCat;

public class CatService {
	/**
	 * Return a Cat object
	 * */
	public static Cat seeCat() {
		try {
			return CatDAO.getCat();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void displayCatPhoto(JFrame previousWindow, Cat kitten) {
		ViewPhotoCat viewPhotocat=new ViewPhotoCat(previousWindow, kitten);
		viewPhotocat.setVisible(true);
	}
	
	public static void favoriteKitten(Cat kitten) {
		CatDAO.postFavoriteCat(kitten);
	}
	
	public static void displayListFavoriteKittens(JFrame previousWindow){
		CatFavorite[] catsList = CatDAO.getListFavoriteCats();
		ViewFavoritesCats viewFavorites= new ViewFavoritesCats(previousWindow, catsList);
		viewFavorites.setVisible(true);
	}

}
