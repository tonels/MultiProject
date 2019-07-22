package example.users.QEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.StringPath;
import example.users.User;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUser_Picture is a Querydsl query type for Picture
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUser_Picture extends BeanPath<User.Picture> {

    private static final long serialVersionUID = 1919653637L;

    public static final QUser_Picture picture = new QUser_Picture("picture");

    public final StringPath large = createString("large");

    public final StringPath medium = createString("medium");

    public final StringPath small = createString("small");

    public QUser_Picture(String variable) {
        super(User.Picture.class, forVariable(variable));
    }

    public QUser_Picture(Path<? extends User.Picture> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser_Picture(PathMetadata metadata) {
        super(User.Picture.class, metadata);
    }

}

