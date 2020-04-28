package querydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductlinesEntity is a Querydsl query type for ProductlinesEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductlinesEntity extends EntityPathBase<ProductlinesEntity> {

    private static final long serialVersionUID = 935455429L;

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

