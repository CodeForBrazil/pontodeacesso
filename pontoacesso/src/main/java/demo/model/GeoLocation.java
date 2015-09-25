package demo.model;

import java.util.List;

public class GeoLocation {

	private List<AddressComponents> address_components;

	private String formatted_address;

	private Geometry geometry;

	private String place_id;

	private String[] types;

	public List<AddressComponents> getAddress_components() {
		return address_components;
	}

	public void setAddress_components(List<AddressComponents> address_components) {
		this.address_components = address_components;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

}
