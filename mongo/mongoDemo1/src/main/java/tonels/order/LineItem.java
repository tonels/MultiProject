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
package tonels.order;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.Assert;
import tonels.core.AbstractDocument;
import tonels.core.Product;

public class LineItem extends AbstractDocument {

	@DBRef
	private Product product;
	private BigDecimal price;
	private int amount;

	public LineItem(Product product) {
		this(product, 1);
	}


	public LineItem(Product product, int amount) {

		Assert.notNull(product, "The given Product must not be null!");
		Assert.isTrue(amount > 0, "The amount of Products to be bought must be greater than 0!");

		this.product = product;
		this.amount = amount;
		this.price = product.getPrice();
	}

	public LineItem() {

	}

	public Product getProduct() {
		return product;
	}

	public int getAmount() {
		return amount;
	}

	public BigDecimal getUnitPrice() {
		return price;
	}

	public BigDecimal getTotal() {
		return price.multiply(BigDecimal.valueOf(amount));
	}
}
