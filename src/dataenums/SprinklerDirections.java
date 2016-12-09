/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataenums;
public enum SprinklerDirections{
    
   
	NORTH("North"),
	EAST("East"),
	SOUTH("South"),
	WEST("West");
	
	private String direction;
	
	private SprinklerDirections(String direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return direction;
	}
    
}
