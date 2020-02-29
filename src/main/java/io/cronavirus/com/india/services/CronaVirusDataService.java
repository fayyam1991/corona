package io.cronavirus.com.india.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
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

import io.cronavirus.com.india.model.LocationStats;

/**
 * Corona Virus Service
 * 
 * @author Fayyam
 *
 */

@Service
public class CronaVirusDataService {

	private static String CORONA_VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

	private List<LocationStats> allStats = new ArrayList<>();

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	private void fetchCoronaViruisData() throws IOException, InterruptedException, URISyntaxException {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(CORONA_VIRUS_URL)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		List<LocationStats> tempStats = new ArrayList<>();

		StringReader csvBodyReader = new StringReader(response.body());

		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {

			LocationStats locationStats = new LocationStats();
			locationStats.setState(record.get("Province/State"));
			locationStats.setCountry(record.get("Country/Region"));
			Integer latestReportedCases = Integer.valueOf(record.get(record.size() - 1));
			Integer prevDayReportedCases = Integer.valueOf(record.get(record.size() - 2));
			locationStats.setLatestReportedCases(latestReportedCases);
			locationStats.setDiffFromPrevDay(latestReportedCases - prevDayReportedCases);
			tempStats.add(locationStats);

		}

		this.allStats = tempStats;
	}

	/**
	 * @return the allStats
	 */
	public List<LocationStats> getAllStats() {
		return allStats;
	}

}
