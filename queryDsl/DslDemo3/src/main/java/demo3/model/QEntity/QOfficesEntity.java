package demo3.model.QEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import demo3.model.OfficesEntity;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QOfficesEntity is a Querydsl query type for OfficesEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOfficesEntity extends EntityPathBase<OfficesEntity> {

    private static final long serialVersionUID = -1435199081L;

    public static final QOfficesEntity officesEntity = new QOfficesEntity("officesEntity");

    public final StringPath addressLine1 = createString("addressLine1");

    public final StringPath addressLine2 = createString("addressLine2");

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath officeCode = createString("officeCode");

    public final StringPath phone = createString("phone");

    public final StringPath postalCode = createString("postalCode");

    public final StringPath state = createString("state");

    public final StringPath territory = createString("territory");

    public QOfficesEntity(String variable) {
        super(OfficesEntity.class, forVariable(variable));
    }

    public QOfficesEntity(Path<? extends OfficesEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOfficesEntity(PathMetadata metadata) {
        super(OfficesEntity.class, metadata);
    }

}

