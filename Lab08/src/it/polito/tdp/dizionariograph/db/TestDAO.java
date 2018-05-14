package it.polito.tdp.dizionariograph.db;

import java.util.Set;

public class TestDAO {
	
	public static void main(String[] args) {
		
		WordDAO wd = new WordDAO();
		Set<String> set = wd.getVicini("cara");
		
		for(String s: set)
			System.out.println(s);
		
		//System.out.println(wd.getAllWordsFixedLength(4));
	}

}
