/*
 *This class is the class to implement sprinkler usage
*class name : SprinklerUsage
* create file date : 2016/12/3
*author: johnsondl
 */
package system;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList; 
import userinterface.MainUserInterface.UserInterface;
public class SprinklerUsage {
	// total usages serialized file path
	private static final String TOTAL_USAGES_FILE_PATH = "total_usages.ser";
	// sprinkler usages serialized file path
	private static final String SPRINKLER_USAGES_FILE_PATH = "sprinkler_usages.ser";
	private static HashMap<String, Double> m_TotalUsages = null;
	private static HashMap<String, LinkedList<SprinklerDayUsage>> m_SprinklerUsages = null;
	//the method to update the stored usages and GUI
	public static void update(String m_SprinklerId, double m_Usage) {
		// retrieve usages map if the system is starting up
		if (m_TotalUsages == null) {
			m_TotalUsages = readTotalUsages();
		}
		if (m_SprinklerUsages == null) {
			m_SprinklerUsages = readSprinklerUsages();
		}
		Double oldUsage = (m_TotalUsages.containsKey(m_SprinklerId)) ? m_TotalUsages.get(m_SprinklerId) : 0.0;
		m_TotalUsages.put(m_SprinklerId, oldUsage.doubleValue() + m_Usage);
		// update the value for the sprinkler
		LinkedList<SprinklerDayUsage> sprinklerUsage = m_SprinklerUsages.containsKey(m_SprinklerId) ?
				m_SprinklerUsages.get(m_SprinklerId) : new LinkedList<SprinklerDayUsage>();
				
		SprinklerDayUsage m_LastUpdate;
		if (sprinklerUsage.isEmpty()) {
			m_LastUpdate = new SprinklerDayUsage(LocalDate.now(), m_Usage);
			sprinklerUsage.addFirst(m_LastUpdate);
		}
		else {
			m_LastUpdate = sprinklerUsage.getFirst();
			// if the day of last update isn't today
			if (!m_LastUpdate.getDay().equals(GardenSystem.getInstance().getDate())) {
				SprinklerDayUsage dayUsage = new SprinklerDayUsage(GardenSystem.getInstance().getDate(), m_Usage);
				sprinklerUsage.addFirst(dayUsage);
			}
			else {
				m_LastUpdate.addUsage(m_Usage);
			}
		}
		m_SprinklerUsages.put(m_SprinklerId, sprinklerUsage);
		// write usages map
		writeTotalUsage(m_TotalUsages);
		writeSprinklerUsage(m_SprinklerUsages);
		// update display
		UserInterface.getInstance().update();
	}
	//the method to get total usages
	public static double getTotalUsage(String sprinklerId) {
		// retrieve usages map if the system is starting up
		if (m_TotalUsages == null) {
			m_TotalUsages = readTotalUsages();
		}
		
		return m_TotalUsages.containsKey(sprinklerId) ? m_TotalUsages.get(sprinklerId) : 0;
	}
	
	//method to get the linked list of 
	public static LinkedList<SprinklerDayUsage> getSprinklerUsage(String sprinklerId, int daysLookback) {
		// retrieve usages map if the system is starting up
		if (m_TotalUsages == null) {
			m_TotalUsages = readTotalUsages();
		}
		if (m_SprinklerUsages == null) {
			m_SprinklerUsages = readSprinklerUsages();
		}
		
		LocalDate lookBack = GardenSystem.getInstance().getDate();
		LinkedList<SprinklerDayUsage> result = new LinkedList<SprinklerDayUsage>();
		LinkedList<SprinklerDayUsage> sprinklerUsage = m_SprinklerUsages.containsKey(sprinklerId) ?
				m_SprinklerUsages.get(sprinklerId) : new LinkedList<SprinklerDayUsage>();
				
		int sprinklerUsageIndex = 0;
		while (daysLookback >= 0 && sprinklerUsageIndex < sprinklerUsage.size()) {
			SprinklerDayUsage dayUsage = sprinklerUsage.get(sprinklerUsageIndex);

			if (lookBack.isEqual(dayUsage.getDay())) {
				result.add(dayUsage);
				sprinklerUsageIndex++;
			}
			
			daysLookback--;
			lookBack = lookBack.minusDays(1);
		}
		
		return result;
	}
	//the method to read total usages
	@SuppressWarnings("unchecked")
	public static HashMap<String, Double> readTotalUsages() {
		HashMap<String, Double> map;
		File usagesFile = new File(TOTAL_USAGES_FILE_PATH);
		if (usagesFile.exists()) {
			try {
				FileInputStream fis = new FileInputStream(TOTAL_USAGES_FILE_PATH);
				ObjectInputStream ois = new ObjectInputStream(fis);
				map = (HashMap<String, Double>) ois.readObject();
				ois.close();
			} catch (IOException e) {
				map = new HashMap<String, Double>();
				e.printStackTrace();
			} catch (Exception e) {
				map = new HashMap<String, Double>();
				e.printStackTrace();
			}
		}
		else {
			map = new HashMap<String, Double>();
		}
		
		return map;
	}
	
	//the method to read sprinkler usages
	@SuppressWarnings("unchecked")
	public static HashMap<String, LinkedList<SprinklerDayUsage>> readSprinklerUsages() {
		HashMap<String, LinkedList<SprinklerDayUsage>> map;
		File usagesFile = new File(SPRINKLER_USAGES_FILE_PATH);
		if (usagesFile.exists()) {
			try {
				FileInputStream fis = new FileInputStream(SPRINKLER_USAGES_FILE_PATH);
				ObjectInputStream ois = new ObjectInputStream(fis);
				map = (HashMap<String, LinkedList<SprinklerDayUsage>>) ois.readObject();
				ois.close();
			} catch (IOException e) {
				// what to do on IOException? display a message?
				map = new HashMap<String, LinkedList<SprinklerDayUsage>>();
				e.printStackTrace();
			} catch (Exception e) {
				map = new HashMap<String, LinkedList<SprinklerDayUsage>>();
				e.printStackTrace();
			}
		}
		else {
			map = new HashMap<String, LinkedList<SprinklerDayUsage>>();
		}
		
		return map;
	}
	
	//the method to write total usage
	private static void writeTotalUsage(HashMap<String, Double> map) {
		try {
			FileOutputStream fos = new FileOutputStream(TOTAL_USAGES_FILE_PATH);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(map);
	        oos.close();
		} catch (IOException e) {
			// what to do on IOException? display a message?
			e.printStackTrace();
		}
	}
	//the method to reset usages
	public static void resetUsages() {
		writeTotalUsage(new HashMap<String, Double>());
		writeSprinklerUsage(new HashMap<String, LinkedList<SprinklerDayUsage>>());
	}
	//the method to write sprinkler usage
	private static void writeSprinklerUsage(HashMap<String, LinkedList<SprinklerDayUsage>> map) {
		try {
			FileOutputStream fos = new FileOutputStream(SPRINKLER_USAGES_FILE_PATH);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(map);
	        oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
