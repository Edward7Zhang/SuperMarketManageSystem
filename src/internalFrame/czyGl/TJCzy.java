package internalFrame.czyGl;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.lzw.dao.*;

import model.TbUserlist;
public class TJCzy extends JPanel {
	private JTextField logName;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField;
	private JTextField name;
	private void clear() {
		logName.setText(null);
		name.setText(null);
		passwordField_1.setText(null);
		passwordField.setText(null);
	}
	public TJCzy() {
		super();
		setLayout(new GridBagLayout());
		setBounds(0, 0, 280, 236);
		final JLabel label_4 = new JLabel();
		label_4.setFont(new Font("", Font.PLAIN, 14));
		label_4.setText("登录名称：");
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.gridx = 0;
		gridBagConstraints_10.gridy = 0;
		add(label_4, gridBagConstraints_10);
		logName = new JTextField();
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.weighty = 1.0;
		gridBagConstraints_11.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_11.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_11.gridwidth = 2;
		gridBagConstraints_11.gridy = 0;
		gridBagConstraints_11.gridx = 1;
		add(logName, gridBagConstraints_11);
		final JLabel label = new JLabel();
		label.setFont(new Font("", Font.PLAIN, 14));
		label.setText("操作员姓名：");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridx = 0;
		add(label, gridBagConstraints);
		name = new JTextField();
		name.setFont(new Font("", Font.PLAIN, 14));
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.weightx = 1.0;
		gridBagConstraints_1.weighty = 1.0;
		gridBagConstraints_1.ipadx = -250;
		gridBagConstraints_1.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_1.gridwidth = 2;
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 1;
		add(name, gridBagConstraints_1);
		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("", Font.PLAIN, 14));
		label_2.setText("输入密码：");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.gridy = 2;
		gridBagConstraints_4.gridx = 0;
		add(label_2, gridBagConstraints_4);
		passwordField = new JPasswordField();
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.weighty = 1.0;
		gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_5.gridwidth = 2;
		gridBagConstraints_5.gridy = 2;
		gridBagConstraints_5.gridx = 1;
		add(passwordField, gridBagConstraints_5);
		final JLabel label_3 = new JLabel();
		label_3.setFont(new Font("", Font.PLAIN, 14));
		label_3.setText("确认密码：");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.gridy = 3;
		gridBagConstraints_6.gridx = 0;
		add(label_3, gridBagConstraints_6);
		passwordField_1 = new JPasswordField();
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.weighty = 1.0;
		gridBagConstraints_7.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_7.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_7.gridwidth = 2;
		gridBagConstraints_7.gridy = 3;
		gridBagConstraints_7.gridx = 1;
		add(passwordField_1, gridBagConstraints_7);
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String tbName = logName.getText();
				String tbUsername = name.getText();
				String password = new String(passwordField.getPassword());
				TbUserlist user = Dao.getUser(tbName, password);
				if (user.getUsername() != null && !user.getUsername().isEmpty()) {
					JOptionPane.showMessageDialog(TJCzy.this,
							"此登录名称已经存在");
					name.setFocusable(true);
					return;
				}
				if (name.getText() == null || name.getText().equals("")) {
					JOptionPane.showMessageDialog(TJCzy.this, "用户名不能为空");
					name.setRequestFocusEnabled(true);
					return;
				}
				if (passwordField.getPassword().length == 0) {
					JOptionPane.showMessageDialog(TJCzy.this, "密码不能为空");
				} else if (passwordField.getText().equals(
						passwordField_1.getText())) {
					TbUserlist tbUserlist = new TbUserlist();
					tbUserlist.setQuan("c");
					tbUserlist.setPass(passwordField_1.getText());
					tbUserlist.setName(name.getText().trim());
					tbUserlist.setUsername(logName.getText().trim());
					Dao.addUser(tbUserlist);
					JOptionPane.showMessageDialog(TJCzy.this, "操作员添加成功");
					clear();
				} else {
					JOptionPane
							.showMessageDialog(TJCzy.this, "两次密码输入不相同");
				}
			}
		});
		button.setText("添加");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.weighty = 1.0;
		gridBagConstraints_8.anchor = GridBagConstraints.EAST;
		gridBagConstraints_8.gridy = 5;
		gridBagConstraints_8.gridx = 1;
		add(button, gridBagConstraints_8);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				clear();
			}
		});
		button_1.setText("重写");
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.weighty = 1.0;
		gridBagConstraints_9.gridy = 5;
		gridBagConstraints_9.gridx = 2;
		add(button_1, gridBagConstraints_9);
	}
}
