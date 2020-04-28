package tonels.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderdetailsEntity is a Querydsl query type for OrderdetailsEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderdetailsEntity extends EntityPathBase<OrderdetailsEntity> {

    private static final long serialVersionUID = 1121168653L;

    public static final QOrderdetailsEntity orderdetailsEntity = new QOrderdetailsEntity("orderdetailsEntity");

    public final NumberPath<Short> orderLineNumber = createNumber("orderLineNumber", Short.class);

    public final NumberPath<Integer> orderNumber = createNumber("orderNumber", Integer.class);

    public final NumberPath<java.math.BigDecimal> priceEach = createNumber("priceEach", java.math.BigDecimal.class);

    public final StringPath productCode = createString("productCode");

    public final NumberPath<Integer> quantityOrdered = createNumber("quantityOrdered", Integer.class);

    public QOrderdetailsEntity(String variable) {
        super(OrderdetailsEntity.class, forVariable(variable));
    }

    public QOrderdetailsEntity(Path<? extends OrderdetailsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderdetailsEntity(PathMetadata metadata) {
        super(OrderdetailsEntity.class, metadata);
    }

}

