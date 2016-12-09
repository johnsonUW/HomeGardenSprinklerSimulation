/*
 *This class is the class to set configuration
*class name : SprinklerConfigurationPanel
* create file date : 2016/12/3
*author: johnsondl
 */
package userinterface;
import dataenums.SprinklerDirections;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import system.GardenSystem;
import system.MyGlobal;
import system.SprinklerCluster;
import system.SprinklerSystemDate;
import utils.SprinklerFormatter;
import userinterface.MainUserInterface;
public class SprinklerConfigurationPanel extends JPanel{
    private static final int BUTTON_WIDTH =MyGlobal.getInstance().getButtonWidth();
    private static final int BUTTON_HEIGHT =MyGlobal.getInstance().getButtonHeight();
    private static final int LABEL_SPACING = MyGlobal.getInstance().getLabelSpacing();
    private static final String FONT_TYPE=MyGlobal.getInstance().getFontType();
    private static final int FONT_SIZE=MyGlobal.getInstance().getFontSize()+4;
      private HashMap<SprinklerDirections, SprinklerCluster> m_clusters;
    private int m_Width;
    private int m_Height;
    private JPanel m_ButtonPanel;
    private JPanel m_LabelPanel;
    private JButton m_BtnIncrementDay;
    private JButton m_BtnDecrementDay;
    private JButton m_BtnSetSchedule;
    private JButton m_BtnSetThresholdTemp;
    private JButton m_BtnEnable;
    private JButton m_BtnDisable;
    private JButton m_BtnActivation;
    private JButton m_BtnStatus;
    private JLabel m_LblSystemDate;
    private JLabel m_LblMinThreshold;
    private JLabel m_LblMaxThreshold;
   
	
	public SprinklerConfigurationPanel(int width, int height){
		super();
		this.m_Width = width;
		this.m_Height = height;
		setPreferredSize(new Dimension(width, height));
		m_ButtonPanel = new JPanel();
		m_LabelPanel = new JPanel();
		m_ButtonPanel.setPreferredSize(new Dimension(width, height / 2));
		m_LabelPanel.setPreferredSize(new Dimension(width, height / 2));
		m_ButtonPanel.setBackground(Color.GRAY);
		m_LabelPanel.setBackground(Color.GRAY);
		m_BtnIncrementDay = new JButton("+ Day");
		m_BtnDecrementDay = new JButton("- Day");
		m_BtnSetSchedule = new JButton("Set Schedule");
		m_BtnSetThresholdTemp = new JButton("Set Threshold");	
		m_BtnEnable = new JButton("Enable");
                m_BtnDisable=new JButton("Disable");
		m_BtnActivation = new JButton("Activate");
		m_BtnStatus = new JButton("Status");
		m_LblSystemDate = new JLabel(SprinklerFormatter.dateLabelFormatter(GardenSystem.getInstance().getDate()));
		m_LblMinThreshold = new JLabel(SprinklerFormatter.minThresholdFormatter(GardenSystem.getInstance().getMinThreshold()));
		m_LblMaxThreshold = new JLabel(SprinklerFormatter.maxThresholdFormatter(GardenSystem.getInstance().getMaxThreshold()));
		m_BtnIncrementDay.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnDecrementDay.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnSetSchedule.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnSetThresholdTemp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnEnable.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
                m_BtnDisable.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnActivation.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnStatus.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));	
		m_BtnIncrementDay.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_BtnDecrementDay.setFont(new Font(FONT_TYPE, Font.PLAIN,FONT_SIZE));
		m_BtnSetSchedule.setFont(new Font(FONT_TYPE, Font.PLAIN,FONT_SIZE));
		m_BtnSetThresholdTemp.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_BtnEnable.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
                m_BtnDisable.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_BtnActivation.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_BtnStatus.setFont(new Font(FONT_TYPE, Font.PLAIN,FONT_SIZE));
		m_LblSystemDate.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_LblMinThreshold.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_LblMaxThreshold.setFont(new Font(FONT_TYPE, Font.PLAIN,FONT_SIZE));
		
		m_LblSystemDate.setForeground(Color.WHITE);
		m_LblMinThreshold.setForeground(Color.WHITE);
		m_LblMaxThreshold.setForeground(Color.WHITE);
		
		m_LblSystemDate.setBorder(new EmptyBorder(0, LABEL_SPACING, 0, LABEL_SPACING));
		m_LblMinThreshold.setBorder(new EmptyBorder(0, LABEL_SPACING, 0, LABEL_SPACING));
		m_LblMaxThreshold.setBorder(new EmptyBorder(0, LABEL_SPACING, 0, LABEL_SPACING));
		
		setBackground(Color.GRAY);
		
		setActionListeners();

		m_ButtonPanel.add(m_BtnDecrementDay);
		m_ButtonPanel.add(m_BtnIncrementDay);
		m_ButtonPanel.add(m_BtnSetSchedule);
		m_ButtonPanel.add(m_BtnSetThresholdTemp);
		
		m_ButtonPanel.add(m_BtnEnable);
                m_ButtonPanel.add(m_BtnDisable);
		m_ButtonPanel.add(m_BtnActivation);
		m_ButtonPanel.add(m_BtnStatus);
		
		m_LabelPanel.add(m_LblSystemDate);
		m_LabelPanel.add(m_LblMinThreshold);
		m_LabelPanel.add(m_LblMaxThreshold);
		
		add(m_ButtonPanel, BorderLayout.NORTH);
		add(m_LabelPanel, BorderLayout.CENTER);
	}
	
        //
	private void setActionListeners(){
		m_BtnIncrementDay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				GardenSystem.getInstance().addDays(1);
				m_LblSystemDate.setText(SprinklerFormatter.dateLabelFormatter(GardenSystem.getInstance().getDate()));
			}		
		});
		
		m_BtnDecrementDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GardenSystem.getInstance().minusDays(1);
				m_LblSystemDate.setText(SprinklerFormatter.dateLabelFormatter(GardenSystem.getInstance().getDate()));
			}
		});
		
		m_BtnSetSchedule.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetSchedule Pressed");
				MainUserInterface.UserInterface.clearContainer();
				MainUserInterface.UserInterface.addToContainer(new SchedulePanel(MyGlobal.getInstance().getUIWidth(),
						MyGlobal.getInstance().getUIHeight() - 87), BorderLayout.NORTH);
				MainUserInterface.UserInterface.addToContainer(new ControlPanel(m_Width, 100), BorderLayout.SOUTH);
			}
		});
		
		m_BtnSetThresholdTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetThresholdTemp Pressed");
				MainUserInterface.UserInterface.clearContainer();
				MainUserInterface.UserInterface.addToContainer(new ThreshouldTempPanel(MyGlobal.getInstance().getUIWidth(),
						MyGlobal.getInstance().getUIHeight() - 87), BorderLayout.NORTH);
				MainUserInterface.UserInterface.addToContainer(new ControlPanel(m_Width, 100), BorderLayout.SOUTH);
			}		
		});
		
		m_BtnEnable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                                 boolean isSysOn;
                                isSysOn=MyGlobal.getInstance().getSysOn();
                                double curTemperature=GardenSystem.getInstance().getTemperature();
                                double minTempThreshould=GardenSystem.getInstance().getMinThreshold();
                                double maxTempThreshould=GardenSystem.getInstance().getMaxThreshold();
                                if(curTemperature<minTempThreshould){
                                    GardenSystem.getInstance().setMinTemperatureSystem();
                                }
                                if(curTemperature>maxTempThreshould){
                                    GardenSystem.getInstance().setMaxTemperatureSystem();
                                }
                                if((curTemperature>=minTempThreshould)&&(curTemperature<=maxTempThreshould)){
                                        if (!isSysOn) {
                                            GardenSystem instance=GardenSystem.getInstance();
                                             JOptionPane.showMessageDialog(null,"Successfully sprinkler system enable");
                                            isSysOn=true;
                                            MyGlobal.getInstance().setSysOn(isSysOn);
                                             m_clusters=GardenSystem.getInstance().getMapSprinklerCluster();
                                            SprinklerCluster northCluster=m_clusters.get(SprinklerDirections.NORTH);
                                            for(int i=0;i<northCluster.getCount();i++){
                                                int index=i+1;
                                                String str_index=String.valueOf(index);
                                                String strid=SprinklerDirections.NORTH.toString()+str_index;
                                                northCluster.getSprinkler(strid).activate();
                                            }
                                            SprinklerCluster southCluster=m_clusters.get(SprinklerDirections.SOUTH);
                                            for(int i=0;i<southCluster.getCount();i++){
                                                int index=i+1;
                                                String str_index=String.valueOf(index);
                                                String strid=SprinklerDirections.SOUTH.toString()+str_index;
                                                southCluster.getSprinkler(strid).activate();
                                            }
                                            SprinklerCluster westCluster=m_clusters.get(SprinklerDirections.WEST);
                                            for(int i=0;i<westCluster.getCount();i++){
                                                int index=i+1;
                                                String str_index=String.valueOf(index);
                                                String strid=SprinklerDirections.WEST.toString()+str_index;
                                                westCluster.getSprinkler(strid).activate();
                                            }
                                            SprinklerCluster eastCluster=m_clusters.get(SprinklerDirections.EAST);
                                            for(int i=0;i<eastCluster.getCount();i++){
                                                int index=i+1;
                                                String str_index=String.valueOf(index);
                                                String strid=SprinklerDirections.EAST.toString()+str_index;
                                                eastCluster.getSprinkler(strid).activate();
                                            }
                                             GardenSystem.getInstance().setMapSprinklerCluster(m_clusters);
                                        }else{
                                             JOptionPane.showMessageDialog(null,"Now sprinkler system is working well,You might click disable");
                                        }
                                }
                            }		
		});
                
                m_BtnDisable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                                 boolean isSysOn;
                                isSysOn=MyGlobal.getInstance().getSysOn();
                                if (isSysOn) {
                                    JOptionPane.showMessageDialog(null,"Successfully sprinkler system disable");
                                    isSysOn=false;
                                    MyGlobal.getInstance().setSysOn(isSysOn);
                                    m_clusters=GardenSystem.getInstance().getMapSprinklerCluster();
                                    SprinklerCluster northCluster=m_clusters.get(SprinklerDirections.NORTH);
                                    for(int i=0;i<northCluster.getCount();i++){
                                        int index=i+1;
                                        String str_index=String.valueOf(index);
                                        String strid=SprinklerDirections.NORTH.toString()+str_index;
                                        northCluster.getSprinkler(strid).deactivate();
                                        
                                    }
                                    SprinklerCluster southCluster=m_clusters.get(SprinklerDirections.SOUTH);
                                    for(int i=0;i<southCluster.getCount();i++){
                                        int index=i+1;
                                        String str_index=String.valueOf(index);
                                        String strid=SprinklerDirections.SOUTH.toString()+str_index;
                                        southCluster.getSprinkler(strid).deactivate();
                                    }
                                    SprinklerCluster westCluster=m_clusters.get(SprinklerDirections.WEST);
                                    for(int i=0;i<westCluster.getCount();i++){
                                        int index=i+1;
                                        String str_index=String.valueOf(index);
                                        String strid=SprinklerDirections.WEST.toString()+str_index;
                                        westCluster.getSprinkler(strid).deactivate();
                                    }
                                    SprinklerCluster eastCluster=m_clusters.get(SprinklerDirections.EAST);
                                    for(int i=0;i<eastCluster.getCount();i++){
                                        int index=i+1;
                                        String str_index=String.valueOf(index);
                                        String strid=SprinklerDirections.EAST.toString()+str_index;
                                        eastCluster.getSprinkler(strid).deactivate();
                                    }
                                    GardenSystem.getInstance().setMapSprinklerCluster(m_clusters);
                                    
                                }else{
                                   JOptionPane.showMessageDialog(null,"Now sprinkler system is not working,You can click enable"); 
                                }
                                
                            }		
		});
		
		m_BtnActivation.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MainUserInterface.UserInterface.clearContainer();
				MainUserInterface.UserInterface.addToContainer(new SprinklerActivationPanel(MyGlobal.getInstance().getUIWidth(),
						MyGlobal.getInstance().getUIHeight() - 87), BorderLayout.NORTH);
				MainUserInterface.UserInterface.addToContainer(new ControlPanel(m_Width, 100), BorderLayout.SOUTH);
			}		
		});
		
		m_BtnStatus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetStatus Pressed");
				MainUserInterface.UserInterface.clearContainer();
				MainUserInterface.UserInterface.addToContainer(new SprinklerStatusPanel(MyGlobal.getInstance().getUIWidth(),
						MyGlobal.getInstance().getUIHeight() - 87), BorderLayout.NORTH);
				MainUserInterface.UserInterface.addToContainer(new ControlPanel(m_Width, 100), BorderLayout.SOUTH);
			}		
		});
		
	} 
    
}
