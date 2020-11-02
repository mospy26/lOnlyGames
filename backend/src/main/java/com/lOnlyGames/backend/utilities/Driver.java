package com.lOnlyGames.backend.utilities;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.utilities.wrappers.CODMW;
import com.lOnlyGames.backend.utilities.wrappers.PUBG;
import com.lOnlyGames.backend.utilities.wrappers.Runescape;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import java.io.IOException;




public class Driver {

    public static void main(String args[]) throws SteamApiException, IOException {
        User user = new User();
        user.setBattlenet("BigBick47#1647");
        user.setPubGPlayerName("LonelyDeBaby");
        System.out.println(new CODMW("BigBick47#1647").resolveAllGameStats());
        System.out.println(new PUBG("LongDeBaby").resolveAllStats());
    }
}
