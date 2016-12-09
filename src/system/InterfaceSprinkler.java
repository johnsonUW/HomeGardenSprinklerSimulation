/*
 *This class is the interface to implement the interface of Sprinkler
*interface name : InterfaceSprinkler
* create file date : 2016/12/3
*author: johnsondl
 */
package system;

import java.util.LinkedList;
public interface InterfaceSprinkler {
    public boolean isActive();
	public boolean isFunctional();
	public double getWaterFlow();
	public double getTotalUsage();
	public double getUsage(int dayLookback);
	public LinkedList<SprinklerDayUsage> getUsageHistory(int dayLookback);
	public String getId();
	public void activate();
	public void deactivate(); 
}
