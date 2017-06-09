package com.lzw;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import model.TbUserlist;
import com.lzw.dao.Dao;
public class Login extends JFrame {
	private JLabel userLabel;
	private JLabel passLabel;
	private JButton exit;
	private JButton login;
	private Main window;
	private static TbUserlist user;
	public Login() {
		setIconImage(new ImageIcon("res/Img/超市进销存管理系统.png").getImage());
		setTitle("登录企业进销存管理系统");
		final JPanel panel = new LoginPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300, 200, panel.getWidth(), panel.getHeight());
		userLabel = new JLabel();
		userLabel.setForeground(Color.WHITE);
		userLabel.setText("用户名：");
		userLabel.setBounds(100, 110, 200, 18);
		panel.add(userLabel);
		final JTextField userName = new JTextField();
		userName.setBounds(150, 110, 200, 18);
		panel.add(userName);
		passLabel = new JLabel();
		passLabel.setForeground(Color.WHITE);
		passLabel.setText("密  码：");
		passLabel.setBounds(100, 140, 200, 18);
		panel.add(passLabel);
		final JPasswordField userPassword = new JPasswordField();
		userPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		userPassword.setBounds(150, 140, 200, 18);
		panel.add(userPassword);
		login = new JButton();
		login.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				user = Dao.getUser(userName.getText(), userPassword.getText());
				if (user.getUsername() == null || user.getName() == null) {
					userName.setText(null);
					userPassword.setText(null);
					return;
				}
				setVisible(false);
				window = new Main();
				window.frame.setVisible(true);
			}
		});
		login.setText("登录");
		login.setBounds(180, 170, 60, 18);
		panel.add(login);
		exit = new JButton();
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setText("退出");
		exit.setBounds(260, 170, 60, 18);
		panel.add(exit);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}
	public static TbUserlist getUser() {
		return user;
	}
	public static void setUser(TbUserlist user) {
		Login.user = user;
	}
}
