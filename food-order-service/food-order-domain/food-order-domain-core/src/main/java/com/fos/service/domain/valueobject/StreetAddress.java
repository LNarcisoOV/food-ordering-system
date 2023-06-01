package com.fos.service.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class StreetAddress {

	private final UUID id;
	private final String street;
	private final String postalCode;
	private final String city;

	private StreetAddress(UUID id, String street, String postalCode, String city) {
		super();
		this.id = id;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}

	public UUID getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, id, postalCode, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StreetAddress other = (StreetAddress) obj;
		return Objects.equals(city, other.city) && Objects.equals(postalCode, other.postalCode)
				&& Objects.equals(street, other.street);
	}

	@Override
	public String toString() {
		return "StreetAddress [id=" + id + ", street=" + street + ", postalCode=" + postalCode + ", city=" + city + "]";
	}
}
