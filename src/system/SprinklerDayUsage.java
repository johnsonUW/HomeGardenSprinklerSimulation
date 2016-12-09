/*
 *This class is the class to implement  sprinkler day usage 
*class name : SprinklerDayUsage
* create file date : 2016/12/3
*author: johnsondl
 */
package system;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class SprinklerDayUsage implements Serializable{
    //sprinkler data member variables
    private LocalDate m_Day;
    private double m_Usage; 
    //constructors
    public SprinklerDayUsage() {
		m_Day = null;
		m_Usage = 0;
	}
	
    public SprinklerDayUsage(LocalDate day, double usage) {
            this.m_Day = day;
            this.m_Usage = usage;
    }
    
    public SprinklerDayUsage(LocalDate day) {
            this.m_Day = day;
            m_Usage = 0;
    }
     //the method to get usage
    public double getUsage() {
            return m_Usage;
    }
    //the method to get dayof a week
    public LocalDate getDay() {
            return m_Day;
    } 
    //the method to add usage
    public void addUsage(double usage) {
            this.m_Usage += usage;
    }
}
