package querydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomersEntity is a Querydsl query type for CustomersEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomersEntity extends EntityPathBase<CustomersEntity> {

    private static final long serialVersionUID = 1700092550L;

    public static final QCustomersEntity customersEntity = new QCustomersEntity("customersEntity");

    public final StringPath addressLine1 = createString("addressLine1");

    public final StringPath addressLine2 = createString("addressLine2");

    public final StringPath city = createString("city");

    public final StringPath contactFirstName = createString("contactFirstName");

    public final StringPath contactLastName = createString("contactLastName");

    public final StringPath country = createString("country");

    public final NumberPath<java.math.BigDecimal> creditLimit = createNumber("creditLimit", java.math.BigDecimal.class);

    public final StringPath customerName = createString("customerName");

    public final NumberPath<Integer> customerNumber = createNumber("customerNumber", Integer.class);

    public final StringPath phone = createString("phone");

    public final StringPath postalCode = createString("postalCode");

    public final NumberPath<Integer> salesRepEmployeeNumber = createNumber("salesRepEmployeeNumber", Integer.class);

    public final StringPath state = createString("state");

    public QCustomersEntity(String variable) {
        super(CustomersEntity.class, forVariable(variable));
    }

    public QCustomersEntity(Path<? extends CustomersEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomersEntity(PathMetadata metadata) {
        super(CustomersEntity.class, metadata);
    }

}

