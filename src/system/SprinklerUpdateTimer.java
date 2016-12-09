/*
 *This class is the class to update sprinkler timer
*class name : SprinklerUpdateTimer
* create file date : 2016/12/3
*author: johnsondl
 */
package system;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
public class SprinklerUpdateTimer {
	//class member variables
        private static final int UPDATE_MULTIPLY_VARIABLE=MyGlobal.getInstance().getUpdateMultiply();
	private static final int UPDATE_INTERVAL =MyGlobal.getInstance().getUpdateInterval();
	private static HashMap<Sprinkler, Date> m_ActiveSprinklers = null;
	private static Timer m_Timer = null;
	
	private static void initialize() {
		if (m_ActiveSprinklers == null) {
			m_ActiveSprinklers = new HashMap<Sprinkler, Date>();
		}
	}
	
	//the method to start the timer
	private static void start() {
		m_Timer = new Timer();
		m_Timer.schedule(new TimerTask() {
			public void run() {
				Iterator<Sprinkler> iterator = m_ActiveSprinklers.keySet().iterator();
				while (iterator.hasNext()) {
					Sprinkler sprinkler = iterator.next();
					Date lastUpdate = m_ActiveSprinklers.get(sprinkler);
					Date now = new Date();
					double elapsedSeconds = MyGlobal.getInstance().getElapsedSeconds(now, lastUpdate);
					SprinklerUsage.update(sprinkler.getId(), sprinkler.getWaterFlow() * elapsedSeconds);
					m_ActiveSprinklers.put(sprinkler, now);
				}
			}
		}, UPDATE_INTERVAL * UPDATE_MULTIPLY_VARIABLE, UPDATE_INTERVAL * UPDATE_MULTIPLY_VARIABLE);
	}	
	//the method to stop the timer
	private static void stop() {
		m_Timer.cancel();
	}
	//the method to add sprinkler
	public static void addSprinkler(Sprinkler sprinkler) {
		initialize();
		m_ActiveSprinklers.put(sprinkler, new Date());
		if (m_ActiveSprinklers.size() == 1) {
			start();
		}
	}
	//the method to remove sprinkler
	public static void removeSprinkler(Sprinkler sprinkler) {
		initialize();
		long m_StopTime = (new Date()).getTime();
		long m_LastUpdate = m_ActiveSprinklers.containsKey(sprinkler) ?
				m_ActiveSprinklers.get(sprinkler).getTime() : m_StopTime;
		double time = (m_StopTime - m_LastUpdate) / UPDATE_MULTIPLY_VARIABLE;
		SprinklerUsage.update(sprinkler.getId(), sprinkler.getWaterFlow() * time);
		m_ActiveSprinklers.remove(sprinkler);
		if (m_ActiveSprinklers.isEmpty()) {
			stop();
		}
	} 
}
