package com.dsiedlarz.library;

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
		library = new LibraryM();
		
 
		System.out.println("Siema, zaczynamy");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
