package queryDsl.entity.QEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import queryDsl.entity.User;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -197907843L;

    public static final QUser user = new QUser("user");

    public final SetPath<queryDsl.entity.BlogPost, QBlogPost> blogPosts = this.<queryDsl.entity.BlogPost, QBlogPost>createSet("blogPosts", queryDsl.entity.BlogPost.class, QBlogPost.class, PathInits.DIRECT2);

    public final BooleanPath disables = createBoolean("disables");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath login = createString("login");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

