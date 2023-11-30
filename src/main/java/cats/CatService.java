package cats;

import java.io.IOException;

import javax.swing.JFrame;

import views.ViewPhotoCat;

public class CatService {
	public static Cat seeCat() {
		//Return a Cat object
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
}
