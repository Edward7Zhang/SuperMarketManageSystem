package internalFrame;

import internalFrame.guanli.Item;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import model.TbUserlist;
import com.lzw.dao.Dao;
public class QuanManager extends JInternalFrame {
	private JTextField userName;
	private JComboBox quanXian;
	private JTextField name;
	private JTextField pass;
	private JButton modifyButton;
	private JButton closeButton;
	private JComboBox userCombo;
	public QuanManager() {
		setClosable(true);
		setIconifiable(true);
		setBounds(10, 10, 420, 170);
		setTitle("权限管理");
		setLayout(new GridBagLayout());
		setVisible(true);

		final JLabel khName = new JLabel();
		khName.setText("用户姓名：");
		setupComponet(khName, 0, 0, 1, 0, false);
		userName = new JTextField();
		userName.setEditable(false);
		setupComponet(userName, 1, 0, 1, 100, true);

		final JLabel addressLabel = new JLabel("登录名：");
		setupComponet(addressLabel, 2, 0, 1, 0, false);
		name = new JTextField();
		name.setEditable(false);
		setupComponet(name, 3, 0, 1, 100, true);

		setupComponet(new JLabel("密码："), 0, 1, 1, 0, false);
		pass = new JTextField();
		pass.setEditable(false);
		setupComponet(pass, 1, 1, 1, 100, true);

		setupComponet(new JLabel("权限："), 2, 1, 1, 0, false);
		quanXian = new JComboBox(new String[]{"管理员", "操作员"});
		setupComponet(quanXian, 3, 1, 1, 100, true);

		setupComponet(new JLabel("选择用户"), 0, 2, 1, 0, false);
		userCombo = new JComboBox();
		userCombo.setPreferredSize(new Dimension(80, 21));
		// 处理用户信息的下拉选择框的选择事件
		userCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUserSelectAction();
			}
		});
		// 定位用户信息的下拉选择框
		setupComponet(userCombo, 1, 2, 2, 0, true);
		modifyButton = new JButton("修改");
		closeButton = new JButton("关闭");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(closeButton);
		setupComponet(panel, 3, 2, 1, 0, false);
		// 处理删除按钮的单击事件
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanManager.this.doDefaultCloseAction();
			}
		});
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) userCombo.getSelectedItem();
				TbUserlist user = Dao.getUser(item);
				int index = quanXian.getSelectedIndex();
				if (index == 0)
					user.setQuan("a");
				else
					user.setQuan("c");
				if (Dao.updateUser(user) >= 1)
					JOptionPane.showMessageDialog(QuanManager.this, "修改完成");
				else
					JOptionPane.showMessageDialog(QuanManager.this, "修改失败");
			}
		});
		// 窗体激活事件
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameActivated(InternalFrameEvent e) {
				initComboBox();// 初始化用户下拉选择框
			}
		});
	}
	// 初始化用户下拉选择框
	public void initComboBox() {
		List khInfo = Dao.getUsers();
		List<Item> items = new ArrayList<Item>();
		userCombo.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			userCombo.addItem(item);
		}
		doUserSelectAction();
	}
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	private void doUserSelectAction() {
		Item selectedItem;
		if (!(userCombo.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) userCombo.getSelectedItem();
		TbUserlist user = Dao.getUser(selectedItem);
		userName.setText(user.getUsername());
		name.setText(user.getName());
		pass.setText(user.getPass());
		if (user.getQuan().equals("a"))
			quanXian.setSelectedIndex(0);
		else
			quanXian.setSelectedIndex(1);
	}
}
