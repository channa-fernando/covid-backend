package com.covid.covid.service;

import com.covid.covid.dto.LoginDTO;
import com.covid.covid.dto.LoginResponseDTO;
import com.covid.covid.dto.UserAccountDTO;
import com.covid.covid.entity.UserAccount;
import com.covid.covid.repository.UserAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserAccount saveUpdate(UserAccountDTO userAccountDTO) {
        try {
            UserAccount userAccount = modelMapper.map(userAccountDTO, UserAccount.class);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userAccount.setPassWord(bCryptPasswordEncoder.encode(userAccountDTO.getPassWord()));
            userAccountRepository.save(userAccount);
            return userAccount;
        } catch (Exception e) {
            return null;
        }
    }

    public LoginResponseDTO loginRequest(LoginDTO loginDTO) {
        if (loginDTO.getEmail() != null && loginDTO.getPassWord() != null) {
            UserAccount userAccount = userAccountRepository.findUseByEmail(loginDTO.getEmail());
            if (userAccount != null) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
                if (bCryptPasswordEncoder.matches(loginDTO.getPassWord(), userAccount.getPassWord())) {
                    String token = bCryptPasswordEncoder.encode(loginDTO.getPassWord());
                    loginResponseDTO.setToken(token);
                    loginResponseDTO.setUserId(userAccount.getId().toString());
                    loginResponseDTO.setUserName(userAccount.getEmail());
                    loginResponseDTO.setAddress(userAccount.getEmail());
                    userAccount.setToken(token);
                    userAccountRepository.save(userAccount);
                    return loginResponseDTO;
                }

            }
        }
        return null;
    }

    public UserAccount findByToken(String token) {
        return userAccountRepository.findUseByToken(token);
    }

    public Optional<UserAccount> findById(Long id){
        return userAccountRepository.findById(id);
    }
}
