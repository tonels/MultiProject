package demo3.model.QEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import demo3.model.OrdersEntity;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOrdersEntity is a Querydsl query type for OrdersEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrdersEntity extends EntityPathBase<OrdersEntity> {

    private static final long serialVersionUID = 164025835L;

    public static final QOrdersEntity ordersEntity = new QOrdersEntity("ordersEntity");

    public final StringPath comments = createString("comments");

    public final NumberPath<Integer> customerNumber = createNumber("customerNumber", Integer.class);

    public final DatePath<java.sql.Date> orderDate = createDate("orderDate", java.sql.Date.class);

    public final NumberPath<Integer> orderNumber = createNumber("orderNumber", Integer.class);

    public final DatePath<java.sql.Date> requiredDate = createDate("requiredDate", java.sql.Date.class);

    public final DatePath<java.sql.Date> shippedDate = createDate("shippedDate", java.sql.Date.class);

    public final StringPath status = createString("status");

    public QOrdersEntity(String variable) {
        super(OrdersEntity.class, forVariable(variable));
    }

    public QOrdersEntity(Path<? extends OrdersEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrdersEntity(PathMetadata metadata) {
        super(OrdersEntity.class, metadata);
    }

}

