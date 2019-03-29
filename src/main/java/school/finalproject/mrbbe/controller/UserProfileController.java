package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.user.User;
import school.finalproject.mrbbe.dto.user.UserDTO;
import school.finalproject.mrbbe.service.user.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user-profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @GetMapping("/me")
    public UserDTO getCurrentUserProfile() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() != null) {
            final User currentUser = (User) authentication.getPrincipal();
            return userService.get(currentUser.getId());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not found!");
    }

    @PostMapping("/logout")
    public void clearAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String[] splitToken = authorization.split(" ");
        if (splitToken.length == 2 && splitToken[0].equalsIgnoreCase("bearer")) {
            String tokenId = splitToken[1];
            defaultTokenServices.revokeToken(tokenId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong header!");
        }
    }
}
