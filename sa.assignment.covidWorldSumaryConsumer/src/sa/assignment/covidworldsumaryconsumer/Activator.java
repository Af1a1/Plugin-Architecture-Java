package sa.assignment.covidworldsumaryconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


import sa.assignment.covidworldsumarypublisher.CovidWorldSummary;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Consumer World Summary");
		serviceReference = context.getServiceReference(CovidWorldSummary.class.getName());
		CovidWorldSummary covidWorldSummary = (CovidWorldSummary) context.getService(serviceReference);
		System.out.println("Print summary enter: 1");
		System.out.println("Get http request string enter: 2");
		
		Scanner in = new java.util.Scanner(System.in);
		int value = in.nextInt();
		
		if(value == 1) {
			covidWorldSummary.printWorldSummary();	
		}
		else if(value == 2) {
			System.out.println(covidWorldSummary.publishService());
		}
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Consumer World Summary");
		context.ungetService(serviceReference);
	}

}
