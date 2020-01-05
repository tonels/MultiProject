package querydsl.tonels.persistence.model;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RoleEnum {
    ADMIN(0),
    USER(1),
    DEV_JAVA(2),
    DEV_PYTHON(3);

    private Integer role;

    RoleEnum(Integer role) {
        this.role = role;
    }

    public Integer getRoleNum() {
        return role;
    }

    public static RoleEnum of(Integer role) {
        return Stream.of(RoleEnum.values())
                .filter(p -> p.getRoleNum() == role)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static void main(String[] args) {

        RoleEnum[] values = RoleEnum.values();
        String collect = Stream.of(values).map(RoleEnum::name).collect(Collectors.joining(","));
        System.out.println(collect); // LOW , MEDIUM , HIGH

        System.out.println(RoleEnum.of(1));
        System.out.println(RoleEnum.valueOf("ADMIN"));
        System.out.println(RoleEnum.ADMIN.getRoleNum());

    }
}
