package com.ankit.bidding.services.authentication;
import com.ankit.bidding.models.UserInfo;
import com.ankit.bidding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserInfo> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(
                user.get().getId(),
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.get().getRoles().toString()))
        );
    }
}