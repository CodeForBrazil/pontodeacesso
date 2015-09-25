package demo.model;

import java.util.List;

public class ResultadoGeolocation {

	private List<GeoLocation> results;

	private String status;

	public List<GeoLocation> getResults() {
		return results;
	}

	public void setResults(List<GeoLocation> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
