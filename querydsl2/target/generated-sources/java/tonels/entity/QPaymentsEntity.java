package tonels.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentsEntity is a Querydsl query type for PaymentsEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPaymentsEntity extends EntityPathBase<PaymentsEntity> {

    private static final long serialVersionUID = -1028734650L;

    public static final QPaymentsEntity paymentsEntity = new QPaymentsEntity("paymentsEntity");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final StringPath checkNumber = createString("checkNumber");

    public final NumberPath<Integer> customerNumber = createNumber("customerNumber", Integer.class);

    public final DatePath<java.sql.Date> paymentDate = createDate("paymentDate", java.sql.Date.class);

    public QPaymentsEntity(String variable) {
        super(PaymentsEntity.class, forVariable(variable));
    }

    public QPaymentsEntity(Path<? extends PaymentsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentsEntity(PathMetadata metadata) {
        super(PaymentsEntity.class, metadata);
    }

}

