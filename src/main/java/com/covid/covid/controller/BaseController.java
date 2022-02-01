package com.covid.covid.controller;

import com.covid.covid.entity.UserAccount;
import com.covid.covid.repository.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import static com.covid.covid.util.Constant.API_HEADER_AUTH;

@Controller
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserAccountRepository userAccountRepository;

    boolean validateUser(HttpServletRequest request) {
        String token = request.getHeader(API_HEADER_AUTH);
        if (token != null) {
            UserAccount userAccount = userAccountRepository.findUseByToken(token);
            if (userAccount != null) {
                logger.info("User validation success!, User Id: {}", userAccount.getId());
                return true;
            }
        }
        return false;
    }


}