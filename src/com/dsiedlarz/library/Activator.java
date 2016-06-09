package com.dsiedlarz.library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.dsiedlarz.library.API.Library;
import com.dsiedlarz.library.mock.LibraryM;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private static Library library;
	
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
	
	static Library createLibrary() {
		
	      Library object = null;
	      try {
	    	
	    	  
	    	  BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Dawid\\workspace\\com.dsiedlarz.library\\conf.prefs"));
	  		String className= br.readLine();
	          Class<Library> classDefinition = (Class<Library>) Class.forName(className);
	          object = classDefinition.newInstance();
	      } catch (InstantiationException e) {
	          System.out.println(e);
	      } catch (IllegalAccessException e) {
	          System.out.println(e);
	      } catch (ClassNotFoundException e) {
	          System.out.println(e);
	      } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return object;
	   }

}
