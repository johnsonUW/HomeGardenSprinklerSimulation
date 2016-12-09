/*
 *This class is the class to implement the sprinkler formatter
*class name : SprinklerFormatter
* create file date : 2016/12/3
*author: johnsondl
 */
package utils;
import java.time.LocalDate;
public class SprinklerFormatter {
    public static String dateLabelFormatter(LocalDate date) {
		return "System Date: " + date;
	}
	
	public static String minThresholdFormatter(double threshold) {
		return "Min Threshold: " + threshold + " ºF";
	}
	
	public static String maxThresholdFormatter(double threshold) {
		return "Max Threshold: " + threshold + " ºF";
	}
	
	public static String getTemperatureFormatter(double degrees) {
		return "Temperature: " + degrees + " ºF";
	}
}
