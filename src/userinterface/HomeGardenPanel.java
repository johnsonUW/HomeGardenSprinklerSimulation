/*
 *This class is the class to implement Home Garden Panel
*class name : HomeGardenPanel
* create file date : 2016/12/3
*author: johnsondl
 */
package userinterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
public class HomeGardenPanel extends JPanel{
    private static final double RATE1=0.15;
    private static final double RATE2=0.7;
    private JPanel m_NorthClusterPanel;
    private JPanel m_EastClusterPanel;
    private JPanel m_SouthClusterPanel;
    private JPanel m_WestClusterPanel;
    private JPanel m_HousePanel;

	public HomeGardenPanel(int width, int height) {
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		m_NorthClusterPanel = new JPanel();
		m_EastClusterPanel = new JPanel();
		m_SouthClusterPanel = new JPanel();
		m_WestClusterPanel = new JPanel();
		m_HousePanel = new JPanel();
		m_NorthClusterPanel.setPreferredSize(new Dimension(width, (int) (height *RATE1)));
		m_EastClusterPanel.setPreferredSize(
				new Dimension((int) (width * 0.15), (int) (height * RATE2)));
		m_SouthClusterPanel.setPreferredSize(new Dimension(width, (int) (height * RATE1)));
		m_WestClusterPanel.setPreferredSize(
				new Dimension((int) (width * 0.15), (int) (height * RATE2)));
		
		m_NorthClusterPanel.setBackground(Color.RED);
		m_EastClusterPanel.setBackground(Color.YELLOW);
		m_SouthClusterPanel.setBackground(Color.WHITE);
		m_WestClusterPanel.setBackground(Color.BLACK);
		m_HousePanel.setBackground(Color.GREEN);
		//add the panels 
		add(m_NorthClusterPanel, BorderLayout.NORTH);
		add(m_EastClusterPanel, BorderLayout.EAST);
		add(m_SouthClusterPanel, BorderLayout.SOUTH);
		add(m_WestClusterPanel, BorderLayout.WEST);
		add(m_HousePanel, BorderLayout.CENTER);
	} 
}
