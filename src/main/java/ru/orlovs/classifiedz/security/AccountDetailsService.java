package ru.orlovs.classifiedz.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.orlovs.classifiedz.domain.User;
import ru.orlovs.classifiedz.domain.UserRepo;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo
                .findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + s));
        // TODO translate this

        AccountDetails details = new AccountDetails();
        details.setId(user.getId());
        details.setEmail(user.getEmail());
        details.setPassword(user.getPassword());
        details.setRole(user.getRole());
        return details;
    }
}
