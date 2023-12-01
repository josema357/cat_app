package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import cats.Cat;
import cats.CatService;

public class ViewPhotoCat extends JFrame{

	private static final long serialVersionUID = 1L;
	private Cat currentKitten=null;
	
	public ViewPhotoCat(JFrame previousWindow, Cat kitten) {
		currentKitten=kitten;
		//Window configuration
		setTitle("CatsApp - "+currentKitten.getId());
		setSize(514,294);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
					
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		//Button to see another image
		JButton btnOther = new JButton("Other");
		btnOther.setBounds(367, 88, 123, 21);
		desktopPane.add(btnOther);
		//Button to add favorite
		JButton btnFavorite = new JButton("Favorite");
		btnFavorite.setBounds(367, 119, 123, 21);
		desktopPane.add(btnFavorite);
		//Button to return to the main menu
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(367, 150, 123, 21);
		desktopPane.add(btnBack);
		//Label to display the image
		JLabel lblImage = new JLabel(currentKitten.getImage());
		lblImage.setBounds(10, 10, 347, 237);
		desktopPane.add(lblImage);
		
		//Action Listeners
		btnOther.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentKitten = CatService.seeCat();
				lblImage.setIcon(currentKitten.getImage());
		        setTitle("CatsApp - " + currentKitten.getId());
			}
		});
		btnFavorite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CatService.favoriteKitten(kitten);
			}
		});
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousWindow.setVisible(true);
				dispose();
			}
		});
	}
}
