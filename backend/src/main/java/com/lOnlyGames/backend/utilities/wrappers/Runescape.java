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

   private Optional<Player> optional;
   private Hiscores hiscores;
   private String userName;
    public Runescape(String userName) {

           this.userName = userName;
            RuneScapeAPI api = RuneScapeAPI.createHttp();
            hiscores = api.hiscores();


    }

    public String resolveAllStats() throws IOException {
        optional = hiscores.playerInformation(userName, HiscoreTable.DEFAULT);
        String s = "";

        if (!optional.isPresent()) {
            return null;
        }

    for (Map.Entry<String, Skill> entry : optional.get().getSkills().entrySet()) {
        s += entry.getKey() + ": " + entry.getValue().getLevel() + "\n";
    }


        return s;
    }
}
