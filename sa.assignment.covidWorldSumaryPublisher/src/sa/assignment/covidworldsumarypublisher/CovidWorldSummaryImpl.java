package sa.assignment.covidworldsumarypublisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.io.*;

import org.json.JSONException;
import org.json.JSONObject;


public class CovidWorldSummaryImpl implements CovidWorldSummary{

	@Override
	public String publishService() {
		JSONObject object = null;
		try {
			object = readJsonFromUrl("https://covid19.mathdro.id/api");
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object.toString();
	}

	@Override
	public void printWorldSummary() {
		JSONObject json = new JSONObject(publishService());
		JSONObject confirmed = json.getJSONObject("confirmed");
		JSONObject recovered = json.getJSONObject("recovered");
		JSONObject deaths = json.getJSONObject("deaths");
		  
		String lastUpdate = json.getString("lastUpdate");
		int valueConfirmed =  confirmed.getInt("value");
	    int valueRecovered =  recovered.getInt("value");
	    int valueDeath =  deaths.getInt("value");
		
	    String leftAlignFormat = "| %-15s |%n";

	    System.out.format("+-----------------+-----------------+%n");
	    System.out.format("| Covid summary   |      	    |%n");
	    System.out.format("+-----------------+-----------------+%n%n");
	 
	    System.out.format(leftAlignFormat, "Confirmed	  |" + " "+ valueConfirmed);
	    System.out.format(leftAlignFormat, "Recovered	  |" + " "+ valueRecovered);
	    System.out.format(leftAlignFormat, "Deaths	  |" + " "+ valueDeath);
	    System.out.format(leftAlignFormat, "Last Update |" + " "+ lastUpdate.substring(0, 10));
	    System.out.format("+-----------------+-----------------+%n");
		
	}
	
	private static String readAll(Reader reader) throws IOException {
	    StringBuilder stringBuilder = new StringBuilder();
	    int cp;
	    while ((cp = reader.read()) != -1) {
	    	stringBuilder.append((char) cp);
	    }
	    return stringBuilder.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String text = readAll(bufferedReader);
	      JSONObject json = new JSONObject(text);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
}
