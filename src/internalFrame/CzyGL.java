package internalFrame;

import internalFrame.czyGl.ShanChuCaoZuoYuan;
import internalFrame.czyGl.TJCzy;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class CzyGL extends JInternalFrame {
	public CzyGL() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 491, 287);
		setTitle("操作员管理");
		JTabbedPane tabPane = new JTabbedPane();
		final TJCzy tjPanel = new TJCzy();
		final ShanChuCaoZuoYuan delPanel = new ShanChuCaoZuoYuan();
		tabPane.addTab("添加操作员", null, tjPanel, "添加操作员");
		tabPane.addTab("删除操作员", null, delPanel, "删除操作员");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				delPanel.initTable();
			}
		});
		pack();
		setVisible(true);
	}
}
