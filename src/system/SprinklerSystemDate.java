/*
 *This class is the class to implement  sprinkler system date
*class name : SprinklerSystemDate
* create file date : 2016/12/3
*author: johnsondl
 */
package system;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask; 
public class SprinklerSystemDate {
    private LocalDateTime m_Date;
//constructors
    public SprinklerSystemDate() {
            m_Date = LocalDateTime.now();
            start();
    }
    //the method to set minus day of a week
     public void minusDays(int days) {
            m_Date = m_Date.minusDays(days);
    }
     //the method to get local time
    public LocalTime getTime() {
            return m_Date.toLocalTime();
    }
    //the method to get date
    public LocalDate getDate() {
            return m_Date.toLocalDate();
    }
    //the method to add days to a week
    public void addDays(int days) {
            m_Date = m_Date.plusDays(days);
    }
    //the method to start
    private void start() {
            m_Date = LocalDateTime.now();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                    public void run() {
                            m_Date = m_Date.plusMinutes(1);
                    }
            }, 0, 1000);
    } 
}
