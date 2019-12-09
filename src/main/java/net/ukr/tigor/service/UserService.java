package net.ukr.tigor.service;

import net.ukr.tigor.domain.Enums.Role;
import net.ukr.tigor.domain.User;
import net.ukr.tigor.dto.ManagerDto;
import net.ukr.tigor.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClientService clientService;
    @Autowired
    EventService eventService;

    @PostConstruct
    public void checkCreateAdminBeforeStart() {

        User admin = userRepo.findByUsername("admin");
        if (admin == null) {
            admin = new User();
            admin.setUsername("admin");
            admin.setPassword("1");
            admin.setActive(true);
            admin.setRoles(Collections.singleton(Role.ADMIN));
            userRepo.save(admin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public Object getUserList() {
        return userRepo.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }

    public void delete(User user) {
        if (isAdmin(user)) return;

        if (!isObjectsByUser(user)) {
            userRepo.delete(user);
        }
    }

    private boolean isObjectsByUser(User user) {
        int count = eventService.getCountByUser(user) + clientService.getCountByUser(user);
        return count != 0;
    }

    private boolean isAdmin(User user) {
        if (user == null) {
            return false;
        }
        if (user.getUsername().equals("admin")) {
            return true;
        }
        return false;
    }

    public void creatUpdate(User user, Map<String, String> form) {
        // админа не правим
        if (isAdmin(user)) return;

        String userName = form.get("userName");
        String password = form.get("password");

        if (userName == null || password == null) {
            return;
        }

        if (user == null) {
            user = new User();
            user.setActive(true);
            user.setRoles(new HashSet<>());
        }

        user.setUsername(userName);
        user.setPassword(password);
        user.getRoles().clear();
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElse(null);
    }

    public Object getUser(User user) {
        if (user == null) {
            user = new User();
            user.setUsername("");
            user.setPassword("");
            user.setId(0l);
            user.setRoles(new HashSet<>());
        }
        return user;
    }

    public Set<ManagerDto> getUsersDto() {
        List<User> users = userRepo.findAll(Sort.by(Sort.Direction.ASC, "username"));
        Set<ManagerDto> managers = new LinkedHashSet<>();
        for (User usr : users) {
            managers.add(getManagerDto(usr));
        }
        return managers;
    }

    public ManagerDto getManagerDto(User user) {
        ManagerDto manager = new ManagerDto();
        if (user != null) {
            manager.setId(user.getId().intValue());
            manager.setName(user.getUsername());
        } else {
            manager.setId(0);
            manager.setName("");
        }
        return manager;
    }
}
