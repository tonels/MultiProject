package tonels.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTCity is a Querydsl query type for TCity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTCity extends EntityPathBase<TCity> {

    private static final long serialVersionUID = 965325673L;

    public static final QTCity tCity = new QTCity("tCity");

    public final DatePath<java.time.LocalDate> cityDate = createDate("cityDate", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> cityDateTime = createDateTime("cityDateTime", java.time.LocalDateTime.class);

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

