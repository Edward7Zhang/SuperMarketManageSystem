package internalFrame;
import internalFrame.shangPinGuanLi.ShangPinTianJiaPanel;
import internalFrame.shangPinGuanLi.ShangPinXiuGaiPanel;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
public class ShangPinGuanLi extends JInternalFrame {
	public ShangPinGuanLi() {
		setIconifiable(true);
		setClosable(true);
		setTitle("��Ʒ����");
		JTabbedPane tabPane = new JTabbedPane();
		final ShangPinXiuGaiPanel spxgPanel = new ShangPinXiuGaiPanel();
		final ShangPinTianJiaPanel sptjPanel = new ShangPinTianJiaPanel();
		tabPane.addTab("��Ʒ��Ϣ���", null, sptjPanel, "��Ʒ���");
		tabPane.addTab("��Ʒ��Ϣ�޸���ɾ��", null, spxgPanel, "�޸���ɾ��");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spxgPanel.initComboBox();
				spxgPanel.initGysBox();
			}
		});
		//����Ʒ�����ڱ�����ʱ����ʼ����Ʒ��ӽ���Ĺ�Ӧ������ѡ���
		addInternalFrameListener(new InternalFrameAdapter(){
			public void internalFrameActivated(InternalFrameEvent e) {
				super.internalFrameActivated(e);
				sptjPanel.initGysBox();
			}
		});
		pack();
		setVisible(true);
	}
}
