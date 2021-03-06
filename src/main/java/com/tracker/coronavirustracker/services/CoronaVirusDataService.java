package com.tracker.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tracker.coronavirustracker.model.LocationStats;

@Service
public class CoronaVirusDataService {
	public static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	
	public List<LocationStats> allCases = new ArrayList<LocationStats>();
	@PostConstruct
	@Scheduled(cron="59 * * * * *")
	public List<LocationStats> fetchCovidData() throws IOException, InterruptedException 
	{
	    List<LocationStats> newCases = new ArrayList<LocationStats>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create(DATA_URL))
							.build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader in = new StringReader(httpResponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
		    LocationStats stats = new LocationStats();
		    stats.setState(record.get("Province_State"));
		    stats.setCountry("Country_Region");
		    stats.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
		    
		    newCases.add(stats);
		}
		
		this.allCases = newCases;
		return allCases;
	}
	
}
