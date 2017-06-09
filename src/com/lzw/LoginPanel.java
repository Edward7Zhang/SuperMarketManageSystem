package com.lzw;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class LoginPanel extends JPanel {
	protected ImageIcon icon = new ImageIcon("res/login.jpg");
	public int width = icon.getIconWidth(), height = icon.getIconHeight();
	public LoginPanel() {
		super();
		setSize(width, height);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = icon.getImage();
		g.drawImage(img, 0, 0,getParent());
	}
}