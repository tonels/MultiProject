/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tonels.core;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;
import tonels.core.AbstractDocument;
import tonels.core.Address;
import tonels.core.EmailAddress;

/**
 * A customer.
 * 
 * @author Oliver Gierke
 */
@Document
public class Customer extends AbstractDocument {

	private String firstname, lastname;

	@Field("email")
	@Indexed(unique = true)
	private EmailAddress emailAddress;

	private Set<Address> addresses = new HashSet<Address>();

	public Customer(String firstname, String lastname) {

		Assert.hasText(firstname);
		Assert.hasText(lastname);

		this.firstname = firstname;
		this.lastname = lastname;
	}
	protected Customer() {
	}

	public void add(Address address) {

		Assert.notNull(address);
		this.addresses.add(address);
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<Address> getAddresses() {
		return Collections.unmodifiableSet(addresses);
	}
}
