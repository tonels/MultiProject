package tonels.core;

import lombok.Getter;

@Getter
public class Address {

	private final String street, city, country;

	public Address(String street, String city, String country) {
		this.street = street;
		this.city = city;
		this.country = country;
	}
	public Address getCopy() {
		return new Address(this.street, this.city, this.country);
	}

}
