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
	
	public ViewPhotoCat(JFrame previousWindow, Cat kitten) {
		//Window configuration
		setTitle("CatsApp - "+kitten.getId());
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
		JLabel lblImage = new JLabel(kitten.getImage());
		lblImage.setBounds(10, 10, 347, 237);
		desktopPane.add(lblImage);
		
		//Action Listeners
		btnOther.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cat newkitten = CatService.seeCat();
				if(newkitten!=null) {
					lblImage.setIcon(newkitten.getImage());
					setTitle("CatsApp - "+newkitten.getId());
				}
			}
		});
		btnFavorite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
