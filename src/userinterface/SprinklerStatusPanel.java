/*
 *This class is the class to set configuration
*class name : SprinklerStatusPanel
* create file date : 2016/12/3
*author: johnsondl
 */
package userinterface;
import dataenums.SprinklerDirections;
import dataenums.SprinklerStatus;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea; 
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import system.GardenSystem;
import system.MyGlobal;
import system.Sprinkler;
import system.SprinklerCluster;
public class SprinklerStatusPanel extends JPanel{
    private static final String FONT_TYPE=MyGlobal.getInstance().getFontType();
    private static final int FONT_SIZE=MyGlobal.getInstance().getFontSize();
    private JScrollPane jScrollPaneFunctional, jScrollPaneNonFunctional;
    private JTextArea m_TextAreaFunctional, m_TextAreaNonFunctional;
    private StringBuilder m_FunctionalBuilder,m_NonFunctionalBuilder;
     private JPanel masterPaneNorth;
    private JPanel masterPaneSouth;
    private JPanel masterPaneEast;
    private JPanel masterPaneWest;
    private  Font fontSmall;
 
	public SprinklerStatusPanel(int width, int height){
        super(new BorderLayout());
        setPreferredSize(new Dimension(width, height));
        m_FunctionalBuilder = new StringBuilder();
        m_NonFunctionalBuilder = new StringBuilder();
        m_TextAreaFunctional = new JTextArea();
        m_TextAreaFunctional.setColumns(40);
        m_TextAreaFunctional.setLineWrap(true);
        m_TextAreaFunctional.setRows(5);
        m_TextAreaFunctional.setWrapStyleWord(true);
        jScrollPaneFunctional = new JScrollPane(m_TextAreaFunctional);
        m_TextAreaFunctional.setEditable(false);
        m_TextAreaNonFunctional = new JTextArea();
        m_TextAreaNonFunctional.setColumns(40);
        m_TextAreaNonFunctional.setLineWrap(true);
        m_TextAreaNonFunctional.setRows(5);
        m_TextAreaNonFunctional.setWrapStyleWord(true);
        jScrollPaneNonFunctional = new JScrollPane(m_TextAreaNonFunctional);
        m_TextAreaNonFunctional.setEditable(false);
        setStringBuilders(); 
        fontSmall = new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE);
        m_TextAreaFunctional.setText(m_FunctionalBuilder.toString());
        m_TextAreaNonFunctional.setText(m_NonFunctionalBuilder.toString());
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.add(jScrollPaneFunctional);
        textAreaPanel.add(jScrollPaneNonFunctional);
        JPanel btnPanel = new JPanel();
        HashMap<SprinklerDirections, SprinklerCluster> m_clusters=GardenSystem.getInstance().getMapSprinklerCluster();
         masterPaneNorth = createPanel(m_clusters.get(SprinklerDirections.NORTH));
        masterPaneEast = createPanel(m_clusters.get(SprinklerDirections.EAST));
        masterPaneSouth = createPanel(m_clusters.get(SprinklerDirections.SOUTH));
        masterPaneWest = createPanel(m_clusters.get(SprinklerDirections.WEST));
        JScrollPane scrollPaneNorth = new JScrollPane(masterPaneNorth);
        JScrollPane scrollPaneSouth = new JScrollPane(masterPaneSouth);
        JScrollPane scrollPaneEast = new JScrollPane(masterPaneEast);
        JScrollPane scrollPaneWest = new JScrollPane(masterPaneWest);
        JPanel masterPaneGroup = new JPanel();
        masterPaneGroup.setLayout(new GridLayout(2, 2));
        masterPaneGroup.add(scrollPaneNorth);
        masterPaneGroup.add(scrollPaneSouth);
        masterPaneGroup.add(scrollPaneEast);
        masterPaneGroup.add(scrollPaneWest); 
        this.add(btnPanel, BorderLayout.SOUTH);
         this.add(masterPaneGroup, BorderLayout.CENTER); 
	}
        private void setActionListeners(){
		
	} 
        
          private JPanel createPanel(SprinklerCluster cluster) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                JPanel groupPanel = new JPanel();
                groupPanel.setLayout(new GridLayout(4, 3));
                JLabel groupName = new JLabel("Group"+cluster.getId().toString());
                groupName.setFont(fontSmall);
                 
                for(int i=0;i<cluster.getCount();i++){
                    int id=i+1;
                    String str_index=String.valueOf(id);
                    Sprinkler spk=cluster.getSprinkler(cluster.getId()+str_index);
                    JLabel mlabel=new JLabel("Active:"+cluster.getId()+str_index);
                    mlabel.setFont(fontSmall);
                    groupPanel.add(mlabel);
                     JLabel itemStatusActivation = new JLabel();
                     if(spk.isActive())
                     {
                           itemStatusActivation.setText(SprinklerStatus.ON.toString());
                          
                     }
                     else{
                          itemStatusActivation.setText(SprinklerStatus.NOTON.toString());
                          
                     }
                   itemStatusActivation.setFont(fontSmall);
                   groupPanel.add(itemStatusActivation);
                   JLabel nlabel=new JLabel("Functional:"+cluster.getId()+str_index);
                    nlabel.setFont(fontSmall);
                     JLabel itemStatusFunctional = new JLabel();
                     if(spk.isFunctional())
                     {
                           itemStatusFunctional.setText(SprinklerStatus.OK.toString());
                          
                     }
                     else{
                          itemStatusFunctional.setText(SprinklerStatus.NOTOK.toString());  
                     }
                    groupPanel.add(nlabel);
                   groupPanel.add(itemStatusFunctional); 
                } 
               groupPanel.add(groupName);
                panel.add(groupPanel);
                return panel;
    }

	private void setStringBuilders(){
	}
        
        public void updateStatus() {
        masterPaneNorth.invalidate();
        masterPaneNorth.repaint();

        masterPaneEast.invalidate();
        masterPaneEast.repaint();

        masterPaneSouth.invalidate();
        masterPaneSouth.repaint();

        masterPaneWest.invalidate();
        masterPaneWest.repaint();

    } 
         private JPanel getPanelBasedOnName(String name) {
        switch (name) {
            case "NORTH":
                return masterPaneNorth;
            case "SOUTH":
                return masterPaneSouth;
            case "EAST":
                return masterPaneEast;
            case "WEST":
                return masterPaneWest;
            default:
                System.out.println("No matching group names");
                return new JPanel();
        }
    }
         
           public void createIndividualStatus(String groupName, Map<String, Boolean[]> northEachStatus) {
        JPanel panel = getPanelBasedOnName(groupName);
        for (Map.Entry<String, Boolean[]> entry : northEachStatus.entrySet()) {
            JPanel individualPanel = createIndividualPanel(entry.getKey(), entry.getValue());
            panel.add(individualPanel);
        }
    } 
       
          public void showGroupStatus(Map<String, Boolean> groupStatusMap) {
        for (Map.Entry<String, Boolean> entry : groupStatusMap.entrySet()) {
            JPanel parentPanel = (JPanel)getPanelBasedOnName(entry.getKey()).getComponent(0);
            switch (entry.getKey()) {
                case "NORTH":
                    parentPanel.getComponent(2).setName("NORTH");
                    getPanelBasedOnName(entry.getKey()).setBorder(new TitledBorder(new EtchedBorder(), "NORTH"));
                    ((javax.swing.border.TitledBorder)getPanelBasedOnName(entry.getKey()).getBorder()).setTitleFont(fontSmall);
                    break;
                case "SOUTH":
                    parentPanel.getComponent(2).setName("SOUTH");
                    getPanelBasedOnName(entry.getKey()).setBorder(new TitledBorder(new EtchedBorder(), "SOUTH"));
                    ((javax.swing.border.TitledBorder)getPanelBasedOnName(entry.getKey()).getBorder()).setTitleFont(fontSmall);
                    break;
                case "EAST":
                    parentPanel.getComponent(2).setName("EAST");
                    getPanelBasedOnName(entry.getKey()).setBorder(new TitledBorder(new EtchedBorder(), "EAST"));
                    ((javax.swing.border.TitledBorder)getPanelBasedOnName(entry.getKey()).getBorder()).setTitleFont(fontSmall);
                    break;
                case "WEST":
                    parentPanel.getComponent(2).setName("WEST");
                    getPanelBasedOnName(entry.getKey()).setBorder(new TitledBorder(new EtchedBorder(), "WEST"));
                    ((javax.swing.border.TitledBorder)getPanelBasedOnName(entry.getKey()).getBorder()).setTitleFont(fontSmall);
                    break;
                default:
                    System.out.println("No matching group names");
            }
            ((JLabel)parentPanel.getComponent(1)).setText(entry.getValue() ? "ON" : "NOTON");
            ((JButton)parentPanel.getComponent(2)).setText(entry.getValue() ? "DISABLE" : "ENABLE");
        }
    } 
         
         
          private JPanel createIndividualPanel(String sprinklerID, Boolean[] sprinklerStatusMap) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        JLabel sprinklerName = new JLabel();
        sprinklerName.setText(sprinklerID);
        sprinklerName.setFont(fontSmall);

        JLabel sprinklerCurrentlyOn = new JLabel();
        sprinklerCurrentlyOn.setText(sprinklerStatusMap[0] ? "ON" : "NOTON");
        sprinklerCurrentlyOn.setFont(fontSmall);

        JLabel sprinklerFunctional = new JLabel();
        sprinklerFunctional.setText(sprinklerStatusMap[1] ? "OK" : "NOTOK*");
        sprinklerFunctional.setFont(fontSmall);

        JButton sprinklerStatusChange = new JButton();
        sprinklerStatusChange.setText(sprinklerStatusMap[0] ? "DISABLE" : "ENABLE");
        sprinklerStatusChange.setName(sprinklerID);
        sprinklerStatusChange.setFont(fontSmall);
        if (!sprinklerStatusMap[1]) {
            sprinklerStatusChange.setEnabled(false);
        }
        panel.add(sprinklerName);
        panel.add(sprinklerCurrentlyOn);
        panel.add(sprinklerFunctional);
        panel.add(sprinklerStatusChange);
        return panel;
    }
 
           public void updateIndividualStatus(String groupName, Map<String, Boolean[]> groupEachStatus) {
        JPanel panel = getPanelBasedOnName(groupName);
        int count = 1;
        for (Map.Entry<String, Boolean[]> entry : groupEachStatus.entrySet()) {
            JPanel individualPanel = (JPanel) panel.getComponent(count);
            count++;

            String sprinklerID = entry.getKey();
            Boolean[] sprinklerStatusMap = entry.getValue();

            JLabel sprinklerName = (JLabel) individualPanel.getComponent(0);
            sprinklerName.setText(sprinklerID);
            sprinklerName.setFont(fontSmall);

            JLabel sprinklerCurrentlyOn = (JLabel) individualPanel.getComponent(1);
            sprinklerCurrentlyOn.setText(sprinklerStatusMap[0] ? "ON" : "NOTON");
            sprinklerCurrentlyOn.setFont(fontSmall);

            JLabel sprinklerFunctional = (JLabel) individualPanel.getComponent(2);
            sprinklerFunctional.setText(sprinklerStatusMap[1] ? "OK" : "NOTOK*");
            sprinklerFunctional.setFont(fontSmall);

            JButton sprinklerStatusChange = (JButton) individualPanel.getComponent(3);
            sprinklerStatusChange.setText(sprinklerStatusMap[0] ? "DISABLE" : "ENABLE");
            sprinklerStatusChange.setName(sprinklerID);
            sprinklerStatusChange.setFont(fontSmall);
        }
    }
  public void addIndividualStatusListener(String groupName, ActionListener listener) {
        JPanel panel = getPanelBasedOnName(groupName);

        for (int i = 1; i < panel.getComponentCount(); i++) {
            JPanel individualPanel = (JPanel)getPanelBasedOnName(groupName).getComponent(i);
            ((JButton)individualPanel.getComponent(3)).addActionListener(listener);
        }
    }
    public void addGroupStatusListener(String groupName, ActionListener listener) {
        JPanel panel = (JPanel)getPanelBasedOnName(groupName).getComponent(0);
        ((JButton)panel.getComponent(2)).addActionListener(listener);
    }          
	
}
