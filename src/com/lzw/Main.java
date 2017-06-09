package com.lzw;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import model.TbUserlist;
public class Main {
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	protected JFrame frame;
	private JLabel backLabel;
	// ���������Map���ͼ��϶���
	private Map<String, JInternalFrame> ifs = new HashMap<String, JInternalFrame>();
	// ����Action������ActionMap���ͼ��϶���
	private ActionMap actionMap = new ActionMap();
	// ��������ȡ��ǰ��¼���û�����
	private TbUserlist user = Login.getUser();
	private Color bgcolor = new Color(Integer.valueOf("023c5d", 16));
	public Main() {
		
		Font font = new Font("����", Font.PLAIN, 15);
		UIManager.put("Menu.font", font);
		UIManager.put("MenuItem.font", font);
		// ����initialize()������ʼ���˵���������������
		initialize();
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Login();
			}
		});
	}
	private void initialize() {		
		frame = new JFrame("���н��������ϵͳ"); 
		frame.setBackground(bgcolor);
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				if (backLabel != null) {
					int backw = ((JFrame) e.getSource()).getWidth();
					ImageIcon icon = backw <= 800 ? new ImageIcon(
							"res/welcome.jpg") : new ImageIcon(
							"res/welcomeB.jpg");
					backLabel.setIcon(icon);
					backLabel.setSize(backw, frame.getWidth());
				}
			}
		});
		frame.setIconImage(new ImageIcon("res/Img/���н��������ϵͳ.png").getImage()); //����Java����ͼ��
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(bgcolor); // #023c5dɫ����
		frame.getContentPane().add(desktopPane);
		backLabel = new JLabel();
		backLabel.setVerticalAlignment(SwingConstants.TOP);
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 792, 66);
		menuBar.setBackground(bgcolor); 
		menuBar.setBorder(new LineBorder(bgcolor));
		menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		frame.setJMenuBar(menuBar);
		menuBar.add(getBasicMenu()); 			// ��ӻ�����Ϣ�˵�
		menuBar.add(getJinHuoMenu()); 			// ��ӽ�������˵�
		menuBar.add(getSellMenu()); 			// ������۹���˵�
		menuBar.add(getKuCunMenu());			// ��ӿ�����˵�
		menuBar.add(getCxtjMenu()); 			// ��Ӳ�ѯͳ�Ʋ˵�
		menuBar.add(getSysMenu()); 				// ���ϵͳ���ò˵�
		final JToolBar toolBar = new JToolBar("������",1);
		frame.getContentPane().add(toolBar, BorderLayout.EAST);
		toolBar.setOpaque(true);
		toolBar.setRollover(true);
		toolBar.setBackground(bgcolor);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		defineToolBar(toolBar);
	}
	private JMenu getSysMenu() { 				// ��ȡϵͳ���ò˵�
		JMenu menu = new JMenu();
		menu.setText("ϵͳ����");
		menu.setBackground(bgcolor);
		JMenuItem item = new JMenuItem();
		item.setAction(actionMap.get("����Ա����"));
		item.setBackground(Color.MAGENTA);
		addFrameAction("����Ա����", "CzyGL", menu);
		addFrameAction("��������", "GengGaiMiMa", menu);
		addFrameAction("Ȩ�޹���", "QuanManager", menu);
		actionMap.put("�˳�ϵͳ", new ExitAction());
		JMenuItem mItem = new JMenuItem(actionMap.get("�˳�ϵͳ"));
		mItem.setBackground(bgcolor);
		menu.add(mItem);
		return menu;
	}
	private JMenu getSellMenu() { 				// ��ȡ���۹���˵�
		JMenu menu = new JMenu();
		menu.setText("���۹���");
		addFrameAction("���۵�", "XiaoShouDan", menu);
		addFrameAction("�����˻�", "XiaoShouTuiHuo", menu);
		return menu;
	}
	private JMenu getCxtjMenu() { 				// ��ȡ��ѯͳ�Ʋ˵�
		JMenu menu;
		menu = new JMenu();
		menu.setText("��ѯͳ��");
		addFrameAction("�ͻ���Ϣ��ѯ", "KeHuChaXun", menu);
		addFrameAction("��Ʒ��Ϣ��ѯ", "ShangPinChaXun", menu);
		addFrameAction("��Ӧ����Ϣ��ѯ", "GongYingShangChaXun", menu);
		addFrameAction("������Ϣ��ѯ", "XiaoShouChaXun", menu);
		addFrameAction("�����˻���ѯ", "XiaoShouTuiHuoChaXun", menu);
		addFrameAction("����ѯ", "RuKuChaXun", menu);
		addFrameAction("����˻���ѯ", "RuKuTuiHuoChaXun", menu);
		addFrameAction("��������", "XiaoShouPaiHang", menu);
		return menu;
	}
	private JMenu getBasicMenu() { 				// ��ȡ�����˵�
		JMenu menu = new JMenu();
		menu.setText("������Ϣ����");
		addFrameAction("�ͻ���Ϣ����", "KeHuGuanLi", menu);
		addFrameAction("��Ʒ��Ϣ����", "ShangPinGuanLi", menu);
		addFrameAction("��Ӧ����Ϣ����", "GysGuanLi", menu);
		return menu;
	}
	private JMenu getKuCunMenu() { 				// ��ȡ������˵�
		JMenu menu = new JMenu();
		menu.setText("������");
		addFrameAction("����̵�", "KuCunPanDian", menu);
		addFrameAction("�۸����", "JiaGeTiaoZheng", menu);
		return menu;
	}
	private JMenu getJinHuoMenu() { 			// ��ȡ��������˵�
		JMenu menu = new JMenu();
		menu.setText("��������");
		addFrameAction("������", "JinHuoDan", menu);
		addFrameAction("�����˻�", "JinHuoTuiHuo", menu);
		return menu;
	}
	// ��ӹ�������ť
	private void defineToolBar(final JToolBar toolBar) {
		toolBar.addSeparator();
		toolBar.add(getToolButton(actionMap.get("�ͻ���Ϣ����")));
		toolBar.add(getToolButton(actionMap.get("��Ʒ��Ϣ����")));
		toolBar.addSeparator();
		toolBar.add(getToolButton(actionMap.get("�ͻ���Ϣ��ѯ")));
		toolBar.add(getToolButton(actionMap.get("��Ʒ��Ϣ��ѯ")));
		toolBar.addSeparator();
		toolBar.add(getToolButton(actionMap.get("����̵�")));
		toolBar.add(getToolButton(actionMap.get("����ѯ")));
		toolBar.add(getToolButton(actionMap.get("�۸����")));
		toolBar.add(getToolButton(actionMap.get("���۵�")));
		toolBar.add(getToolButton(actionMap.get("�˳�ϵͳ")));
	}
	private JButton getToolButton(Action action) {
		JButton actionButton = new JButton(action);
		actionButton.setHideActionText(true);
		actionButton.setMargin(new Insets(0, 0, 0, 0));
		actionButton.setBackground(bgcolor);
		return actionButton;
	}
	/***********************��������***************************/
	/*
	//�޸Ĳ˵�������ɫ
	class BackgroundMenu extends JMenu
	{
	    Color bgColor=Color.BLUE;
	    public BackgroundMenu(String s){
	            super(s);
	    }
	    public void setColor(Color color)
	    {
	        bgColor=color;
	    }
	 
	    @Override
	    protected void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(bgColor);
	        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	 
	    }
	}
	*/
	// Ϊ�ڲ��������Action�ķ���
	private void addFrameAction(String fName, String cname, JMenu menu) {
		// System.out.println(fName+".jpg");//���ͼƬ��--������
		String img = "res/ActionIcon/" + fName + ".png";
		Icon icon = new ImageIcon(img);
		Action action = new openFrameAction(fName, cname, icon);
		if (menu.getText().equals("ϵͳ����") && !fName.equals("��������")) {
			if (user == null || user.getQuan() == null
					|| !user.getQuan().equals("a")) {
				action.setEnabled(false);
			}
		}
		actionMap.put(fName, action);
		JMenuItem item = new JMenuItem(action);
		item.setBackground(bgcolor);
		menu.add(item);
		if (!menu.getBackground().equals(bgcolor))
			menu.setBackground(bgcolor);
	}
	// ��ȡ�ڲ������Ψһʵ������
	private JInternalFrame getIFrame(String frameName) {
		JInternalFrame jf = null;
		if (!ifs.containsKey(frameName)) {
			try {
				jf = (JInternalFrame) Class.forName(
						"internalFrame." + frameName).getConstructor(null)
						.newInstance(null);
				ifs.put(frameName, jf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			jf = ifs.get(frameName);
		return jf;
	}
	// ������˵���ĵ����¼�������
	protected final class openFrameAction extends AbstractAction {
		private String frameName = null;
		private openFrameAction() {
		}
		public openFrameAction(String cname, String frameName, Icon icon) {
			this.frameName = frameName;
			putValue(Action.NAME, cname);
			putValue(Action.SHORT_DESCRIPTION, cname);
			putValue(Action.SMALL_ICON, icon);
		}
		public void actionPerformed(final ActionEvent e) {
			JInternalFrame jf = getIFrame(frameName);
			// ���ڲ�����չ�ʱ�����ڲ���������ifs����������ô��塣
			jf.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameClosed(InternalFrameEvent e) {
					ifs.remove(frameName);
				}
			});
			if (jf.getDesktopPane() == null) {
				desktopPane.add(jf);
				jf.setVisible(true);
			}
			try {
				jf.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}
	// �˳�����
	protected final class ExitAction extends AbstractAction {
		private ExitAction() {
			putValue(Action.NAME, "�˳�ϵͳ");
			putValue(Action.SHORT_DESCRIPTION, "�˳����������ϵͳ");
			putValue(Action.SMALL_ICON,
					new ImageIcon("res/ActionIcon/�˳�ϵͳ.png"));
		}
		public void actionPerformed(final ActionEvent e) {
			int exit;
			exit = JOptionPane.showConfirmDialog(frame.getContentPane(),
					"ȷ��Ҫ�˳���", "�˳�ϵͳ", JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}