package sa.assignment.covidworldsumarypublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration serviceRegistration;
	public void start(BundleContext context) throws Exception {
		System.out.println("CovidWorldSummary is started");
		CovidWorldSummary covidWorldSummary = new CovidWorldSummaryImpl();
		serviceRegistration = context.registerService(CovidWorldSummary.class.getName(), covidWorldSummary, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("CovidWorldSummary is stopped");
		serviceRegistration.unregister();
	}

}
