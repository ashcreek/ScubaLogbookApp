package com.andreahowes.dive_db.presentation.api;

import com.andreahowes.dive_db.logic.SecurityModule.JWT.Credentials;
import com.andreahowes.dive_db.logic.SecurityModule.JWT.MyTokenService;
import com.andreahowes.dive_db.logic.SecurityModule.JWT.Token;
import com.andreahowes.dive_db.logic.SecurityModule.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/api")
public class AuthenticationApiController {
    private Logger logger = LoggerFactory.getLogger(AuthenticationApiController.class);


    @Resource
    private UserService userService;

    @Resource
    private MyTokenService myTokenService;

    @PostMapping("/authenticate")
    public ResponseEntity<Token> getCredentials(@RequestBody Credentials credentials) {
        Boolean isUserValid = userService.checkCredentials(credentials.getUser(), credentials.getPassword());
        if (isUserValid) {
            logger.info("An INFO Message");
            return new ResponseEntity<>(myTokenService.createApiToken(credentials), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Token(), HttpStatus.UNAUTHORIZED);
        }

    }

}
