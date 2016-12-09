/*
 *This class is the class to implement  sprinkler schedule
*class name : SprinklerSchedule
* create file date : 2016/12/3
*author: johnsondl
 */
package system;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import dataenums.WeekDays;
import org.joda.time.Interval;
public class SprinklerSchedule {
    //class member variables
    private Map<WeekDays, List<Interval>> m_Schedule;
    //constructor
    public SprinklerSchedule(){
            initializeSchedule();
    }
    //the method to get schedule of day in a week
    public List<Interval> getScheduleForDay(WeekDays day){
            return m_Schedule.get(day);
    }
    //the method to get map of the 7 day schedule.
    public Map<WeekDays, List<Interval>> getSchedule(){
            return m_Schedule;
    }

    //the method to set schedule for day in a week.
    public void setSchedule(Map<WeekDays, List<Interval>> newSchedule){
            for(Entry<WeekDays, List<Interval>> entry: newSchedule.entrySet()){
                    m_Schedule.put(entry.getKey(), mergeIntervals(entry.getValue()));
            }
    }
    //Clears the entire map and reinitializes it
    public void removeAll(){
            m_Schedule.clear();
            initializeSchedule();
    }

    //the method to remmove day from week days
    public void removeDay(WeekDays day){
            m_Schedule.remove(day);
            m_Schedule.put(day, new ArrayList<Interval>());
    }
    
    //the method to merges the interval
    public List<Interval> mergeIntervals(List<Interval> intervals){
            List<Interval> result = new ArrayList<Interval>();

            if(intervals==null || intervals.size()==0){
                    return result;
            }

            Collections.sort(intervals, new IntervalComparator());
            Interval prev = intervals.get(0);
            for(int i=0; i<intervals.size()-1; ++i){
                    Interval curr = intervals.get(i);
                    if(curr.getStartMillis()>prev.getEndMillis()){
                            result.add(prev);
                            prev = curr;
                    }else{
                            Interval merged = new Interval(prev.getStartMillis(), Math.max(prev.getEndMillis(), curr.getEndMillis()));
                            prev = merged;
                    }

            }
            result.add(prev);
            return result;
    }
    @Override
    //the method to convert to string
    public String toString(){
            StringBuilder b = new StringBuilder();
            for(Entry<WeekDays, List<Interval>> entry: m_Schedule.entrySet()){
                    b.append(entry.getKey());
                    for(Interval interval: entry.getValue()){
                            b.append(" " + interval.toString());
                    }
                    b.append("\n");
            }
            return b.toString();
    }

    //the method to initialize the every days of a week
    private void initializeSchedule(){
            m_Schedule = new HashMap<WeekDays, List<Interval>>();
            m_Schedule.put(WeekDays.SUNDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.MONDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.TUESDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.WEDNESDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.THURSDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.FRIDAY, new ArrayList<Interval>());
            m_Schedule.put(WeekDays.SATURDAY, new ArrayList<Interval>());
    }
}

/*
* This class is the  comparator class for interavel
*/
class IntervalComparator implements Comparator<Interval>{
	@Override
	public int compare(Interval ic1, Interval ic2) {
		return (int) (ic1.getStartMillis() - ic2.getStartMillis());
	} 
}
