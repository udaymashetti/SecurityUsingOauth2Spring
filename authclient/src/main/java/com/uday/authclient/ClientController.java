package com.uday.authclient;

import org.apache.catalina.User;
import org.apache.catalina.users.AbstractUser;
import org.apache.catalina.users.GenericUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
public class ClientController {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping("/message")
    public String message(Principal principal) {
        RestTemplate rs = new RestTemplate();
        String accessToken = oAuth2AuthorizedClientService.loadAuthorizedClient("reg-client", principal.getName()).getAccessToken().getTokenValue();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<String> exchange = rs.exchange("http://localhost:8081/hello", HttpMethod.GET, entity, String.class);
        return "SUCCESS :: "+exchange.getBody();
    }

    @GetMapping("/accessToken")
    public String getAccessToken(Principal principal){
        return oAuth2AuthorizedClientService.loadAuthorizedClient("reg-client", principal.getName()).getAccessToken().getTokenValue();
    }

}
