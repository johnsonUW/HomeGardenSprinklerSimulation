/*
 *This class is the class to implement Home Garden Panel
*class name : SchedulePanel
* create file date : 2016/12/3
*author: johnsondl
 */
package userinterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.joda.time.Interval;
import dataenums.WeekDays; 
import system.MyGlobal;
import org.joda.time.Interval;
import system.GardenSystem;
public class SchedulePanel extends javax.swing.JPanel {
    private  final int BUTTON_WIDTH=MyGlobal.getInstance().getButtonWidth();
     private final int BUTTON_HEIGHT=MyGlobal.getInstance().getButtonHeight();
    private Map<WeekDays, List<Interval>> m_Schedule;
     private HashMap<WeekDays,int[]> m_MapStartSchedule;
     private HashMap<WeekDays,int[]> m_MapEndSchedule;
    private JLabel m_LblStartDay, m_LblStartHour, m_LblStartMinute;
    private JLabel m_LblEndDay, m_LblEndHour, m_LblEndMinute;
    private JComboBox<String> m_DaysStartComboBox;
    private JComboBox <Integer> m_HoursStartComboBox, m_MinutesStartComboBox, m_HoursEndComboBox, m_MinutesEndComboBox;
    private JButton m_BtnAddInterval;
    private String [] days = {WeekDays.SUNDAY.toString(), WeekDays.MONDAY.toString(), WeekDays.TUESDAY.toString(),
                    WeekDays.WEDNESDAY.toString(), WeekDays.THURSDAY.toString(), WeekDays.FRIDAY.toString(),
                    WeekDays.SATURDAY.toString()};	
    private List<Integer> m_Hours;
    private List<Integer> m_Minutes;
    private JScrollPane jScrollPaneSchedule;
    private JTextArea m_TextAreaSchedule;
    private StringBuilder m_Builder;
    public SchedulePanel(int width, int height){
            super(new BorderLayout());
            setPreferredSize(new Dimension(width, height));
            m_Builder = new StringBuilder();
            m_Hours = new ArrayList<Integer>();
            m_Minutes = new ArrayList<Integer>();
            for(Integer i=0;i<24; ++i){
                    m_Hours.add(i);
            }
            for(Integer i=0;i<=59; ++i){
                    m_Minutes.add(i);
            }
             m_MapStartSchedule=GardenSystem.getInstance().getMapStartSchedule();
            m_MapEndSchedule=GardenSystem.getInstance().getMapEndSchedule();
            initializeSchedule();
            TopPanel startPanel = new TopPanel();
            BodyPanel endPanel = new BodyPanel();
            setActionListeners();
            add(startPanel, BorderLayout.NORTH);
            add(endPanel, BorderLayout.CENTER);
    }
    //buton events handler
    private void setActionListeners(){
            m_BtnAddInterval.addActionListener(new ActionListener(){
                    @Override
                  
                    public void actionPerformed(ActionEvent e) {  
                          String weeSkDay=m_DaysStartComboBox.getSelectedItem().toString();
                           int[] startValue=new int[2];
                                int[] endValue=new int[2];
                                int startHour=Integer.parseInt(m_HoursStartComboBox.getSelectedItem().toString());
                                int startMinute=Integer.parseInt(m_MinutesStartComboBox.getSelectedItem().toString());
                                int endHour=Integer.parseInt(m_HoursEndComboBox.getSelectedItem().toString());
                                int endMinute=Integer.parseInt(m_MinutesEndComboBox.getSelectedItem().toString());
                        switch(weeSkDay){
                            case "Monday":
                                  endValue[0]=endHour;
                                endValue[1]=endMinute;
                                startValue[0]=startHour;
                                startValue[1]=startMinute;
                                m_MapStartSchedule.put(WeekDays.MONDAY, startValue);
                                m_MapEndSchedule.put(WeekDays.MONDAY, endValue);
                                break;
                            case "Tuesday":
                                
                                startValue[0]=startHour;
                                startValue[1]=startMinute;
                                endValue[0]=endHour;
                                endValue[1]=endMinute;
                                m_MapStartSchedule.put(WeekDays.TUESDAY, startValue);
                                m_MapEndSchedule.put(WeekDays.TUESDAY, endValue);
                             break;
                            case "Wdenesday":
                                startValue[0]=startHour;
                                startValue[1]=startMinute;
                                  endValue[0]=endHour;
                                endValue[1]=endMinute;
                                m_MapStartSchedule.put(WeekDays.MONDAY, startValue);
                                m_MapEndSchedule.put(WeekDays.MONDAY, endValue);
                                break;
                            case "Sunnday":
                                startValue[0]=startHour;
                                startValue[1]=startMinute;
                                  endValue[0]=endHour;
                                endValue[1]=endMinute;
                                m_MapStartSchedule.put(WeekDays.MONDAY, startValue);
                                m_MapEndSchedule.put(WeekDays.MONDAY, endValue);
                                break;
                         
                            case "Thursday":
                                startValue[0]=startHour;
                                startValue[1]=startMinute;
                                  endValue[0]=endHour;
                                endValue[1]=endMinute;
                                m_MapStartSchedule.put(WeekDays.MONDAY, startValue);
                                m_MapEndSchedule.put(WeekDays.MONDAY, endValue);
                                break;
                            case "Friday":
                                 startValue[0]=startHour;
                                startValue[1]=startMinute;
                                  endValue[0]=endHour;
                                endValue[1]=endMinute;
                                m_MapStartSchedule.put(WeekDays.MONDAY, startValue);
                                m_MapEndSchedule.put(WeekDays.MONDAY, endValue);
                                break;
                             case "Saturday":
                                  startValue[0]=startHour;
                                startValue[1]=startMinute;
                                  endValue[0]=endHour;
                                endValue[1]=endMinute;
                                m_MapStartSchedule.put(WeekDays.MONDAY, startValue);
                                m_MapEndSchedule.put(WeekDays.MONDAY, endValue);
                                break;
                        }
                            m_TextAreaSchedule.append(m_DaysStartComboBox.getSelectedItem() + " " +
                                            m_HoursStartComboBox.getSelectedItem() + "/" + m_MinutesStartComboBox.getSelectedItem()+
                                    " "+m_HoursEndComboBox.getSelectedItem()+"/"+m_MinutesEndComboBox.getSelectedItem() + "\n");
                    }
            });	
            
    }

    //Helper function to initialize a list for each of the 7 days
    private void initializeSchedule(){
            m_Schedule = new HashMap<WeekDays, List<Interval>>();
            m_Schedule.put(WeekDays.SUNDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.MONDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.TUESDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.WEDNESDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.THURSDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.FRIDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.SATURDAY, new ArrayList<Interval>());
    }

    private class TopPanel extends JPanel{
            public TopPanel(){			
                    m_LblStartDay = new JLabel("Day");
                    m_DaysStartComboBox = new JComboBox<String>(days);
                    m_DaysStartComboBox.setSelectedIndex(0);			
                    this.add(m_LblStartDay);
                    this.add(m_DaysStartComboBox);
            }
    }
    //body panel class
    private class BodyPanel extends JPanel{
            public BodyPanel(){
                    super(new BorderLayout());			
                    StartPanel startPanel = new StartPanel();
                    EndPanel endPanel = new EndPanel();			
                    this.add(startPanel, BorderLayout.NORTH);
                    this.add(endPanel, BorderLayout.CENTER);
            }
    }

    //start panel class
    private class StartPanel extends JPanel{
            public StartPanel(){
                    super();
                    m_LblStartHour = new JLabel("Start Hour");
                    m_LblStartMinute = new JLabel("Start Minute");
                    m_HoursStartComboBox = new JComboBox(m_Hours.toArray());
                    m_MinutesStartComboBox = new JComboBox(m_Minutes.toArray());
                    m_HoursStartComboBox.setSelectedIndex(0);
                    m_MinutesStartComboBox.setSelectedIndex(0);
                    this.add(m_LblStartHour, BorderLayout.CENTER);
                    this.add(m_HoursStartComboBox, BorderLayout.CENTER);
                    this.add(m_LblStartMinute, BorderLayout.CENTER);
                    this.add(m_MinutesStartComboBox, BorderLayout.CENTER);
            }
    }
    //end panel class
    private class EndPanel extends JPanel{
            public EndPanel(){
                    super(new BorderLayout());
                    m_LblEndHour = new JLabel("End Hour");
                    m_LblEndMinute = new JLabel("End Minute");			
                    m_HoursEndComboBox = new JComboBox(m_Hours.toArray());
                    m_MinutesEndComboBox = new JComboBox( m_Minutes.toArray());
                    m_HoursEndComboBox.setSelectedIndex(0);
                    m_MinutesEndComboBox.setSelectedIndex(0);
                    m_BtnAddInterval = new JButton("Add Interval");
                    m_TextAreaSchedule = new JTextArea();
                    m_TextAreaSchedule.setColumns(40);
                    m_TextAreaSchedule.setLineWrap(true);
                    m_TextAreaSchedule.setRows(5);
                    m_TextAreaSchedule.setWrapStyleWord(true);
                    jScrollPaneSchedule = new JScrollPane(m_TextAreaSchedule);
                    m_TextAreaSchedule.setEditable(false);
                    m_Builder.append("Intervals to add:\n");
                    m_TextAreaSchedule.setText(m_Builder.toString());      
                    JPanel btnPanel = new JPanel();
                    m_BtnAddInterval.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
                    btnPanel.add(m_BtnAddInterval);
                    JPanel comboPanel = new JPanel();
                    comboPanel.add(m_LblEndHour);
                    comboPanel.add(m_HoursEndComboBox);
                    comboPanel.add(m_LblEndMinute);
                    comboPanel.add(m_MinutesEndComboBox);
                    comboPanel.add(jScrollPaneSchedule);
                    this.add(comboPanel, BorderLayout.NORTH);
                    this.add(btnPanel, BorderLayout.CENTER);
                  
            }
    } 
    
}
