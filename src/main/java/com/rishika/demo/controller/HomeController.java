package com.rishika.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rishika.demo.models.LocationStats;
import com.rishika.demo.services.CoronaVirusDataService;

@Controller
public class HomeController {
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStates();
		int totalReportedCases =allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases =allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases",  totalNewCases);
		//System.out.println(totalNewCases+" ...... ");
		
		
		return "home";
	}

}
