/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataenums;
public enum SprinklerStatus {
    OK("OK"),
	NOTOK("NOTOK"),
	ON("ON"),
	NOTON("NOTON");
	
	private String curStatus;
	
	private SprinklerStatus(String str){
		this.curStatus = str;
	}
	
	@Override
	public String toString(){
		return curStatus;
	} 
}
