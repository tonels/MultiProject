package tonels.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductsEntity is a Querydsl query type for ProductsEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductsEntity extends EntityPathBase<ProductsEntity> {

    private static final long serialVersionUID = -166871491L;

    public static final QProductsEntity productsEntity = new QProductsEntity("productsEntity");

    public final NumberPath<java.math.BigDecimal> buyPrice = createNumber("buyPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> msrp = createNumber("msrp", java.math.BigDecimal.class);

    public final StringPath productCode = createString("productCode");

    public final StringPath productDescription = createString("productDescription");

    public final StringPath productLine = createString("productLine");

    public final StringPath productName = createString("productName");

    public final StringPath productScale = createString("productScale");

    public final StringPath productVendor = createString("productVendor");

    public final NumberPath<Short> quantityInStock = createNumber("quantityInStock", Short.class);

    public QProductsEntity(String variable) {
        super(ProductsEntity.class, forVariable(variable));
    }

    public QProductsEntity(Path<? extends ProductsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductsEntity(PathMetadata metadata) {
        super(ProductsEntity.class, metadata);
    }

}

