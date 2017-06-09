package internalFrame;
import internalFrame.guanli.Item;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import model.TbKucun;
import com.lzw.dao.Dao;
public class JiaGeTiaoZheng extends JInternalFrame {
	private TbKucun kcInfo;
	private JLabel guiGe;
	private JTextField kuCunJinE;
	private JTextField kuCunShuLiang;
	private JTextField danJia;
	private JComboBox shangPinMingCheng;
	private void updateJinE() {
		Double dj = Double.valueOf(danJia.getText());
		Integer sl = Integer.valueOf(kuCunShuLiang.getText());
		kuCunJinE.setText((dj * sl) + "");
	}
	public JiaGeTiaoZheng() {
		super();
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameActivated(final InternalFrameEvent e) {
				DefaultComboBoxModel mingChengModel = (DefaultComboBoxModel) shangPinMingCheng
						.getModel();
				mingChengModel.removeAllElements();
				List list = Dao.getKucunInfos();
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					List element = (List) iterator.next();
					Item item=new Item();
					item.setId((String) element.get(0));
					item.setName((String) element.get(1));
					mingChengModel.addElement(item);
				}
			}
		});
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("�۸����");
		setBounds(100, 100, 531, 253);
		setupComponet(new JLabel("��Ʒ���ƣ�"), 0, 0, 1, 1, false);
		shangPinMingCheng = new JComboBox();
		shangPinMingCheng.setPreferredSize(new Dimension(220,21));
		setupComponet(shangPinMingCheng, 1, 0, 1, 1, true);
		setupComponet(new JLabel("�桡����"), 2, 0, 1, 0, false);
		guiGe = new JLabel();
		guiGe.setForeground(Color.BLUE);
		guiGe.setPreferredSize(new Dimension(130,21));
		setupComponet(guiGe, 3, 0, 1, 1, true);
		setupComponet(new JLabel("�������أ� "), 0, 1, 1, 0, false);
		final JLabel chanDi = new JLabel();
		chanDi.setForeground(Color.BLUE);
		setupComponet(chanDi, 1, 1, 1, 1, true);
		setupComponet(new JLabel("�򡡡��ƣ�"), 2, 1, 1, 0, false);
		final JLabel jianCheng = new JLabel();
		jianCheng.setForeground(Color.BLUE);
		setupComponet(jianCheng, 3, 1, 1, 1, true);
		setupComponet(new JLabel("������װ��"), 0, 2, 1, 0, false);
		final JLabel baoZhuang = new JLabel();
		baoZhuang.setForeground(Color.BLUE);
		setupComponet(baoZhuang, 1, 2, 1, 1, true);
		setupComponet(new JLabel("������λ��"), 2, 2, 1, 0, false);
		final JLabel danWei = new JLabel();
		danWei.setForeground(Color.BLUE);
		setupComponet(danWei, 3, 2, 1, 1, true);
				setupComponet(new JLabel("�������ۣ�"), 0, 3, 1, 0, false);
		danJia = new JTextField();
		danJia.addKeyListener(new KeyAdapter() {
			public void keyReleased(final KeyEvent e) {
				updateJinE();
			}
		});
		setupComponet(danJia, 1, 3, 1, 1, true);
		setupComponet(new JLabel("���������"), 2, 3, 1, 0, false);
		kuCunShuLiang = new JTextField();
		kuCunShuLiang.setEditable(false);
		setupComponet(kuCunShuLiang, 3, 3, 1, 1, true);

		setupComponet(new JLabel("����"), 0, 4, 1, 0, false);
		kuCunJinE = new JTextField();
		kuCunJinE.setEditable(false);
		setupComponet(kuCunJinE, 1, 4, 1, 1, true);
		
		final JButton okButton = new JButton();
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				kcInfo.setDj(Double.valueOf(danJia.getText()));
				kcInfo.setKcsl(Integer.valueOf(kuCunShuLiang.getText()));
				int rs = Dao.updateKucunDj(kcInfo);
				if (rs > 0)
					JOptionPane.showMessageDialog(getContentPane(), "�۸������ϡ�",
							kcInfo.getSpname() + "�۸����",
							JOptionPane.QUESTION_MESSAGE);
			}
		});
		okButton.setText("ȷ��");
		setupComponet(okButton, 1, 5, 1, 1, false);
		 
		final JButton closeButton = new JButton();
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				JiaGeTiaoZheng.this.doDefaultCloseAction();
			}
		});
		closeButton.setText("�ر�");
		setupComponet(closeButton, 2, 5, 1, 1, false);
		
		shangPinMingCheng.addItemListener(new ItemListener() {
			public void itemStateChanged(final ItemEvent e) {
				Object selectedItem = shangPinMingCheng.getSelectedItem();
				if (selectedItem == null)
					return;
				Item item = (Item) selectedItem;
				kcInfo = Dao.getKucun(item);
				if(kcInfo.getId()==null)
					return;
				int dj, sl;
				dj = kcInfo.getDj().intValue();
				sl = kcInfo.getKcsl().intValue();
				chanDi.setText(kcInfo.getCd());
				jianCheng.setText(kcInfo.getJc());
				baoZhuang.setText(kcInfo.getBz());
				danWei.setText(kcInfo.getDw());
				danJia.setText(kcInfo.getDj() + "");
				kuCunShuLiang.setText(kcInfo.getKcsl() + "");
				kuCunJinE.setText(dj * sl + "");
				guiGe.setText(kcInfo.getGg());
			}
		});
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 5);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}
}
