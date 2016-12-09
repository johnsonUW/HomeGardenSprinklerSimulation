/*
 *This class is the class to implement the individual sprinkler 
*class name : Sprinkler
* create file date : 2016/12/3
*author: johnsondl
 */
package system;


import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
public class Sprinkler implements InterfaceSprinkler{
    
    // constants
	// water flow cubic feet per minute (second in real time)
	private static final double WATER_FLOW = MyGlobal.getInstance().getWaterFlow();
	
	// defines data members
	private String m_Id;
	private boolean m_Active;
	private boolean m_Functional;
	
	// default constructors
	public Sprinkler() {
		m_Id = null;
		m_Active = false;
		m_Functional = true;
	}
	
	public Sprinkler(String id) {
		this.m_Id = id;
		m_Active = false;
		m_Functional = true;
	}
	
	//public member methods
        //the method to get if the sprinkler is activated 
	public boolean isActive() {
		return m_Active;
	}
	 //the method to deactivate the sprinkler
	public void deactivate() {
		if (m_Active) {
			m_Active = false;
			SprinklerUpdateTimer.removeSprinkler(this);
		}
	}
	
        //the method to activate the sprinkler
	public void activate() {
		if (!m_Active) {
			m_Active = true;
			SprinklerUpdateTimer.addSprinkler(this);
		}
	}
	
	// get functionality
	public boolean isFunctional() {
		return m_Functional;
	}
	//the method to get the functional of sprinkler
	public void setFunctional(boolean functional) {
		this.m_Functional = functional;
	}
	//the method to get the id of sprinkler
	public String getId() {
		return m_Id;
	}
	public void setStartSchedule(){
            activate();
        }
        public void setEndSchedule(){
            deactivate();
        }
	// the method to get the usage from serialized file
	public double getTotalUsage() {
		return SprinklerUsage.getTotalUsage(m_Id);
	}
        
	//the method to get the history list of usage
	public LinkedList<SprinklerDayUsage> getUsageHistory(int daysLookback) {
		LinkedList<SprinklerDayUsage> list = SprinklerUsage.getSprinklerUsage(m_Id, daysLookback);
		LinkedList<SprinklerDayUsage> result = new LinkedList<SprinklerDayUsage>();
		int listIndex = 0;
		for (int i = 0; i < daysLookback; i++) {
			LocalDate date = GardenSystem.getInstance().getDate().minusDays(i);
			if (listIndex < list.size() && date.isEqual(list.get(listIndex).getDay())) {
				result.add(list.get(listIndex));
				listIndex++;
			}
			else {
				result.add(new SprinklerDayUsage(date, 0));
			}
		}
		
		return result;
	}
	//the method to get usage of sprinkler
	public double getUsage(int daysLookback) {
		double result = 0;
		Iterator<SprinklerDayUsage> iterator = getUsageHistory(daysLookback).iterator();
		
		while (iterator.hasNext()) {
			result += iterator.next().getUsage();
		}
		
		return result;	
	}
	
	// the method to get sprinkler water flow
	public double getWaterFlow() {
		return WATER_FLOW;
	} 
    
}
