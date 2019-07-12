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

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.math.BigInteger;

@Repository
@Profile("mongodb")
class MongoDbCustomerRepository implements CustomerRepository {

	private final MongoOperations operations;

	@Autowired
	public MongoDbCustomerRepository(MongoOperations operations) {
		Assert.notNull(operations);
		this.operations = operations;
	}

//	@Override
//	public Customer findOne(BigInteger id) {
//		Query query = query(Criteria.where("id").is(id));
//		Customer one = operations.findOne(query, Customer.class);
////		Customer one = operations.findOne(new Query(Criteria.where( "id" ).is(id)), Customer.class);
//		return one;
//	}

	@Override
	public Customer save(Customer customer) {

		operations.save(customer);
		return customer;
	}

	@Override
	public Customer findByEmailAddress(EmailAddress emailAddress) {

		Query query = query(where("emailAddress").is(emailAddress));
		return operations.findOne(query, Customer.class);
	}
}
