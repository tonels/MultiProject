package querydsl.tonels.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import querydsl.tonels.persistence.dao.PasswordResetTokenRepository;
import querydsl.tonels.persistence.dao.RoleRepository;
import querydsl.tonels.persistence.dao.UserRepository;
import querydsl.tonels.persistence.dao.VerificationTokenRepository;
import querydsl.tonels.persistence.model.PasswordResetToken;
import querydsl.tonels.persistence.model.User;
import querydsl.tonels.persistence.model.VerificationToken;
import querydsl.tonels.service.UserService;
import querydsl.tonels.web.dto.UserDto;
import querydsl.tonels.web.error.UserAlreadyExistException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    public static String APP_NAME = "SpringRegistration";

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException {
        return null;
    }

    @Override
    public User getUser(String verificationToken) {
        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {

    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return null;
    }

    @Override
    public VerificationToken generateNewVerificationToken(String token) {
        return null;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {

    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return null;
    }

    @Override
    public User getUserByPasswordResetToken(String token) {
        return null;
    }

    @Override
    public Optional<User> getUserByID(long id) {
        return Optional.empty();
    }

    @Override
    public void changeUserPassword(User user, String password) {

    }

    @Override
    public boolean checkIfValidOldPassword(User user, String password) {
        return false;
    }

    @Override
    public String validateVerificationToken(String token) {
        return null;
    }

    @Override
    public String generateQRUrl(User user) throws UnsupportedEncodingException {
        return null;
    }

    @Override
    public User updateUser2FA(boolean use2FA) {
        return null;
    }

    @Override
    public List<String> getUsersFromSessionRegistry() {
        return null;
    }
}
