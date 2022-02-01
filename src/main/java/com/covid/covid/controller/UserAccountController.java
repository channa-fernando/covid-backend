package com.covid.covid.controller;

import com.covid.covid.dto.LoginDTO;
import com.covid.covid.dto.LoginResponseDTO;
import com.covid.covid.dto.UserAccountDTO;
import com.covid.covid.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {
    Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/saveUpdate")
    public ResponseEntity<String> saveUpdate(@RequestBody UserAccountDTO userAccountDTO) {
        logger.info("User Registration ==> saveUpdate() {}", userAccountDTO.toString());
        if (userAccountService.saveUpdate(userAccountDTO) != null) {
            return new ResponseEntity<>("Success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        logger.info("User Login ==> login() {}", loginDTO.toString());
        LoginResponseDTO loginResponseDTO = userAccountService.loginRequest(loginDTO);
        if (loginResponseDTO != null) {
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
