package in.mallikarjun.expenseTrackerAPI.service;

import in.mallikarjun.expenseTrackerAPI.entity.User;
import in.mallikarjun.expenseTrackerAPI.entity.UserModel;
import in.mallikarjun.expenseTrackerAPI.exceptions.ItemAlreadyExistException;
import in.mallikarjun.expenseTrackerAPI.exceptions.ResourceNotFoundException;
import in.mallikarjun.expenseTrackerAPI.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel){
        if ( userRepository.existsByEmail(userModel.getEmail())) {
            throw new ItemAlreadyExistException("The Email already exist "+ userModel.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(userModel,newUser);
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for the ID : "+id));
    }

    @Override
    public User updateUser(UserModel userModel, Long id) {
        User existingUser=readUser(id);
        existingUser.setName((userModel.getName())!= null? userModel.getName():existingUser.getName());
        existingUser.setEmail((userModel.getEmail())!=null? userModel.getEmail():existingUser.getEmail());
        existingUser.setPassword((userModel.getPassword())!= null ? userModel.getPassword():existingUser.getPassword());
        existingUser.setAge((userModel.getAge())!= null? userModel.getAge():existingUser.getAge());

        return userRepository.save(existingUser);

    }

    @Override
    public void deleteUser(Long id) {
        User user = readUser(id);
        userRepository.delete(user);
    }
}
