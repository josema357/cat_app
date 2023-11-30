package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import cats.Cat;
import cats.CatService;

import javax.swing.JButton;

public class ViewMenuCat extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public ViewMenuCat() {
		//Window configuration
		setTitle("CatsApp");
		setSize(514,294);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
			
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		//Button to see cats
		JButton btnSeeCat = new JButton("See cat");
		btnSeeCat.setBounds(173, 104, 138, 29);
		desktopPane.add(btnSeeCat);
		
		//Action to the button "See Cat"
		btnSeeCat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cat kitten =CatService.seeCat();
				CatService.displayCatPhoto(ViewMenuCat.this, kitten);
				dispose();
			}
		});
	}
}
