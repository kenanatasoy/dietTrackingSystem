package api.registration;

import api.config.CustomUserDetailRepository;
import api.config.CustomUserDetails;
import api.login.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registry/")
public class RegistrationController {

    @Autowired
    CustomUserDetailRepository customUserDetailRepository;

    @PostMapping("user")
    public void saveUser(@RequestBody UserDto userDto) {

        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            CustomUserDetails customUserDetails = CustomUserDetails.builder()
                    .id(userDto.getId())
                    .username(userDto.getUsername())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .emailAddress(userDto.getEmailAddress())
                    .password(userDto.getPassword())
                    .build();

            customUserDetailRepository.save(customUserDetails);
    }
}
