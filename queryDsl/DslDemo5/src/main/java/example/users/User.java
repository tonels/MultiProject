/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.users;

import lombok.Data;
import lombok.Value;

import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author Christoph Strobl
 * @author Oliver Gierke
 */
@Data
@Accessors(chain = true)
@Document
public class User {

	private @Id String username;
	private String firstname, lastname, email, nationality;
	private @JsonIgnore String password;

	private @JsonUnwrapped Address address;
	private Picture picture;

	@Value
	public static class Address {
		String city, street, zip;
	}

	@Value
	public static class Picture {
		String large, medium, small;
	}
}
