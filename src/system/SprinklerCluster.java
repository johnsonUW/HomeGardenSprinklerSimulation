/*
 *This class is the class to implement clustering of sprinkler 
*class name : SprinklerCluster
* create file date : 2016/12/3
*author: johnsondl
 */
package system;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import dataenums.SprinklerDirections;
import java.util.List;

public class SprinklerCluster implements InterfaceSprinkler{
    //class data member variables
    // map of sprinkler ids to sprinklers in cluster
	private HashMap<String, Sprinkler> m_SprinklerMap;
	//  location of sprinkler cluster
	private SprinklerDirections m_ClusterId;
        private boolean isON;
	//  id of next sprinkler
	private static HashMap<SprinklerDirections, Integer> m_NextId;
	//constructors
        List<Sprinkler> sList;
	public SprinklerCluster() {
		m_SprinklerMap = new HashMap<String, Sprinkler>();
		m_ClusterId = null;
	}
	
	public SprinklerCluster(SprinklerDirections location) {
		m_SprinklerMap = new HashMap<String, Sprinkler>();
		m_ClusterId = location;
		m_NextId = new HashMap<SprinklerDirections, Integer>();
		m_NextId.put(SprinklerDirections.NORTH, 1);
		m_NextId.put(SprinklerDirections.EAST, 1);
		m_NextId.put(SprinklerDirections.SOUTH, 1);
		m_NextId.put(SprinklerDirections.WEST, 1);
	}

	
	@Override
        
        //the method to get activation status of sprinkler cluster
	public boolean isActive() {
		Iterator<Sprinkler> sprinklerIterator = m_SprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			if (current.isActive()) {
				return true;
			}
		}
		
		return false;
	}
        
        @Override
        //the method to get history list of usages 
	public LinkedList<SprinklerDayUsage> getUsageHistory(int daysLookback) {
		LinkedList<SprinklerDayUsage> result = new LinkedList<SprinklerDayUsage>();
		Iterator<Sprinkler> sprinklerIterator = m_SprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			LinkedList<SprinklerDayUsage> sprinklerUsage = current.getUsageHistory(daysLookback);
			for (int i = 0; i < sprinklerUsage.size(); i++) {
				SprinklerDayUsage day = sprinklerUsage.get(i);
				
				if (result.size() == i) {
					result.add(new SprinklerDayUsage(day.getDay(), 0));
				}
				
				SprinklerDayUsage dayUsage = result.get(i);
				dayUsage.addUsage(day.getUsage());
			}
		}
		
		return result;
	}
	
	@Override
        //the method to get functional of sprinkler cludter
	public boolean isFunctional() {
		Iterator<Sprinkler> sprinklerIterator = m_SprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			if (!current.isFunctional()) {
				return false;
			}
		}
		
		return true;
	}
        
        @Override
	//the method to get the usage
	public double getUsage(int daysLookback) {
		double result = 0;
		Iterator<SprinklerDayUsage> iterator = getUsageHistory(daysLookback).iterator();
		
		while (iterator.hasNext()) {
			result += iterator.next().getUsage();
		}
		
		return result;	
	}

	
	@Override
        //the method to get water flow of sprinkler cluster
	public double getWaterFlow() {
		double result = 0;
		
		Iterator<Sprinkler> sprinklerIterator = m_SprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			result += current.getWaterFlow();
		}
		
		return result;
	}
        
	@Override
        //the method to get total usage
	public double getTotalUsage() {
		double result = 0;
		
		Iterator<Sprinkler> sprinklerIterator = m_SprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			result += current.getTotalUsage();
		}
		
		return result;
	}
	
	
	@Override
        //the method to get the id of sprinkler cluster
	public String getId() {
		return m_ClusterId.toString();
	}

	@Override
        //the method to activate the sprinkler cluster
	public void activate() {
		Iterator<Sprinkler> sprinklerIterator = m_SprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			current.activate();
		}
	}

	@Override
        //the method to deactivate the sprinkler cluster
	public void deactivate() {
		Iterator<Sprinkler> sprinklerIterator = m_SprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			current.deactivate();
		}
		
	}
        
        public boolean getStatus(){
		return isON;
	}
	
	public void setStatus(boolean stat){
		for(Sprinkler s: sList){
			if(!stat && s.isActive()) s.deactivate();
		}
		if(stat && !isON){
			isON = true;
		}else if(!stat && isON){
			isON = false;
		}
	}
       
        //the method to iterate at sprinkler list
        public Iterator<Sprinkler> getIterator() {
		return m_SprinklerMap.values().iterator();
	}
	//the method to get sprinkler having id in sprinkler list 
	public Sprinkler getSprinkler(String id) {
		return m_SprinklerMap.containsKey(id) ? m_SprinklerMap.get(id) : null;
	} 
	//the method to add sprinkler into sprinkler list
	public void addSprinkler() {
		int id = m_NextId.get(m_ClusterId);
		Sprinkler sprinkler = new Sprinkler(m_ClusterId.toString() + id);
		m_NextId.put(m_ClusterId, ++id);
		
		m_SprinklerMap.put(sprinkler.getId(), sprinkler);
	}
	//the method to get the count of sprinkler list
	public int getCount() {
		return m_SprinklerMap.size();
	}
	
}
