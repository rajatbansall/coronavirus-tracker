package com.tracker.coronavirustracker.model;

public class LocationStats {

		private String state;
		private String country;
		private int latestTotalCases;
		
		public LocationStats() {};
		public LocationStats(String state, String country, int latestTotalCases) {
			super();
			this.state = state;
			this.country = country;
			this.latestTotalCases = latestTotalCases;
		}
		
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public int getLatestTotalCases() {
			return latestTotalCases;
		}
		public void setLatestTotalCases(int latestTotalCases) {
			this.latestTotalCases = latestTotalCases;
		}
		
}
