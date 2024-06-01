package in.mallikarjun.expenseTrackerAPI.Controller;

import in.mallikarjun.expenseTrackerAPI.entity.AuthModel;
import in.mallikarjun.expenseTrackerAPI.entity.JwtResponse;
import in.mallikarjun.expenseTrackerAPI.entity.User;
import in.mallikarjun.expenseTrackerAPI.entity.UserModel;
import in.mallikarjun.expenseTrackerAPI.security.CustomUserDetailsService;
import in.mallikarjun.expenseTrackerAPI.service.UserService;
import in.mallikarjun.expenseTrackerAPI.util.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login (@RequestBody AuthModel authModel) throws Exception{

        authenticate(authModel.getEmail(),authModel.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authModel.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
    }
    private void authenticate(String email, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch (DisabledException e){
            throw new Exception("user Disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad Credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.createUser(userModel),HttpStatus.CREATED);
    }
}
