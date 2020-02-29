package io.cronavirus.com.india.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.cronavirus.com.india.model.LocationStats;
import io.cronavirus.com.india.services.CronaVirusDataService;

/**
 * 
 * @author Fayyam
 *
 */

@Controller
public class HomeController {

	@Autowired
	CronaVirusDataService coronaVirusService;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusService.getAllStats();
		int totalNoOfCases = allStats.stream().mapToInt(stats -> stats.getLatestReportedCases()).sum();
		model.addAttribute("allStats", allStats);
		model.addAttribute("totalNoOfCases", totalNoOfCases);
		return "home";
	}

}
