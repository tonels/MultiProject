package querydsl.model;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTHotel is a Querydsl query type for THotel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTHotel extends EntityPathBase<THotel> {

    private static final long serialVersionUID = -2097641090L;

    public static final QTHotel tHotel = new QTHotel("tHotel");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> city = createNumber("city", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QTHotel(String variable) {
        super(THotel.class, forVariable(variable));
    }

    public QTHotel(Path<? extends THotel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTHotel(PathMetadata metadata) {
        super(THotel.class, metadata);
    }

}

