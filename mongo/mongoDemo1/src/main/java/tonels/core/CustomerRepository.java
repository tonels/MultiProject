package tonels.core;

import org.springframework.data.repository.Repository;

import java.math.BigInteger;

public interface CustomerRepository extends Repository<Customer, BigInteger> {

//	Customer findOne(BigInteger id);

	Customer save(Customer customer);

	Customer findByEmailAddress(EmailAddress emailAddress);
}
