/*
 *This class is the class to implement Control Panel
*class name : ControlPanel
* create file date : 2016/12/3
*author: johnsondl
 */
package userinterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import system.GardenSystem;
import system.MyGlobal;
import utils.SprinklerFormatter;
public class ControlPanel extends JPanel{
    
    private static final int BUTTON_WIDTH =MyGlobal.getInstance().getButtonWidth();
    private static final int BUTTON_HEIGHT = MyGlobal.getInstance().getButtonHeight();
    private static final String FONT_TYPE=MyGlobal.getInstance().getFontType();
    private static final int FONT_SIZE=MyGlobal.getInstance().getFontSize();
    private int m_Width, m_Height;
    private JButton m_BtnIncTemp;
    private JButton m_BtnDecTemp;
    private JButton m_BtnConfig;
    private JButton m_BtnUsage;
    private JButton m_BtnHome;
    private JLabel m_LblTemp;
	
	public ControlPanel(int width, int height) {
		super();
		this.m_Width = width;
		this.m_Height = height;
		m_BtnIncTemp = new JButton("Increase Temperature");
		m_BtnDecTemp = new JButton("Decrease Temperature");
		m_BtnConfig = new JButton("Configuration");
		m_BtnUsage = new JButton("View Usage");
		m_BtnHome = new JButton("Home Garden");
		m_LblTemp = new JLabel(SprinklerFormatter.getTemperatureFormatter(GardenSystem.getInstance().getTemperature()));
		m_BtnIncTemp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnDecTemp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnConfig.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnUsage.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnHome.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		m_BtnIncTemp.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_BtnDecTemp.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_BtnConfig.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_BtnUsage.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_BtnHome.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));
		m_LblTemp.setFont(new Font(FONT_TYPE, Font.PLAIN,FONT_SIZE));
		m_LblTemp.setForeground(Color.WHITE);
                
		setActionListeners();
		
		setBackground(Color.DARK_GRAY);
		//add the buttons to the panel
		add(m_BtnHome);
		add(m_BtnUsage);
		add(m_BtnConfig);
		add(m_BtnDecTemp);
		add(m_BtnIncTemp);
		add(m_LblTemp);
	}
	//carry out various button events
	private void setActionListeners(){
		m_BtnHome.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MainUserInterface.UserInterface.clearContainer();
				JPanel screen = new HomeGardenPanel(MyGlobal.getInstance().getUIWidth(),
						MyGlobal.getInstance().getUIHeight() - 87);
				MainUserInterface.UserInterface.addToContainer(screen, BorderLayout.NORTH);
				MainUserInterface.UserInterface.addToContainer(new ControlPanel(m_Width, 100), BorderLayout.SOUTH);
				MainUserInterface.UserInterface.getInstance().setCurrentScreen(screen);
			}
		});
		
		m_BtnIncTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				GardenSystem.getInstance().incrementTemperature();
				m_LblTemp.setText(SprinklerFormatter.getTemperatureFormatter(GardenSystem.getInstance().getTemperature()));
			}
		});
		
		m_BtnDecTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				GardenSystem.getInstance().decrementTemperature();
				m_LblTemp.setText(SprinklerFormatter.getTemperatureFormatter(GardenSystem.getInstance().getTemperature()));
			}
		});
		
		m_BtnConfig.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MainUserInterface.UserInterface.clearContainer();
				JPanel screen = new SprinklerConfigurationPanel(MyGlobal.getInstance().getUIWidth(),
						MyGlobal.getInstance().getUIHeight() - 87);
				MainUserInterface.UserInterface.addToContainer(screen, BorderLayout.NORTH);
				MainUserInterface.UserInterface.addToContainer(new ControlPanel(m_Width, 100), BorderLayout.SOUTH);
				MainUserInterface.UserInterface.getInstance().setCurrentScreen(screen);
			}
		});
		
		m_BtnUsage.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MainUserInterface.UserInterface.clearContainer();
				JPanel screen = new SprinklerUsagePanel(MyGlobal.getInstance().getUIWidth(),
						MyGlobal.getInstance().getUIHeight() - 87);
				MainUserInterface.UserInterface.addToContainer(screen, BorderLayout.NORTH);
				MainUserInterface.UserInterface.addToContainer(new ControlPanel(m_Width, 100), BorderLayout.SOUTH);
				MainUserInterface.UserInterface.getInstance().setCurrentScreen(screen);
			}
		});
	} 
    
}
