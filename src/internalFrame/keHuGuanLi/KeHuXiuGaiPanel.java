package internalFrame.keHuGuanLi;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import keyListener.InputKeyListener;
import model.TbKhinfo;
import com.lzw.dao.Dao;
public class KeHuXiuGaiPanel extends JPanel {
	private JTextField keHuQuanCheng;
	private JTextField yinHangZhangHao;
	private JTextField kaiHuYinHang;
	private JTextField EMail;
	private JTextField lianXiDianHua;
	private JTextField lianXiRen;
	private JTextField chuanZhen;
	private JTextField dianHua;
	private JTextField youZhengBianMa;
	private JTextField diZhi;
	private JTextField keHuJianCheng;
	private JButton modifyButton;
	private JButton delButton;
	private JComboBox kehu;
	public KeHuXiuGaiPanel() {
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);

		final JLabel khName = new JLabel();
		khName.setText("�ͻ�ȫ�ƣ�");
		setupComponet(khName, 0, 0, 1, 0, false);

		keHuQuanCheng = new JTextField();
		keHuQuanCheng.setEditable(false);
		// ��λȫ���ı���
		setupComponet(keHuQuanCheng, 1, 0, 3, 350, true);

		final JLabel addressLabel = new JLabel("�ͻ���ַ��");
		setupComponet(addressLabel, 0, 1, 1, 0, false);

		diZhi = new JTextField();
		// ��λ��ַ�ı���
		setupComponet(diZhi, 1, 1, 3, 0, true);

		setupComponet(new JLabel("�ͻ���ƣ�"), 0, 2, 1, 0, false);
		keHuJianCheng = new JTextField();
		// ��λ�ͻ�����ı���
		setupComponet(keHuJianCheng, 1, 2, 1, 130, true);

		setupComponet(new JLabel("�������룺"), 2, 2, 1, 0, false);

		youZhengBianMa = new JTextField();
		// ��λ���������ı���
		setupComponet(youZhengBianMa, 3, 2, 1, 100, true);
		youZhengBianMa.addKeyListener(new InputKeyListener());

		setupComponet(new JLabel("�绰��"), 0, 3, 1, 0, false);

		dianHua = new JTextField();
		// ��λ�绰�ı���
		setupComponet(dianHua, 1, 3, 1, 100, true);
		dianHua.addKeyListener(new InputKeyListener());

		setupComponet(new JLabel("���棺"), 2, 3, 1, 0, false);
		chuanZhen = new JTextField();
		// ��λ�����ı���
		chuanZhen.addKeyListener(new InputKeyListener());
		setupComponet(chuanZhen, 3, 3, 1, 100, true);

		setupComponet(new JLabel("��ϵ�ˣ�"), 0, 4, 1, 0, false);
		lianXiRen = new JTextField();
		// ��λ��ϵ���ı���
		setupComponet(lianXiRen, 1, 4, 1, 100, true);

		setupComponet(new JLabel("��ϵ�绰��"), 2, 4, 1, 0, false);
		lianXiDianHua = new JTextField();
		// ��λ��ϵ�绰�ı���
		setupComponet(lianXiDianHua, 3, 4, 1, 100, true);
		lianXiDianHua.addKeyListener(new InputKeyListener());

		setupComponet(new JLabel("E-Mail��"), 0, 5, 1, 0, false);
		EMail = new JTextField();
		// ��λE-Mail�ı���
		setupComponet(EMail, 1, 5, 3, 350, true);

		setupComponet(new JLabel("�������У�"), 0, 6, 1, 0, false);
		kaiHuYinHang = new JTextField();
		// ��λ���������ı���
		setupComponet(kaiHuYinHang, 1, 6, 1, 100, true);

		setupComponet(new JLabel("�����˺ţ�"), 2, 6, 1, 0, false);
		yinHangZhangHao = new JTextField();
		// ��λ�����˺��ı���
		setupComponet(yinHangZhangHao, 3, 6, 1, 100, true);

		setupComponet(new JLabel("ѡ��ͻ�"), 0, 7, 1, 0, false);
		kehu = new JComboBox();
		kehu.setPreferredSize(new Dimension(230, 21));
		initComboBox();// ��ʼ������ѡ���
		// ����ͻ���Ϣ������ѡ����ѡ���¼�
		kehu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doKeHuSelectAction();
			}
		});
		// ��λ�ͻ���Ϣ������ѡ���
		setupComponet(kehu, 1, 7, 2, 0, true);
		modifyButton = new JButton("�޸�");
		delButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// ��λ��ť
		setupComponet(panel, 3, 7, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) kehu.getSelectedItem();
				if (item == null || !(item instanceof Item))
					return;
				int confirm = JOptionPane.showConfirmDialog(
						KeHuXiuGaiPanel.this, "ȷ��ɾ���ͻ���Ϣ��");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = Dao.delete("delete tb_khinfo where id='"
							+ item.getId() + "'");
					if (rs > 0) {
						JOptionPane.showMessageDialog(KeHuXiuGaiPanel.this,
								"�ͻ���" + item.getName() + "��ɾ���ɹ�");
						kehu.removeItem(item);
					}
				}
			}
		});
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) kehu.getSelectedItem();
				TbKhinfo khinfo = new TbKhinfo();
				khinfo.setId(item.getId());
				khinfo.setAddress(diZhi.getText().trim());
				khinfo.setBianma(youZhengBianMa.getText().trim());
				khinfo.setFax(chuanZhen.getText().trim());
				khinfo.setHao(yinHangZhangHao.getText().trim());
				khinfo.setJian(keHuJianCheng.getText().trim());
				khinfo.setKhname(keHuQuanCheng.getText().trim());
				khinfo.setLian(lianXiRen.getText().trim());
				khinfo.setLtel(lianXiDianHua.getText().trim());
				khinfo.setMail(EMail.getText().trim());
				khinfo.setTel(dianHua.getText().trim());
				khinfo.setXinhang(kaiHuYinHang.getText());
				if (Dao.updateKeHu(khinfo) == 1)
					JOptionPane.showMessageDialog(KeHuXiuGaiPanel.this, "�޸����");
				else
					JOptionPane.showMessageDialog(KeHuXiuGaiPanel.this, "�޸�ʧ��");
			}
		});
	}
	// ��ʼ���ͻ�����ѡ���
	public void initComboBox() {
		List khInfo = Dao.getKhInfos();
		List<Item> items = new ArrayList<Item>();
		kehu.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			kehu.addItem(item);
		}
		doKeHuSelectAction();
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
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	private void doKeHuSelectAction() {
		Item selectedItem;
		if (!(kehu.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) kehu.getSelectedItem();
		TbKhinfo khInfo = Dao.getKhInfo(selectedItem);
		keHuQuanCheng.setText(khInfo.getKhname());
		diZhi.setText(khInfo.getAddress());
		keHuJianCheng.setText(khInfo.getJian());
		youZhengBianMa.setText(khInfo.getBianma());
		dianHua.setText(khInfo.getTel());
		chuanZhen.setText(khInfo.getFax());
		lianXiRen.setText(khInfo.getLian());
		lianXiDianHua.setText(khInfo.getLtel());
		EMail.setText(khInfo.getMail());
		kaiHuYinHang.setText(khInfo.getXinhang());
		yinHangZhangHao.setText(khInfo.getHao());
	}
}
