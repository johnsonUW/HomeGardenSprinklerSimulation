/*
 *This class is the my global class to init in the system and userinterface package 
*class name : MyGlobal
* create file date : 2016/12/5
*author: johnsondl
 */
package system;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.util.Date;
import userinterface.ControlPanel;
import userinterface.HomeGardenPanel;

public class MyGlobal {
    private static MyGlobal mGlobal = null;
    private double x, y;
    private boolean isSysOn;
    private double waterFlow=0.5;
    //implement the singleton pattern
    public static MyGlobal getInstance() {        
        if (mGlobal == null){
                mGlobal = new MyGlobal();
        }
        return mGlobal;
    }
    //the method to get x-axis rate
    public double getX(){       
        return 0.9;
    }
     //the method to get x-axis rate
    public double getY(){       
        return 0.8;
    }
        
        //to init the screensize dimension
        public Dimension init(){
            Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
            screenDimension.setSize(screenDimension.getWidth() * 0.9, screenDimension.getHeight() * 0.8);
            x = screenDimension.getWidth() * 0.9; y = screenDimension.getHeight() * 0.8;
           return  screenDimension;
                        
        }
        
        public HomeGardenPanel getContainer(Container container){

            container.setLayout(new BorderLayout());			
            HomeGardenPanel homePanel = new HomeGardenPanel((int) (x), (int) (y - 87));

            ControlPanel controlPanel = new ControlPanel((int) (x), 100);

            container.add(homePanel, BorderLayout.NORTH);
            container.add(controlPanel, BorderLayout.SOUTH);
            return homePanel;
        }
        //the method to get UI width
        public  int getUIWidth() {
                return (int) x;
        }
         //the method to get UI height
        public int getUIHeight() {
                return (int) y;
        }
        //the method to get water flow
        public double getWaterFlow(){
            return waterFlow;
        }
        
          public void setWaterFlow(double waterFlow){
              this.waterFlow=waterFlow;
            
        }
        // the program will update the usage every 10 minutes (10 seconds in real time)
        public int getUpdateInterval(){
            return 10;
        }
        //the method to get elapsed seconds
        public double getElapsedSeconds(Date now,Date lastUpdate){
            double elapsedSeconds = (now.getTime() - lastUpdate.getTime()) / 1000;
            return elapsedSeconds;
        }  
        //the method to get update multiply variable
        public int getUpdateMultiply(){
            return 1000;
        }
        //the method to get the width of Button
        public int getButtonWidth(){
            return 190;
        }
        
        //the method to get the height of Button
        public int getButtonHeight(){
            return 50;
        }
        //the method to get font size
        public String getFontType(){
            return "Arial";
        }
        //the method to get the font size
        public int getFontSize(){
            return 16;
        }
        
        //the method to get the label spacing
        public int getLabelSpacing(){
            return 15;
        }
        //the method to get sys available
        public boolean getSysOn(){
            return isSysOn;
        }
        //the method to set sys available
        public void setSysOn(boolean bOn){
            this.isSysOn=bOn;
        }
        
}
