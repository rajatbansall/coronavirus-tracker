package com.tracker.coronavirustracker.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tracker.coronavirustracker.model.LocationStats;
import com.tracker.coronavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {

		@RequestMapping("/")
		public String home(Model theModel) throws IOException, InterruptedException 
		{
			CoronaVirusDataService cvds = new CoronaVirusDataService();
			List<LocationStats> coronaStats = cvds.fetchCovidData();
			theModel.addAttribute("coronaStats",coronaStats);
			theModel.addAttribute("welcomeText", "Welcome To Corona Virus Tracker");
			
			return "home";
		}
}

