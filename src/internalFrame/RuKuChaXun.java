package internalFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import model.TbRukuDetail;
import model.TbUserlist;
import com.lzw.dao.*;

public class RuKuChaXun extends JInternalFrame {
	private JCheckBox selectDate;
	private JLabel label;
	private JTextField endDate;
	private JTextField startDate;
	private JTable table;
	private JTextField content;
	private JComboBox operation;
	private JComboBox condition;
	private TbUserlist user;
	private JButton button;
	private DefaultTableModel dftm;

	public RuKuChaXun() {
		super();
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameActivated(final InternalFrameEvent e) {
				java.sql.Date date = new java.sql.Date(System
						.currentTimeMillis());
				endDate.setText(date.toString());
				date.setMonth(0);
				date.setDate(1);
				startDate.setText(date.toString());
			}
		});
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("商品入库查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 620, 375);

		label = new JLabel();
		label.setFont(new Font("", Font.PLAIN, 14));
		label.setText(" 选择查询条件：");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		getContentPane().add(label, gridBagConstraints);

		condition = new JComboBox();
		condition.setModel(new DefaultComboBoxModel(new String[]{"商品名称",
				"商品编号", "供应商全称"}));
		condition.setFont(new Font("", Font.PLAIN, 14));
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridy = 0;
		gridBagConstraints_1.gridx = 1;
		getContentPane().add(condition, gridBagConstraints_1);

		operation = new JComboBox();
		operation.setModel(new DefaultComboBoxModel(new String[]{"等于", "包含"}));
		operation.setFont(new Font("", Font.PLAIN, 14));
		operation.setPreferredSize(new Dimension(30, 21));
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.gridy = 0;
		gridBagConstraints_2.gridx = 2;
		gridBagConstraints_2.gridwidth=3;
		gridBagConstraints_2.ipadx=30;
		getContentPane().add(operation, gridBagConstraints_2);

		content = new JTextField();
		content.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 13) {
					button.doClick();
				}
			}
		});
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.ipadx = 105;
		gridBagConstraints_3.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_3.weightx = 1.0;
		gridBagConstraints_3.gridy = 0;
		gridBagConstraints_3.gridx = 6;
		getContentPane().add(content, gridBagConstraints_3);

		button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				List list = null;
				String conditionStr, operationStr;
				String contentStr = content.getText();
				switch (condition.getSelectedIndex()) {
					case 0 :
						conditionStr = "spname";
						break;
					case 1 :
						conditionStr = "id";
						break;
					case 2 :
						conditionStr = "gysname";
						break;
					default :
						conditionStr = "id";
						break;
				}
				switch (operation.getSelectedIndex()) {
					case 0 :
						operationStr = "= '" + contentStr + "'";
						break;
					case 1 :
						operationStr = "like '%" + contentStr + "%'";
						break;
					default :
						operationStr = "= '" + contentStr + "'";
						break;
				}
				String sql = "select * from v_rukuView where ";
				if (selectDate.isSelected()) {
					String sDate = startDate.getText().trim();
					String eDate = endDate.getText().trim();
					list = Dao.findForList(sql + conditionStr + " "
							+ operationStr + " and rkdate>='" + sDate
							+ "' and rkdate<='" + eDate + " 23:59:59'");
				} else {
					list = Dao.findForList(sql + conditionStr + " "
							+ operationStr);
				}
				updateTable(list.iterator());
			}
		});
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.gridy = 0;
		gridBagConstraints_4.gridx = 7;
		getContentPane().add(button, gridBagConstraints_4);
		button.setFont(new Font("", Font.PLAIN, 12));
		button.setText("查询");

		selectDate = new JCheckBox();
		selectDate.setFont(new Font("", Font.PLAIN, 14));
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.anchor = GridBagConstraints.EAST;
		gridBagConstraints_7.gridy = 1;
		gridBagConstraints_7.gridx = 0;
		getContentPane().add(selectDate, gridBagConstraints_7);

		final JLabel label_1 = new JLabel();
		label_1.setFont(new Font("", Font.PLAIN, 14));
		label_1.setText("指定查询日期   从");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.anchor = GridBagConstraints.EAST;
		gridBagConstraints_8.gridy = 1;
		gridBagConstraints_8.gridx = 1;
		getContentPane().add(label_1, gridBagConstraints_8);

		startDate = new JTextField();
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.ipadx = 70;
		gridBagConstraints_9.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints_9.gridwidth = 3;
		gridBagConstraints_9.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_9.gridy = 1;
		gridBagConstraints_9.gridx = 2;
		getContentPane().add(startDate, gridBagConstraints_9);

		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("", Font.PLAIN, 14));
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.gridy = 1;
		gridBagConstraints_10.gridx = 5;
		getContentPane().add(label_2, gridBagConstraints_10);
		label_2.setText("到");

		endDate = new JTextField();
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.ipadx = 80;
		gridBagConstraints_11.weightx = 1;
		gridBagConstraints_11.anchor = GridBagConstraints.WEST;
		gridBagConstraints_11.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints_11.gridy = 1;
		gridBagConstraints_11.gridx = 6;
		getContentPane().add(endDate, gridBagConstraints_11);

		final JButton showallButton_1 = new JButton();
		showallButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				content.setText("");
				List list = Dao.findForList("select * from v_rukuView");
				Iterator iterator = list.iterator();
				updateTable(iterator);
			}
		});
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_5.gridy = 1;
		gridBagConstraints_5.gridx = 7;
		getContentPane().add(showallButton_1, gridBagConstraints_5);
		showallButton_1.setFont(new Font("", Font.PLAIN, 12));
		showallButton_1.setText("显示全部数据");

		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 9;
		gridBagConstraints_6.gridy = 2;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setShowGrid(true);
		table.putClientProperty("Quaqua.Table.style", "striped");
		dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[]{"入库票号", "商品编号", "商品名称", "规格", "单价",
				"数量", "金额", "供应商全称", "入库日期", "操作员", "经手人", "结算方式"};
		dftm.setColumnIdentifiers(tableHeads);
		scrollPane.setViewportView(table);
	}

	private void updateTable(Iterator iterator) {
		int rowCount = dftm.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			dftm.removeRow(0);
		}
		while (iterator.hasNext()) {
			Vector vector = new Vector();
			List view = (List) iterator.next();
			vector.addAll(view);
			dftm.addRow(vector);
		}
	}
}