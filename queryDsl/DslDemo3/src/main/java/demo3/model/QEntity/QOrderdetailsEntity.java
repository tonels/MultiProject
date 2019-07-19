package demo3.model.QEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import demo3.model.OrderdetailsEntity;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOrderdetailsEntity is a Querydsl query type for OrderdetailsEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderdetailsEntity extends EntityPathBase<OrderdetailsEntity> {

    private static final long serialVersionUID = -1813015366L;

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

