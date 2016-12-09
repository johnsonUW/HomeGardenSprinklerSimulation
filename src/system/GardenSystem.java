/*
 *This class is the class to implement the garden system 
*class name : GardenSystem
* create file date : 2016/12/3
*author: johnsondl
 */
package system;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import dataenums.SprinklerDirections;
import dataenums.WeekDays;
import java.util.List;

public class GardenSystem {
    private HashMap<SprinklerDirections, SprinklerCluster> clusters;
	private SprinklerSystemDate date;
	private Thermometer thermometer;
	private SprinklerSchedule schedule;
        private HashMap<WeekDays,int[]> m_StartSchedule;
        private HashMap<WeekDays,int[]> m_EndSchedule;
        
        private boolean isOn;
        private List<SprinklerCluster> m_ClusterList;
	
	private static GardenSystem garden = null;
	
	public static GardenSystem getInstance() {
		if (garden == null) {
			garden = new GardenSystem();
		}
		
		return garden;
	}
	
	private GardenSystem() {
                this.isOn=true;
            
		clusters = new HashMap<SprinklerDirections, SprinklerCluster>();
		clusters.put(SprinklerDirections.NORTH, new SprinklerCluster(SprinklerDirections.NORTH));
		clusters.put(SprinklerDirections.EAST, new SprinklerCluster(SprinklerDirections.EAST));
		clusters.put(SprinklerDirections.SOUTH, new SprinklerCluster(SprinklerDirections.SOUTH));
		clusters.put(SprinklerDirections.WEST, new SprinklerCluster(SprinklerDirections.WEST));
		
		initializeSprinklersInClusters(3);
		
		date = new SprinklerSystemDate();
		thermometer = new Thermometer();
		schedule = new SprinklerSchedule();
	}
	
	private void initializeSprinklersInClusters(int sprinklersPerCluster) {
		for (int i = 0; i < sprinklersPerCluster; i++) {
			clusters.get(SprinklerDirections.NORTH).addSprinkler();
			clusters.get(SprinklerDirections.EAST).addSprinkler();
			clusters.get(SprinklerDirections.SOUTH).addSprinkler();
			clusters.get(SprinklerDirections.WEST).addSprinkler();
		}
	}
	public HashMap<SprinklerDirections, SprinklerCluster> getMapSprinklerCluster(){
            return clusters;
        }
        
        public void setMapSprinklerCluster(HashMap<SprinklerDirections, SprinklerCluster> m_clusters){
             this.clusters=m_clusters;
        }
        //the method to get status
        public HashMap<String,Boolean[]> getSprinklerStatus(String groupName){
		HashMap<String, Boolean[]> res = new HashMap<>();
                for(int i=0;i<clusters.get(groupName).getCount();i++){
                    Sprinkler spk = clusters.get(groupName).getSprinkler(groupName);
                    Boolean[] status = new Boolean[2];
                    status[0]=spk.isActive();
                    status[1]=spk.isFunctional();
                    res.put(spk.getId(), status);
                }    		
		return res;
	}
	
        public  HashMap<WeekDays,int[]> getMapStartSchedule(){
            return m_StartSchedule;
        }
        
          public  HashMap<WeekDays,int[]> getMapEndSchedule(){
            return m_EndSchedule;
        }
        
        public void setSysStatus(boolean isOn){
		if(isOn) setEnableSystem();
		else setDisableSystem();
	}	
        
        
        public void setMinTemperatureSystem(){
            int i;
           SprinklerCluster northCluster= clusters.get(SprinklerDirections.NORTH);
           SprinklerCluster southCluster= clusters.get(SprinklerDirections.SOUTH);
           SprinklerCluster eastCluster= clusters.get(SprinklerDirections.EAST);
           SprinklerCluster westCluster= clusters.get(SprinklerDirections.WEST);
            
           double curTem=getTemperature();
           double minTemThreshould=getMinThreshold();
           if(curTem<minTemThreshould){
               for(i=0;i<northCluster.getCount();i++){
                   int id=i+1;
                   String str_Id=SprinklerDirections.NORTH.toString()+String.valueOf(id);
                   Sprinkler spk=northCluster.getSprinkler(str_Id);
                   spk.deactivate();
               }
               
               for(i=0;i<southCluster.getCount();i++){
                   int id=i+1;
                   String str_Id=SprinklerDirections.SOUTH.toString()+String.valueOf(id);
                   Sprinkler spk=southCluster.getSprinkler(str_Id);
                   spk.deactivate();
               }
               
               for(i=0;i<westCluster.getCount();i++){
                   int id=i+1;
                   String str_Id=SprinklerDirections.WEST.toString()+String.valueOf(id);
                   Sprinkler spk=westCluster.getSprinkler(str_Id);
                   spk.deactivate();
               }
               
               for(i=0;i<eastCluster.getCount();i++){
                   int id=i+1;
                   String str_Id=SprinklerDirections.EAST.toString()+String.valueOf(id);
                   Sprinkler spk=eastCluster.getSprinkler(str_Id);
                   spk.deactivate();
               }
               
           }
          
        }
        
        public void setMaxTemperatureSystem(){
            int i;
           SprinklerCluster northCluster= clusters.get(SprinklerDirections.NORTH);
           SprinklerCluster southCluster= clusters.get(SprinklerDirections.SOUTH);
           SprinklerCluster eastCluster= clusters.get(SprinklerDirections.EAST);
           SprinklerCluster westCluster= clusters.get(SprinklerDirections.WEST);
            
           double curTem=getTemperature();
           double maxTemThreshould=getMaxThreshold();
           if(curTem>maxTemThreshould){
               for(i=0;i<northCluster.getCount();i++){
                   int id=i+1;
                   String str_Id=SprinklerDirections.NORTH.toString()+String.valueOf(id);
                   Sprinkler spk=northCluster.getSprinkler(str_Id);
                   spk.deactivate();
               }
               
               for(i=0;i<southCluster.getCount();i++){
                   int id=i+1;
                   String str_Id=SprinklerDirections.SOUTH.toString()+String.valueOf(id);
                   Sprinkler spk=southCluster.getSprinkler(str_Id);
                   spk.deactivate();
               }
               
               for(i=0;i<westCluster.getCount();i++){
                   int id=i+1;
                   String str_Id=SprinklerDirections.WEST.toString()+String.valueOf(id);
                   Sprinkler spk=westCluster.getSprinkler(str_Id);
                   spk.deactivate();
               }
               
               for(i=0;i<eastCluster.getCount();i++){
                   int id=i+1;
                   String str_Id=SprinklerDirections.EAST.toString()+String.valueOf(id);
                   Sprinkler spk=eastCluster.getSprinkler(str_Id);
                   spk.deactivate();
               }
               
           }
        }
        public void setEnableSystem(){
			this.isOn = true;
			for(SprinklerCluster cluster : m_ClusterList){
//				group.restartGroupTimer();
				System.out.println("Group " + cluster.getId().toString() + " has been restarat!");
			}
	}
        public void setDisableSystem(){
		for(SprinklerCluster cluster: m_ClusterList){
			cluster.setStatus(false);;
//			group.stopGroupTimer();
			System.out.println("All system sprinklers have been turned off.");
		}
	}	
        
        
        public boolean getSysStatus(){
            return isOn;
        }
        
	public SprinklerCluster getCluster(SprinklerDirections direction) {
		return clusters.get(direction);
	}
	
	public double getTotalClusterUsage(SprinklerDirections direction) {
		return clusters.get(direction).getTotalUsage();
	}
	
	public double getTemperature() {
		return thermometer.getTemperature();
	}
	
	public void incrementTemperature() {
		thermometer.incrementTemperature();
	}
	
	public void decrementTemperature() {
		thermometer.decrementTemperature();
	}
	
	public double getMinThreshold() {
		return thermometer.getMinThreshold();
	}
	
	public double getMaxThreshold() {
		return thermometer.getMaxThreshold();
	}
	
	public void setMinThreshold(double threshold) {
		thermometer.setMinThreshold(threshold);
	}
	
	public void setMaxThreshold(double threshold) {
		thermometer.setMaxThreshold(threshold);
	}
	
	public LocalDate getDate() {
		return date.getDate();
	}
	
	public void addDays(int days) {
		date.addDays(days);
	}
	
	public void minusDays(int days) {
		date.minusDays(days);
	}
	
	public LocalTime getTime() {
		return date.getTime();
	} 
}
