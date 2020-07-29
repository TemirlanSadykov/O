package com.example.ooo.backend.service;

import com.example.ooo.backend.model.User;
import com.example.ooo.backend.repository.UserRepo;
import com.example.ooo.frontend.forms.UserLoginForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public String createUser(UserLoginForm userLoginForm){
        if(userRepo.findByLogin(userLoginForm.getLogin()).isPresent()){
            return "Данный пользователь зарегистрирован";
        }
        else {
            User user = User.builder()
                    .login(userLoginForm.getLogin())
                    .email(userLoginForm.getEmail())
                    .password(encoder.encode(userLoginForm.getPassword()))
                    .build();

            userRepo.save(user);
            return null;
        }
    }
}
