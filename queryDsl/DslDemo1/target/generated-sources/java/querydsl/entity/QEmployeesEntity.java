package querydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmployeesEntity is a Querydsl query type for EmployeesEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployeesEntity extends EntityPathBase<EmployeesEntity> {

    private static final long serialVersionUID = 772385206L;

    public static final QEmployeesEntity employeesEntity = new QEmployeesEntity("employeesEntity");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> employeeNumber = createNumber("employeeNumber", Integer.class);

    public final StringPath extension = createString("extension");

    public final StringPath firstName = createString("firstName");

    public final StringPath jobTitle = createString("jobTitle");

    public final StringPath lastName = createString("lastName");

    public final StringPath officeCode = createString("officeCode");

    public final NumberPath<Integer> reportsTo = createNumber("reportsTo", Integer.class);

    public QEmployeesEntity(String variable) {
        super(EmployeesEntity.class, forVariable(variable));
    }

    public QEmployeesEntity(Path<? extends EmployeesEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployeesEntity(PathMetadata metadata) {
        super(EmployeesEntity.class, metadata);
    }

}

