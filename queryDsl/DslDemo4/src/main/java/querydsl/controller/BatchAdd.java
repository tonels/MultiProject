package querydsl.controller;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import querydsl.tonels.persistence.dao.PrivilegeRepository;
import querydsl.tonels.persistence.dao.RoleRepository;
import querydsl.tonels.persistence.dao.UserRepository;
import querydsl.tonels.persistence.model.Privilege;
import querydsl.tonels.persistence.model.Role;
import querydsl.tonels.persistence.model.RoleEnum;
import querydsl.tonels.persistence.model.User;
import querydsl.tonels.web.error.CustomerException;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class BatchAdd {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PrivilegeRepository privilegeRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping("/update")
    @Transactional
    public void update() throws Exception {

        // == create initial Privilege
        final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        final Privilege passwordPrivilege = createPrivilegeIfNotFound("CHANGE_PASSWORD_PRIVILEGE");

        // == create initial roles
        ArrayList<Privilege> adminPrivileges = Lists.newArrayList(readPrivilege, writePrivilege, passwordPrivilege);
        ArrayList<Privilege> userPrivileges = Lists.newArrayList(readPrivilege, passwordPrivilege);

        final Role adminRole = createRoleIfNotFound(RoleEnum.ADMIN.getRoleNum(), adminPrivileges);
        createRoleIfNotFound(RoleEnum.USER.getRoleNum(), userPrivileges);

        // == create initial user
        createUserIfNotFound("test@test.com","Test", "test", new ArrayList<Role>(Arrays.asList(adminRole)));
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(final Integer name, final List<Privilege> privileges) throws Exception {
        Optional<Role> byNameValue = roleRepository.findByNameValue(name);
        if (byNameValue.isPresent()) {
            throw new CustomerException("该数据已存在");
        }
        // todo 这里role中fillPersistent会报空指针错误
        Role role = new Role();
        role.setNameValue(name);
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    User createUserIfNotFound(final String email, final String userName,  final String password, final List<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setUserName(userName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(true);
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }



}
