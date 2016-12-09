/*
 *This class is the class to implement Main User Interface
*class name : HomeGardenPanel
* create file date : 2016/12/3
*author: johnsondl
 */
package userinterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import system.GardenSystem;
import system.MyGlobal;
import system.Thermometer;
public class MainUserInterface {
    public static class UserInterface extends JFrame {
		private static Dimension m_ScreenSize = null;
		private static UserInterface ui = null;
		private JPanel m_CurrentScreen;
		private UserInterface() {
                    super("Home Garden Sprinkler System"); 
                    m_ScreenSize = MyGlobal.getInstance().init();
                    setPreferredSize(m_ScreenSize);
                    Container container = getContentPane();
                    m_CurrentScreen = MyGlobal.getInstance().getContainer(container);
                    pack();
                    setLocationRelativeTo(null);
                    setVisible(true);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
                //the singlton pattern implementation
		public static UserInterface getInstance() {
			if (ui == null){
				ui = new UserInterface();
			}
			return ui;
		}
                //the method to clear the container
		public static void clearContainer() {
			if (ui == null){
				ui = new UserInterface();
			}
			ui.getContentPane().removeAll();
			ui.getContentPane().repaint();
		}
		//the method to add the cotainer
		public static void addToContainer(JPanel panel, String location) {
			ui.getContentPane().add(panel, location);
			ui.pack();
			ui.setVisible(true);
		}
		//the method the current screen
		public void setCurrentScreen(JPanel screen) {
			m_CurrentScreen = screen;
		}
		//the method update panel
		public void update() {
			if (m_CurrentScreen instanceof SprinklerUsagePanel) {
                                ((SprinklerUsagePanel) m_CurrentScreen).updateGraphs();
			}
		}
	}
}
