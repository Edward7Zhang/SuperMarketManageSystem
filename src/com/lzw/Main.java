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
	// 创建窗体的Map类型集合对象
	private Map<String, JInternalFrame> ifs = new HashMap<String, JInternalFrame>();
	// 创建Action动作的ActionMap类型集合对象
	private ActionMap actionMap = new ActionMap();
	// 创建并获取当前登录的用户对象
	private TbUserlist user = Login.getUser();
	private Color bgcolor = new Color(Integer.valueOf("023c5d", 16));
	public Main() {
		
		Font font = new Font("黑体", Font.PLAIN, 15);
		UIManager.put("Menu.font", font);
		UIManager.put("MenuItem.font", font);
		// 调用initialize()方法初始化菜单、工具栏、窗体
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
		frame = new JFrame("超市进销存管理系统"); 
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
		frame.setIconImage(new ImageIcon("res/Img/超市进销存管理系统.png").getImage()); //设置Java窗体图标
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(bgcolor); // #023c5d色背景
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
		menuBar.add(getBasicMenu()); 			// 添加基础信息菜单
		menuBar.add(getJinHuoMenu()); 			// 添加进货管理菜单
		menuBar.add(getSellMenu()); 			// 添加销售管理菜单
		menuBar.add(getKuCunMenu());			// 添加库存管理菜单
		menuBar.add(getCxtjMenu()); 			// 添加查询统计菜单
		menuBar.add(getSysMenu()); 				// 添加系统设置菜单
		final JToolBar toolBar = new JToolBar("工具栏",1);
		frame.getContentPane().add(toolBar, BorderLayout.EAST);
		toolBar.setOpaque(true);
		toolBar.setRollover(true);
		toolBar.setBackground(bgcolor);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		defineToolBar(toolBar);
	}
	private JMenu getSysMenu() { 				// 获取系统设置菜单
		JMenu menu = new JMenu();
		menu.setText("系统管理");
		menu.setBackground(bgcolor);
		JMenuItem item = new JMenuItem();
		item.setAction(actionMap.get("操作员管理"));
		item.setBackground(Color.MAGENTA);
		addFrameAction("操作员管理", "CzyGL", menu);
		addFrameAction("更改密码", "GengGaiMiMa", menu);
		addFrameAction("权限管理", "QuanManager", menu);
		actionMap.put("退出系统", new ExitAction());
		JMenuItem mItem = new JMenuItem(actionMap.get("退出系统"));
		mItem.setBackground(bgcolor);
		menu.add(mItem);
		return menu;
	}
	private JMenu getSellMenu() { 				// 获取销售管理菜单
		JMenu menu = new JMenu();
		menu.setText("销售管理");
		addFrameAction("销售单", "XiaoShouDan", menu);
		addFrameAction("销售退货", "XiaoShouTuiHuo", menu);
		return menu;
	}
	private JMenu getCxtjMenu() { 				// 获取查询统计菜单
		JMenu menu;
		menu = new JMenu();
		menu.setText("查询统计");
		addFrameAction("客户信息查询", "KeHuChaXun", menu);
		addFrameAction("商品信息查询", "ShangPinChaXun", menu);
		addFrameAction("供应商信息查询", "GongYingShangChaXun", menu);
		addFrameAction("销售信息查询", "XiaoShouChaXun", menu);
		addFrameAction("销售退货查询", "XiaoShouTuiHuoChaXun", menu);
		addFrameAction("入库查询", "RuKuChaXun", menu);
		addFrameAction("入库退货查询", "RuKuTuiHuoChaXun", menu);
		addFrameAction("销售排行", "XiaoShouPaiHang", menu);
		return menu;
	}
	private JMenu getBasicMenu() { 				// 获取基础菜单
		JMenu menu = new JMenu();
		menu.setText("基础信息管理");
		addFrameAction("客户信息管理", "KeHuGuanLi", menu);
		addFrameAction("商品信息管理", "ShangPinGuanLi", menu);
		addFrameAction("供应商信息管理", "GysGuanLi", menu);
		return menu;
	}
	private JMenu getKuCunMenu() { 				// 获取库存管理菜单
		JMenu menu = new JMenu();
		menu.setText("库存管理");
		addFrameAction("库存盘点", "KuCunPanDian", menu);
		addFrameAction("价格调整", "JiaGeTiaoZheng", menu);
		return menu;
	}
	private JMenu getJinHuoMenu() { 			// 获取进货管理菜单
		JMenu menu = new JMenu();
		menu.setText("进货管理");
		addFrameAction("进货单", "JinHuoDan", menu);
		addFrameAction("进货退货", "JinHuoTuiHuo", menu);
		return menu;
	}
	// 添加工具栏按钮
	private void defineToolBar(final JToolBar toolBar) {
		toolBar.addSeparator();
		toolBar.add(getToolButton(actionMap.get("客户信息管理")));
		toolBar.add(getToolButton(actionMap.get("商品信息管理")));
		toolBar.addSeparator();
		toolBar.add(getToolButton(actionMap.get("客户信息查询")));
		toolBar.add(getToolButton(actionMap.get("商品信息查询")));
		toolBar.addSeparator();
		toolBar.add(getToolButton(actionMap.get("库存盘点")));
		toolBar.add(getToolButton(actionMap.get("入库查询")));
		toolBar.add(getToolButton(actionMap.get("价格调整")));
		toolBar.add(getToolButton(actionMap.get("销售单")));
		toolBar.add(getToolButton(actionMap.get("退出系统")));
	}
	private JButton getToolButton(Action action) {
		JButton actionButton = new JButton(action);
		actionButton.setHideActionText(true);
		actionButton.setMargin(new Insets(0, 0, 0, 0));
		actionButton.setBackground(bgcolor);
		return actionButton;
	}
	/***********************辅助方法***************************/
	/*
	//修改菜单栏背景色
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
	// 为内部窗体添加Action的方法
	private void addFrameAction(String fName, String cname, JMenu menu) {
		// System.out.println(fName+".jpg");//输出图片名--调试用
		String img = "res/ActionIcon/" + fName + ".png";
		Icon icon = new ImageIcon(img);
		Action action = new openFrameAction(fName, cname, icon);
		if (menu.getText().equals("系统管理") && !fName.equals("更改密码")) {
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
	// 获取内部窗体的唯一实例对象
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
	// 主窗体菜单项的单击事件监听器
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
			// 在内部窗体闭关时，从内部窗体容器ifs对象中清除该窗体。
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
	// 退出动作
	protected final class ExitAction extends AbstractAction {
		private ExitAction() {
			putValue(Action.NAME, "退出系统");
			putValue(Action.SHORT_DESCRIPTION, "退出进销存管理系统");
			putValue(Action.SMALL_ICON,
					new ImageIcon("res/ActionIcon/退出系统.png"));
		}
		public void actionPerformed(final ActionEvent e) {
			int exit;
			exit = JOptionPane.showConfirmDialog(frame.getContentPane(),
					"确定要退出吗？", "退出系统", JOptionPane.YES_NO_OPTION);
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