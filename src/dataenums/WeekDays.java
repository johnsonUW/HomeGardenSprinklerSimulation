/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataenums;
public enum WeekDays {
        SUNDAY("Sunday"),
	MONDAY("Monday"),
	TUESDAY("Tuesday"),
	WEDNESDAY("Wednesday"),
	THURSDAY("Thursday"),
	FRIDAY("Friday"),
	SATURDAY("Saturday");	
	
	private String dayString;
	
	private WeekDays(String str){
		this.dayString = str;
	}
	
	@Override
	public String toString(){
		return dayString;
	} 
    
}
