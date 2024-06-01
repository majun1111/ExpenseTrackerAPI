package in.mallikarjun.expenseTrackerAPI.security;

import in.mallikarjun.expenseTrackerAPI.exceptions.ResourceNotFoundException;
import in.mallikarjun.expenseTrackerAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        in.mallikarjun.expenseTrackerAPI.entity.User existingUser = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found for the email:"+email));

        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(),existingUser.getPassword(),new ArrayList<>());
    }
}
