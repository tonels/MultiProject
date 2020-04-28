package querydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTHotel is a Querydsl query type for THotel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTHotel extends EntityPathBase<THotel> {

    private static final long serialVersionUID = -922676238L;

    public static final QTHotel tHotel = new QTHotel("tHotel");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> city = createNumber("city", Integer.class);

    public final DatePath<java.time.LocalDate> hotelDate = createDate("hotelDate", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> hotelDateTime = createDateTime("hotelDateTime", java.time.LocalDateTime.class);

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

