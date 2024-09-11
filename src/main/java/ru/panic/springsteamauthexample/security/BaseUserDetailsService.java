package ru.panic.springsteamauthexample.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.panic.springsteamauthexample.model.User;
import ru.panic.springsteamauthexample.repository.user.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userRepository.findById(UUID.fromString(username)).orElseThrow(() -> new UsernameNotFoundException("Auth failed"));

        return new ru.panic.springsteamauthexample.security.UserDetails(currentUser.getId().toString());
    }
}
