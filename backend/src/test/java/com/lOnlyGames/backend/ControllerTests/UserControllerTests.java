package com.lOnlyGames.backend.ControllerTests;

import com.lOnlyGames.backend.controllers.UserController;
import com.lOnlyGames.backend.errorhandlers.exceptions.*;
import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.response.*;
import com.lOnlyGames.backend.services.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

    @InjectMocks
    UserController userController;

    @Mock
    BlockedService BlockedService;

    @Mock
    LikeService likeService;

    @Mock
    MatchesService matchesService;

    @Mock
    UserService userService;

    @Mock
    AvailabilityService availabilityService;

    //user is blocked
    @Test
    public void testValidBlockee(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User blockee = new User("Blockee");

        when(BlockedService.blockUser(blockee)).thenReturn("User '" + blockee.getUsername() + "' has been blocked.");

        ResponseEntity<BlockUnblockResponse> responseEntity = (ResponseEntity<BlockUnblockResponse>) userController.blockUser(blockee);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo("User 'Blockee' has been blocked.");
    }


    //user tries to block themselves
    @Test
    public void testInvalidUserBlocksThemselves(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User blocker = new User("Blocker");

        doThrow(new CannotBlockSelfException()).when(BlockedService).blockUser(blocker);
        Assertions.assertThrows(CannotBlockSelfException.class, () -> {
            userController.blockUser(blocker);
        });
    }

    //user tries to block someone they have already blocked
    @Test
    public void testInvalidAlreadyBlocked(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User blockee = new User("Blockee");

        doThrow(new UserAlreadyBlockedException()).when(BlockedService).blockUser(blockee);
        Assertions.assertThrows(UserAlreadyBlockedException.class, () -> {
            userController.blockUser(blockee);
        });
    }

    //user tries to block someone that does not exist
    @Test
    public void testInvalidBlockee(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User blockee = new User("Blockee");

        doThrow(new UsernameNotFoundException("Username '" + blockee.getUsername() + "' is invalid")).when(BlockedService).blockUser(blockee);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userController.blockUser(blockee);
        });
    }

    @Test
    public void testValidUnblock(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User unblock = new User("unblock");

        when(BlockedService.unblockUser(unblock)).thenReturn("User '" + unblock.getUsername() + "' has been unblocked.");

        ResponseEntity<BlockUnblockResponse> responseEntity = (ResponseEntity<BlockUnblockResponse>) userController.unblockUser(unblock);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo("User 'unblock' has been unblocked.");
    }
    @Test
    public void testInvalidUserUnblocksThemselves(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User unblockee = new User("Unblockee");

        doThrow(new CannotUnblockSelfException()).when(BlockedService).unblockUser(unblockee);
        Assertions.assertThrows(CannotUnblockSelfException.class, () -> {
            userController.unblockUser(unblockee);
        });
    }
    @Test
    public void testInvalidUnblockee(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User unblockee = new User("Unblockee");

        doThrow(new UsernameNotFoundException("Username '" + unblockee.getUsername() + "' is invalid")).when(BlockedService).unblockUser(unblockee);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userController.unblockUser(unblockee);
        });
    }
    @Test
    public void testInvalidAlreadyUnblocked(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User unblockee = new User("Unblockee");

        doThrow(new UserAlreadyUnblockedException()).when(BlockedService).unblockUser(unblockee);
        Assertions.assertThrows(UserAlreadyUnblockedException.class, () -> {
            userController.unblockUser(unblockee);
        });
    }

    @Test
    public void testGetBlockedUsers(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<User> blockedUsers = new ArrayList<>();
        User blocked1 = new User("Blocked1");
        User blocked2 = new User("Blocked2");

        blockedUsers.add(blocked1);
        blockedUsers.add(blocked2);

        when(BlockedService.allBlockedByUser()).thenReturn(blockedUsers);
        ResponseEntity<AllBlockedResponse> responseEntity = (ResponseEntity<AllBlockedResponse>) userController.getAllBlockedUsers();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo(blockedUsers);

    }

    @Test
    public void testValidLike(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User toLike = new User("User to like");
        when(likeService.likeUser(toLike)).thenReturn("User '" + toLike.getUsername() + "' has been liked.");

        ResponseEntity<LikeDislikeResponse> responseEntity = (ResponseEntity<LikeDislikeResponse>) userController.likeUser(toLike);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo("User 'User to like' has been liked.");
    }

    @Test
    public void testInvalidUsernameLike(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User toLike = new User("User to like");

        doThrow(new UsernameNotFoundException("Username '" + toLike.getUsername() + "' is invalid")).when(likeService).likeUser(toLike);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userController.likeUser(toLike);
        });
    }

    @Test
    public void testInvalidAlreadyLiked(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User toLike = new User("User to like");
        doThrow(new UserAlreadyLikedException()).when(likeService).likeUser(toLike);
        Assertions.assertThrows(UserAlreadyLikedException.class, () -> {
            userController.likeUser(toLike);
        });
    }

    @Test
    public void testInvalidCannotLikeThemselves(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User toLike = new User("User to like");
        doThrow(new CannotLikeSelfException()).when(likeService).likeUser(toLike);
        Assertions.assertThrows(CannotLikeSelfException.class, () -> {
            userController.likeUser(toLike);
        });
    }

    @Test
    public void testValidDislike(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User toDislike = new User("User to dislike");
        when(likeService.dislikeUser(toDislike)).thenReturn("User '" + toDislike.getUsername() + "' has been disliked.");

        ResponseEntity<LikeDislikeResponse> responseEntity = (ResponseEntity<LikeDislikeResponse>) userController.dislikeUser(toDislike);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo("User 'User to dislike' has been disliked.");
    }

    @Test
    public void testInvalidUsernameDislike(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User toDislike = new User("User to dislike");

        doThrow(new UsernameNotFoundException("Username '" + toDislike.getUsername() + "' is invalid")).when(likeService).dislikeUser(toDislike);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userController.dislikeUser(toDislike);
        });
    }

    @Test
    public void testInvalidAlreadyDisliked(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User toDislike = new User("User to dislike");

        doThrow(new UserAlreadyDislikedException()).when(likeService).dislikeUser(toDislike);
        Assertions.assertThrows(UserAlreadyDislikedException.class, () -> {
            userController.dislikeUser(toDislike);
        });
    }

    @Test
    public void testInvalidCannotDislikeThemselves(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User toDislike = new User("User to dislike");

        doThrow(new CannotUnlikeSelfException()).when(likeService).dislikeUser(toDislike);
        Assertions.assertThrows(CannotUnlikeSelfException.class, () -> {
            userController.dislikeUser(toDislike);
        });
    }

    @Test
    public void testGetAllLikes(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<User> likedUsers = new ArrayList<>();
        User liked1 = new User("Liked1");
        User liked2 = new User("Liked2");

        likedUsers.add(liked1);
        likedUsers.add(liked2);

        when(likeService.getAllLikes()).thenReturn(likedUsers);
        ResponseEntity<AllLikesResponse> responseEntity = (ResponseEntity<AllLikesResponse>) userController.getAllLikes();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo(likedUsers);
    }

    @Test
    public void testGetMatches(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<List<UserGame>> listListUG = new ArrayList<>();

        List<UserGame> listUG1 = new ArrayList<>();
        List<UserGame> listUG2 = new ArrayList<>();

        UserGame ug1 = new UserGame(new User("User1"), new Game("Game1"));
        UserGame ug2 = new UserGame(new User("User2"), new Game("Game2"));

        listUG1.add(ug1);
        listUG2.add(ug2);

        listListUG.add(listUG1);
        listListUG.add(listUG2);
        when(matchesService.getMatches()).thenReturn(listListUG);
        ResponseEntity<MatchesResponse> responseEntity = (ResponseEntity<MatchesResponse>) userController.getMatches();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo(listListUG);
    }

    @Test
    public void testSearchUser(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<User> listUsers = new ArrayList<>();
        User user = new User("User");
        listUsers.add(user);

        List<String> listUsernames = new ArrayList<>();
        listUsernames.add(user.getUsername());

        String str = "Use";

        when(userService.getUsersWithNameLike(str)).thenReturn(listUsers);
        ResponseEntity<UsersListResponse> responseEntity = (ResponseEntity<UsersListResponse>) userController.dynamicSearch(str);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo(listUsernames);
    }

    @Test
    public void testGetProfile(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String profile = "User";
        User user = new User("User");

        when(userService.getProfile(profile)).thenReturn(user);
        ResponseEntity<UserResponse> responseEntity = (ResponseEntity<UserResponse>) userController.getProfile(profile);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo(user);
    }

    @Test
    public void testInvalidGetProfile(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String profile = "Invalid";
        doThrow(new UsernameNotFoundException("Username \"" + profile + "\" is invalid")).when(userService).getProfile(profile);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userController.getProfile(profile);
        });
    }

//    @Test
//    public void testUpdateProfile(){
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        Map<String, String> payload = new Map<String, String>();
//    }
    @Test
    public void testValidReport(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User report = new User("Reported");
        when(userService.reportUser(report)).thenReturn("User '" + report.getUsername() + "' has been reported.");

        ResponseEntity<ReportResponse> responseEntity = (ResponseEntity<ReportResponse>) userController.report(report);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo("User 'Reported' has been reported.");
    }

    @Test
    public void testInvalidReport(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User report = new User("Invalid");
        doThrow(new CannotReportSelfException()).when(userService).reportUser(report);
        Assertions.assertThrows(CannotReportSelfException.class, () -> {
            userController.report(report);
        });
    }

    @Test
    public void testAllAvailabilities(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Availability> listAvailabilities = new ArrayList<>();
        Availability available = new Availability(1, 1000, 1200);
        listAvailabilities.add(available);

        User user = new User("Availabilities");

        when(availabilityService.allUserAvailabilities(user)).thenReturn(listAvailabilities);
        ResponseEntity<AllAvailabilitiesResponse> responseEntity = (ResponseEntity<AllAvailabilitiesResponse>) userController.getUserAvailabilities(user);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo(listAvailabilities);
    }

    @Test
    public void testAvailabilityAdd(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Availability available = new Availability(1, 1000, 1200);
        when(availabilityService.addAvailability(available)).thenReturn("Availability has been successfully added.");
        ResponseEntity<AvailabilityResponse> responseEntity = (ResponseEntity<AvailabilityResponse>) userController.availability(available);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo("Availability has been successfully added.");

    }

    @Test
    public void testInvalidAvailabilityAdd(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Availability available = new Availability(-1, -3, -5);
        doThrow(new InvalidAvailabilityException()).when(availabilityService).addAvailability(available);
        Assertions.assertThrows(InvalidAvailabilityException.class, () -> {
            userController.availability(available);
        });
    }

    @Test
    public void testOverlapAvailability(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Availability available = new Availability(0, 0, 0);
        doThrow(new AvailabilityOverlapException()).when(availabilityService).addAvailability(available);
        Assertions.assertThrows(AvailabilityOverlapException.class, () -> {
            userController.availability(available);
        });
    }

    @Test
    public void testRemoveAvailability(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Availability available = new Availability(0, 0, 0);
        when(availabilityService.removeAvailability(available)).thenReturn("Availability successfully removed");
        ResponseEntity<AvailabilityResponse> responseEntity = (ResponseEntity<AvailabilityResponse>) userController.removeAvailability(available);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo("Availability successfully removed");
    }

    @Test
    public void testInvalidAvailabilityRemove(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Availability available = new Availability(0, 0, 0);

        doThrow(new InvalidAvailabilityException()).when(availabilityService).removeAvailability(available);
        Assertions.assertThrows(InvalidAvailabilityException.class, () -> {
            userController.removeAvailability(available);
        });
    }

    @Test
    public void testGetGames(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Game> list = new ArrayList<>();
        Game game = new Game("Game");

        list.add(game);

        when(userService.getGames()).thenReturn(list);
        ResponseEntity<GamesResponse> responseEntity = (ResponseEntity<GamesResponse>) userController.getMyGames();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getResult()).isEqualTo(list);
    }

}
