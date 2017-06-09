package internalFrame;
import internalFrame.gysGuanLi.GysTianJiaPanel;
import internalFrame.gysGuanLi.GysXiuGaiPanel;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class GysGuanLi extends JInternalFrame {
	public GysGuanLi() {
		setIconifiable(true);
		setClosable(true);
		setTitle("��Ӧ�̹���");
		JTabbedPane tabPane = new JTabbedPane();
		final GysXiuGaiPanel spxgPanel = new GysXiuGaiPanel();
		final GysTianJiaPanel sptjPanel = new GysTianJiaPanel();
		tabPane.addTab("��Ӧ����Ϣ���", null, sptjPanel, "��Ӧ�����");
		tabPane.addTab("��Ӧ����Ϣ�޸���ɾ��", null, spxgPanel, "�޸���ɾ��");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spxgPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}