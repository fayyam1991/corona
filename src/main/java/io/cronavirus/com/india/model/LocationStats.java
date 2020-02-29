package io.cronavirus.com.india.model;

/**
 * 
 * @author Fayyam
 *
 */
public class LocationStats {

	private String state;
	private String country;
	private int latestReportedCases;
	private int diffFromPrevDay;

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

	/**
	 * @return the latestReportedCases
	 */
	public int getLatestReportedCases() {
		return latestReportedCases;
	}

	/**
	 * @param latestReportedCases the latestReportedCases to set
	 */
	public void setLatestReportedCases(int latestReportedCases) {
		this.latestReportedCases = latestReportedCases;
	}

	/**
	 * @return the diffFromPrevDay
	 */
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}

	/**
	 * @param diffFromPrevDay the diffFromPrevDay to set
	 */
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}

}
