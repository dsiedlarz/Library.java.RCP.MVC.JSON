package com.dsiedlarz.library;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.dsiedlarz.library.API.Book;
import com.dsiedlarz.library.API.Library;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private static Library library;
	private static BufferedReader br;
	
	public static Library getLibrary(){
		return library;
	}
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		
		library = createLibrary();
		References.setLibrary(library);
		
		
		
 
		System.out.println("Siema, zaczynamy");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	@SuppressWarnings("unchecked")
	static Library createLibrary() {
		
	      Library object = null;
	      try {
	    	
	    	  
	    	  br = new BufferedReader(new FileReader("C:\\Users\\Dawid\\workspace\\com.dsiedlarz.library\\prefs.conf"));
	  		String className= br.readLine();
	          Class<Library> classDefinition = (Class<Library>) Class.forName(className);
	          
	          className= br.readLine();
	          References.setBookClass((Class<Book>) Class.forName(className));
	        
	          References.setStaxFile(br.readLine());
	          
	          object = classDefinition.newInstance();
	      } catch (InstantiationException e) {
	    	  System.out.println("instantion");
	          System.out.println(e);
	      } catch (IllegalAccessException e) {
	    	  System.out.println("illegal acces");
	          System.out.println(e);
	      } catch (ClassNotFoundException e) {
	    	  References.setErrorMessage("Klasa z prefs.conf nie zosta³a znaleziona");
	    	  return null;
	          
	      } catch (FileNotFoundException e) {
	    	  References.setErrorMessage(" prefs.conf nie zosta³ znaleziony");
	    	  return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return object;
	   }

}
