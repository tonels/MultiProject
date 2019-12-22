package querydsl.tonels.security;

public interface SecurityUserService {

    String validatePasswordResetToken(long id, String token);

}
