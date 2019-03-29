package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    DefaultTokenServices defaultTokenServices;

    @PostMapping("/logout/")
    @ResponseStatus(HttpStatus.OK)
    public void clearAccessToken(HttpServletRequest request) {
//        String authorization = request.getHeader("Authorization");
//        if (StringUtils.isEmpty(authorization)) {
//            throw new PartyPlusBadRequestException("wrong header");
//        }
//        String[] splitToken = authorization.split(" ");
//        if (splitToken.length == 2 && splitToken[0].equalsIgnoreCase("bearer")) {
//            String tokenId = splitToken[1];
//            defaultTokenServices.revokeToken(tokenId);
//        } else {
//            throw new PartyPlusBadRequestException("wrong header");
//        }
    }
}
