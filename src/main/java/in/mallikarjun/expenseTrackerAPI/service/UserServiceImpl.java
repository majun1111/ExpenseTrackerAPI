package in.mallikarjun.expenseTrackerAPI.service;

import in.mallikarjun.expenseTrackerAPI.entity.User;
import in.mallikarjun.expenseTrackerAPI.entity.UserModel;
import in.mallikarjun.expenseTrackerAPI.exceptions.ItemAlreadyExistException;
import in.mallikarjun.expenseTrackerAPI.exceptions.ResourceNotFoundException;
import in.mallikarjun.expenseTrackerAPI.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel){
        if ( userRepository.existsByEmail(userModel.getEmail())) {
            throw new ItemAlreadyExistException("The Email already exist "+ userModel.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(userModel,newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User readUser() {
        Long userId= getLoggedInUser().getId();
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for the ID : "+userId));
    }

    @Override
    public User updateUser(UserModel userModel) {
        User existingUser=readUser();
        existingUser.setName((userModel.getName())!= null? userModel.getName():existingUser.getName());
        existingUser.setEmail((userModel.getEmail())!=null? userModel.getEmail():existingUser.getEmail());
        existingUser.setPassword((userModel.getPassword())!= null ? bcryptEncoder.encode(userModel.getPassword()):existingUser.getPassword());
        existingUser.setAge((userModel.getAge())!= null? userModel.getAge():existingUser.getAge());

        return userRepository.save(existingUser);

    }

    @Override
    public void deleteUser() {
        User existingUser = readUser();
        userRepository.delete(existingUser);
    }
    @Override
    public User getLoggedInUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        String email =authentication.getName();

        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found with Email "+email));
    }
}
