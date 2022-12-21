package com.techelevator.deck.services;

import com.techelevator.deck.model.CredentialsDto;
import com.techelevator.deck.model.TokenDto;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


public class AuthenticationService {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();

    public String login(String username, String password) {
        CredentialsDto credentialsDto = new CredentialsDto();
        credentialsDto.setUsername(username);
        credentialsDto.setPassword(password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CredentialsDto> entity = new HttpEntity<>(credentialsDto, headers);
        String token = null;
        try {
            // Add code here to send the request to the API and get the token from the response.
            ResponseEntity<TokenDto> response = restTemplate.exchange(API_BASE_URL + "login", HttpMethod.POST, entity, TokenDto.class);
            TokenDto body = response.getBody();
            if(body!=null){
                token = body.getToken();
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return token;

    }

}
