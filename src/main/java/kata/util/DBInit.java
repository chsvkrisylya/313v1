package kata.util;

import kata.model.Role;
import kata.model.User;
import kata.repository.RoleRepository;
import kata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import kata.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DBInit {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @Autowired
    public DBInit(RoleRepository roleRepository,
                  UserRepository userRepository,
                  UserServiceImpl userService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostConstruct
    public void initData() {
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        User adminUser = userRepository.findByUsername("admin");
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setFirstName("nata");
            adminUser.setLastName("cherepashka");
            adminUser.setAge(22);
            adminUser.setPassword("admin");
            adminUser.setEmail("admin@gmail.com");
            adminUser.setRoles(List.of(adminRole, userRole));
            userService.saveUser(adminUser);
        }

        User regularUser = userRepository.findByUsername("user");
        if (regularUser == null) {
            regularUser = new User();
            regularUser.setUsername("user");
            regularUser.setFirstName("Sian");
            regularUser.setLastName("Li");
            regularUser.setAge(100);
            regularUser.setPassword("user");
            regularUser.setEmail("user@gmail.com");
            regularUser.setRoles(List.of(userRole));
            userService.saveUser(regularUser);
        }
    }
}
