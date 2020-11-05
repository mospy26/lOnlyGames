package com.lOnlyGames.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lOnlyGames.backend.auth.JwtTokenUtil;
import com.lOnlyGames.backend.controllers.AuthenticationController;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidUsernameException;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.response.JwtTokenResponse;
import com.lOnlyGames.backend.services.UserService;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationTests {

    @InjectMocks
    AuthenticationController authenticationController;

    @Mock
    UserService userService;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testControllerWithValidUserCredsReturnsResponseEntity() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User testUser = new User("username");
        testUser.setPassword("password");
        Map<String, String> creds = new HashMap<>();
        creds.put("username", "username");
        creds.put("password", "password");
        when(userService.authenticate("username", "password")).thenReturn(testUser);
        when(userService.loadUserByUsername("username")).thenReturn(testUser);
        when(jwtTokenUtil.generateToken(testUser)).thenReturn("TOKENN");
        ResponseEntity<JwtTokenResponse> responseEntity = (ResponseEntity<JwtTokenResponse>) authenticationController.login(creds);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo("TOKENN");
    }

    @Test
    public void testControllerWithInvalidUserCredsReturnsResponseEntity() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User testUser = new User("invalid username");
        testUser.setPassword("password");
        Map<String, String> creds = new HashMap<>();
        creds.put("username", "invalid username");
        creds.put("password", "password");
        when(userService.authenticate("invalid username", "password")).thenThrow(new InvalidCredentialsException());
        Assertions.assertThrows(InvalidCredentialsException.class, () -> {
            authenticationController.login(creds);
        });
    }

    @Test
    public void testControllerRegisterWithValidCreds() throws IOException, SteamApiException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User testUser = new User("username");
        testUser.setPassword("password");

        when(userService.loadUserByUsername("username")).thenReturn(testUser);
        when(jwtTokenUtil.generateToken(testUser)).thenReturn("TOKENN");
        ResponseEntity<JwtTokenResponse> responseEntity = (ResponseEntity<JwtTokenResponse>) authenticationController.register(testUser);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo("TOKENN");
    }

    @Test
    public void testControllerRegisterWithInvalidUserCredsReturnsResponseEntity() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User testUser = new User("invalid username");
        testUser.setPassword("password");
        doThrow(new InvalidUsernameException()).when(userService).register(testUser);

        Assertions.assertThrows(InvalidUsernameException.class, () -> {
            authenticationController.register(testUser);
        });
    }
}
