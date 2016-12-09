/*
 *This class is the class to set Specific date Panel
*class name : SprinklerActivationPanel
* create file date : 2016/12/3
*author: johnsondl
 */
package userinterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dataenums.SprinklerDirections;
import java.util.HashMap;
import system.GardenSystem;
import system.InterfaceSprinkler;
import system.MyGlobal;
import system.Sprinkler;
import system.SprinklerCluster; 
public class SprinklerActivationPanel extends JPanel{
    private final double RATE1=0.7;
    private final double RATE2=0.15;
    private final String FONT_TYPE=MyGlobal.getInstance().getFontType();
    private final int FONT_SIZE=MyGlobal.getInstance().getFontSize();
    private ClusterPanel m_NorthClusterPanel, m_EastClusterPanel, m_SouthClusterPanel, m_WestClusterPanel;
    private JLabel m_TitleLabel;
     private HashMap<SprinklerDirections, SprinklerCluster> m_clusters;
    public SprinklerActivationPanel(int width, int height){
            super(new BorderLayout());
             m_clusters=GardenSystem.getInstance().getMapSprinklerCluster();
            setPreferredSize(new Dimension(width, height));
            m_NorthClusterPanel = new ClusterPanel(SprinklerDirections.NORTH, width, (int) (height * 0.15));
            m_EastClusterPanel = new ClusterPanel(SprinklerDirections.EAST, (int) (width * 0.15), (int) (height * 0.7));
            m_SouthClusterPanel = new ClusterPanel(SprinklerDirections.SOUTH, width, (int) (height * 0.15));
            m_WestClusterPanel = new ClusterPanel(SprinklerDirections.WEST, (int) (width * 0.15), (int) (height * 0.7));
            m_TitleLabel = new JLabel("Toggle sprinkler and sprinkler cluster activation");
            m_TitleLabel.setFont(new Font(FONT_TYPE, Font.PLAIN, 2*FONT_SIZE));
            m_TitleLabel.setHorizontalAlignment(JLabel.CENTER);
            m_TitleLabel.setVerticalAlignment(JLabel.CENTER);
            add(m_NorthClusterPanel, BorderLayout.NORTH);
            add(m_EastClusterPanel, BorderLayout.EAST);
            add(m_SouthClusterPanel, BorderLayout.SOUTH);
            add(m_WestClusterPanel, BorderLayout.WEST);
            add(m_TitleLabel, BorderLayout.CENTER);
            add(m_TitleLabel);
    }
	
	class ClusterPanel extends JPanel {
		private SprinklerButton[] btnsSprinkler;
		public ClusterPanel(SprinklerDirections direction, int width, int height) {
			setPreferredSize(new Dimension(width, height));			
			boolean isHorizontal = (height >= width);
			SprinklerCluster cluster = GardenSystem.getInstance().getCluster(direction);
			int btnCount = cluster.getCount() + 1;
			int btnWidth, btnHeight;
			if (isHorizontal) {
				setLayout(new GridLayout(btnCount, 1));
				btnWidth = width / btnCount;
				btnHeight = height;
			}
			else {
				setLayout(new GridLayout(1, btnCount));
				btnWidth = width;
				btnHeight = height / btnCount;
			}
			
			btnsSprinkler = new SprinklerButton[cluster.getCount() + 1];
			btnsSprinkler[0] = new SprinklerButton(cluster, btnWidth, btnHeight);
			
			add(btnsSprinkler[0]);
			
			int btnsIndex = 1;
			Iterator<Sprinkler> iterator = cluster.getIterator();
			while (iterator.hasNext()) {
				Sprinkler sprinkler = iterator.next();
				btnsSprinkler[btnsIndex] = new SprinklerButton(sprinkler, btnWidth, btnHeight);
				add(btnsSprinkler[btnsIndex]);
				btnsIndex++;
			}
		}
		//the method to set button title
		public void setBtnTitles() {
			for (int i = 0; i < btnsSprinkler.length; i++) {
				btnsSprinkler[i].setBtnTitle();
			}
		}
		
		class SprinklerButton extends JButton {
			private InterfaceSprinkler m_Sprinkler;
			
			public SprinklerButton(InterfaceSprinkler sprinkler, int width, int height) {
				setPreferredSize(new Dimension(width, height));
				this.m_Sprinkler = sprinkler;
				setText(getButtonTitle(sprinkler));
				
				addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (sprinkler.isActive()) {
							sprinkler.deactivate();
                                                        
						}
						else {
							sprinkler.activate();
                                                        String[] str_split=getButtonTitle(sprinkler).split(" ");
                                                        String sprinklerid=str_split[1];
                                                        String directions=sprinklerid.substring(0,sprinklerid.length()-1);
                                                        if(directions.equals("North")){
                                                           // m_clusters.get(SprinklerDirections.NORTH).getSprinkler(sprinklerid).setActivate(false);
                                                        }
                                                        GardenSystem.getInstance().setMapSprinklerCluster(m_clusters);
						}
						setBtnTitles();
					}
				});
			}
			
			public void setBtnTitle() {
				setText(getButtonTitle(m_Sprinkler));
			}
			
			private String getButtonTitle(InterfaceSprinkler sprinkler) {
				String verb;
				if (sprinkler.isActive()) {
					verb = "Deactivate ";
				}
				else {
					verb = "Activate ";
				}
				
				if (sprinkler instanceof Sprinkler) {
					return verb + sprinkler.getId() + " Sprinkler";
				}
				else {
					return verb + sprinkler.getId() + " Cluster";
				}
			}
			
		}
		
	} 
    
}
