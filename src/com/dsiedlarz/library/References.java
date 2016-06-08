package com.dsiedlarz.library;

import com.dsiedlarz.library.API.Library;

public class References {
	private static Library library;
	
	public static Library getLibrary(){
		return library;
	}
	
	public static void setLibrary(Library library){
		References.library = library;
		
	}
	

}
