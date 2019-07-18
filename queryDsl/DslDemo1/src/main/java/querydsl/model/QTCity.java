package querydsl.model;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTCity is a Querydsl query type for TCity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTCity extends EntityPathBase<TCity> {

    private static final long serialVersionUID = -1037651871L;

    public static final QTCity tCity = new QTCity("tCity");

    public final StringPath country = createString("country");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath map = createString("map");

    public final StringPath name = createString("name");

    public final StringPath state = createString("state");

    public QTCity(String variable) {
        super(TCity.class, forVariable(variable));
    }

    public QTCity(Path<? extends TCity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTCity(PathMetadata metadata) {
        super(TCity.class, metadata);
    }

}

