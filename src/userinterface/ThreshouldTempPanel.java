/*
 *This class is the class to implement the threshould temoerature panel
*class name : ThreshouldTempPanel
* create file date : 2016/12/3
*author: johnsondl
 */
package userinterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import system.GardenSystem;
import utils.SprinklerFormatter; 
public class ThreshouldTempPanel extends JPanel {
 //class member variables
    private JLabel m_LblMin, m_LblMax;
    private JComboBox <Integer> m_ComboMin, m_ComboMax;
    private JButton m_BtnCommit;
    private Integer [] m_TempArray;
    //constructor
	public ThreshouldTempPanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		m_LblMin = new JLabel("Set Min Temperature");
		m_LblMax = new JLabel("Set Max Temperature");
		m_TempArray = new Integer[126];
		for(int i=0;i<126;++i){
			m_TempArray[i]=i;
		}	
		m_ComboMin = new JComboBox<Integer>(m_TempArray);
		m_ComboMax = new JComboBox<Integer>(m_TempArray);
		m_ComboMin.setSelectedIndex(50);
		m_ComboMax.setSelectedIndex(90);
		m_BtnCommit = new JButton("Commit Changes");
		setActionListeners();
		JPanel btnPanel = new JPanel();
		btnPanel.add(m_BtnCommit);	
		JPanel settingPanel = new JPanel();
		settingPanel.add(m_LblMin);
		settingPanel.add(m_ComboMin);
		settingPanel.add(m_LblMax);
		settingPanel.add(m_ComboMax);
		this.add(settingPanel, BorderLayout.NORTH);
		this.add(btnPanel, BorderLayout.CENTER);	
	}
        //button events handler
	private void setActionListeners(){
		m_BtnCommit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnCommit pressed");
				int max = (int) m_ComboMax.getSelectedItem();
				int min = (int) m_ComboMin.getSelectedItem();
				GardenSystem.getInstance().setMaxThreshold(max);
				GardenSystem.getInstance().setMinThreshold(min);
                                GardenSystem.getInstance().setMinTemperatureSystem();
                                JOptionPane.showMessageDialog(null, "Successufly temperature threshould change!");
			}
		});
	} 
}
