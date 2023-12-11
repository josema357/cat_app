package views;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import cats.CatDAO;
import cats.CatFavorite;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ViewFavoritesCats extends JFrame {
	private static final long serialVersionUID = 1L;
	private int currentIndex=0;
	
	public ViewFavoritesCats(JFrame previousWindow, CatFavorite[] catsList) {
		//Window configuration
		setTitle("CatsApp - "+catsList[currentIndex].getImage().getId());
		setSize(514,294);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 10, 85, 21);
		desktopPane.add(btnBack);
		
		JButton btnDeleteFavorite = new JButton("Delete");
		btnDeleteFavorite.setBounds(210, 10, 85, 21);
		desktopPane.add(btnDeleteFavorite);
		
		JButton btnNextFavorite = new JButton("Next");
		btnNextFavorite.setBounds(405, 10, 85, 21);
		desktopPane.add(btnNextFavorite);
		
		JLabel lblFavoriteCat = new JLabel(catsList[currentIndex].getImage().getImage());
		lblFavoriteCat.setHorizontalAlignment(SwingConstants.CENTER);
		lblFavoriteCat.setBounds(10, 41, 480, 206);
		desktopPane.add(lblFavoriteCat);
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousWindow.setVisible(true);
				dispose();
			}
		});
		
		btnNextFavorite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentIndex<catsList.length-1) {
					currentIndex++;
					setTitle("CatsApp - "+catsList[currentIndex].getImage().getId());
					lblFavoriteCat.setIcon(catsList[currentIndex].getImage().getImage());
				}else {
					currentIndex=0;
					setTitle("CatsApp - "+catsList[currentIndex].getImage().getId());
					lblFavoriteCat.setIcon(catsList[currentIndex].getImage().getImage());
				}
			}
		});
		
		btnDeleteFavorite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CatDAO.deleteFavoriteCat(catsList[currentIndex]);
				previousWindow.setVisible(true);
				dispose();
				
			}
		});
		
	}
}
