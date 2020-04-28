package example.users;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser_Address is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUser_Address extends BeanPath<User.Address> {

    private static final long serialVersionUID = 1349703163L;

    public static final QUser_Address address = new QUser_Address("address");

    public final StringPath city = createString("city");

    public final StringPath street = createString("street");

    public final StringPath zip = createString("zip");

    public QUser_Address(String variable) {
        super(User.Address.class, forVariable(variable));
    }

    public QUser_Address(Path<? extends User.Address> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser_Address(PathMetadata metadata) {
        super(User.Address.class, metadata);
    }

}

