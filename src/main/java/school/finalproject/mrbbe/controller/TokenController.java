package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.config.Const;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = {"/oauth"},
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Validated
public class TokenController {
    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(method = RequestMethod.DELETE, path = "/revoke")
    @ResponseStatus(HttpStatus.OK)
    public void revokeToken(Authentication authentication) {
        final String userToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        tokenServices.revokeToken(userToken);
    }

    @GetMapping(path = "/token/list")
    @ResponseStatus(HttpStatus.OK)
    public List<String> findAllTokens() {
        final Collection<OAuth2AccessToken> tokensByClientId = tokenStore.findTokensByClientId(Const.CLIENT_ID);

        return tokensByClientId.stream().map(OAuth2AccessToken::getValue).collect(Collectors.toList());
    }
}
