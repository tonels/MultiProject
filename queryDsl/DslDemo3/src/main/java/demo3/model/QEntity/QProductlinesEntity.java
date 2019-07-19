package demo3.model.QEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.ArrayPath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import demo3.model.ProductlinesEntity;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QProductlinesEntity is a Querydsl query type for ProductlinesEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductlinesEntity extends EntityPathBase<ProductlinesEntity> {

    private static final long serialVersionUID = -474120202L;

    public static final QProductlinesEntity productlinesEntity = new QProductlinesEntity("productlinesEntity");

    public final StringPath htmlDescription = createString("htmlDescription");

    public final ArrayPath<byte[], Byte> image = createArray("image", byte[].class);

    public final StringPath productLine = createString("productLine");

    public final StringPath textDescription = createString("textDescription");

    public QProductlinesEntity(String variable) {
        super(ProductlinesEntity.class, forVariable(variable));
    }

    public QProductlinesEntity(Path<? extends ProductlinesEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductlinesEntity(PathMetadata metadata) {
        super(ProductlinesEntity.class, metadata);
    }

}

