/*
 *This class is the class to set Specific date Panel
*class name : SpecificSetDayPanel
* create file date : 2016/12/3
*author: johnsondl
 */
package userinterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.joda.time.Interval;
import dataenums.WeekDays; 
public class SpecificSetDayPanel extends JPanel{
    private JLabel m_LblDay, m_LblMonth, m_LblDate, m_LblYear;
	private JComboBox<String> m_DayComboBox;
	private JComboBox <Integer> m_ComboMonth, m_ComboDate, m_ComboYear;
	private JButton m_BtnCommit, m_BtnBack;
	private String [] days = {WeekDays.SUNDAY.toString(), WeekDays.MONDAY.toString(), WeekDays.TUESDAY.toString(),
			WeekDays.WEDNESDAY.toString(), WeekDays.THURSDAY.toString(), WeekDays.FRIDAY.toString(),
			WeekDays.SATURDAY.toString()};
	private Integer [] months = {1,2,3,4,5,6,7,8,9,10,11,12};
	private Integer [] date = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
			20,21,22,23,24,25,26,27,28,29,30,31};
	private Integer [] year = {2016,2017,2018,2019,2020};
	public SpecificSetDayPanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		m_LblDay = new JLabel("Day");
		m_LblMonth = new JLabel("Month");
		m_LblDate = new JLabel("Date");
		m_LblYear = new JLabel("Year");
		m_DayComboBox = new JComboBox<String>(days);
		m_ComboMonth = new JComboBox<Integer>(months);
		m_ComboDate = new JComboBox<Integer>(date);
		m_ComboYear = new JComboBox<Integer>(year);
		m_DayComboBox.setSelectedIndex(0);
		m_ComboMonth.setSelectedIndex(0);
		m_ComboDate.setSelectedIndex(0);
		m_ComboYear.setSelectedIndex(0);
		m_BtnCommit = new JButton("Set Date");
		m_BtnBack = new JButton("Back");
		JPanel datePanel = new JPanel();
		datePanel.add(m_LblDay);
		datePanel.add(m_DayComboBox);
		datePanel.add(m_LblMonth);
		datePanel.add(m_ComboMonth);
		datePanel.add(m_LblDate);
		datePanel.add(m_ComboDate);
		datePanel.add(m_LblYear);
		datePanel.add(m_ComboYear);
		JPanel btnPanel = new JPanel();
		btnPanel.add(m_BtnCommit);
		btnPanel.add(m_BtnBack);
		setActionListeners();
		this.add(datePanel, BorderLayout.NORTH);
		this.add(btnPanel, BorderLayout.CENTER);
	}
	private void setActionListeners(){
		m_BtnCommit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnCommit pressed");
			}
		});
		m_BtnBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnBack pressed");
			}
		});
	} 
}
