package me.xtrm.battlehax.utils;

import java.util.ArrayList;

public class Modes {
	
	public static ArrayList<String> build(String... str){
		ArrayList<String> finxl = new ArrayList<String>();
		
		for(String s : str) {
			finxl.add(s);
		}
		
		return finxl;
	}

}
