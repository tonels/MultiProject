package demo3.model.QEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import demo3.model.PaymentsEntity;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QPaymentsEntity is a Querydsl query type for PaymentsEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPaymentsEntity extends EntityPathBase<PaymentsEntity> {

    private static final long serialVersionUID = -2061269133L;

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

