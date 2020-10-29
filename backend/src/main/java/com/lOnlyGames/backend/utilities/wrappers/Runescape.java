package com.lOnlyGames.backend.utilities.wrappers;

import com.github.michaelbull.rs.RuneScapeAPI;
import com.github.michaelbull.rs.hiscores.HiscoreTable;
import com.github.michaelbull.rs.hiscores.Hiscores;
import com.github.michaelbull.rs.hiscores.Player;
import com.github.michaelbull.rs.hiscores.Skill;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class Runescape {

   Optional<Player> optional;

    public Runescape(String userName) throws IOException {

        RuneScapeAPI api = RuneScapeAPI.createHttp();
        Hiscores hiscores = api.hiscores();
        optional = hiscores.playerInformation(userName, HiscoreTable.DEFAULT);
    }

    public String getAllStats()
    {
        String s = "";
        for (Map.Entry<String, Skill> entry : optional.get().getSkills().entrySet()) {
            s+= entry.getKey() + ": " + entry.getValue().getLevel() + "\n" ;
        }
        System.out.println(s);
        return s;
    }
}
