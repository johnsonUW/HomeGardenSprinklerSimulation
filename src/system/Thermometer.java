/*
 *This class is the class to implement thermometer
*class name : Thermometer
* create file date : 2016/12/3
*author: johnsondl
 */
package system;
public class Thermometer {
    //class member variables
    private double m_DegreesFarenheit;
    private double m_MinThreshold;
    private double m_MaxThreshold;
    private final int INCREMENTOR = 10;
    private final int MIN_TEMP = 0;
    private final int MAX_TEMP = 125;
    
     private static Thermometer mThermometer = null;
    //implement the singleton pattern
    public static Thermometer getInstance() {        
        if (mThermometer == null){
                mThermometer = new Thermometer();
        }
        return mThermometer;
    }
    //constructor
    public Thermometer() {
            this.m_DegreesFarenheit = 60.0;
            this.m_MinThreshold = 50.0;
            this.m_MaxThreshold = 90.0;
    }	
    public Thermometer(int minThreshold, int maxThreshold){
            this.m_DegreesFarenheit = 60.0;
            this.m_MinThreshold = minThreshold;
            this.m_MaxThreshold = maxThreshold;
    }	
    //the method to increment the temperature
    public boolean incrementTemperature(){
            if(m_DegreesFarenheit+INCREMENTOR<=MAX_TEMP){
                    m_DegreesFarenheit += INCREMENTOR;
                    return true;
            }
            return false;
    }
    //the method to decrement the temperature
    public boolean decrementTemperature(){
            if(m_DegreesFarenheit-INCREMENTOR>=MIN_TEMP){
                    m_DegreesFarenheit -= INCREMENTOR;
                    return true;
            }
            return false;
    }
    //the method to turn on the sprinkler system
    public boolean shouldTurnOn(){
            return m_DegreesFarenheit>=m_MaxThreshold;
    }
    //the method to turn off the sprinkler off
    public boolean shouldTurnOff(){
            return m_DegreesFarenheit<=m_MinThreshold;
    }
    //the method to covert into string
    @Override
    public String toString(){
            StringBuilder b = new StringBuilder();
            b.append("Temperature: " + m_DegreesFarenheit);
            b.append(", minThreshold: " + m_MinThreshold);
            b.append(", maxThreshold: " + m_MaxThreshold);
            b.append(", isOn: " + shouldTurnOn());
            b.append(", isOff: " + shouldTurnOff());
            return b.toString();
    }

    //Setters and getters
    public boolean setTemperature(double degreesF){
            this.m_DegreesFarenheit = degreesF;
            return true;
    }
     //the method to set max threshold
    public boolean setMaxThreshold(double m_DegreesF){
            if(m_DegreesF<MIN_TEMP || m_DegreesF>MAX_TEMP){
                    return false;
            }
            this.m_MaxThreshold = m_DegreesF;
            return true;
    }
     //the method to set min threshold
    public boolean setMinThreshold(double m_DegreesF){
            if(m_DegreesF<MIN_TEMP || m_DegreesF>MAX_TEMP){
                    return false;
            }
            this.m_MinThreshold = m_DegreesF;
            return true;
    }
     //the method to get min threshrold
    public double getMinThreshold(){
            return this.m_MinThreshold;
    }
      //the method to get max threshrold
    public double getMaxThreshold(){
            return this.m_MaxThreshold;
    } 
    //the method to get temperature
    public double getTemperature(){
            return this.m_DegreesFarenheit;
    }
}
