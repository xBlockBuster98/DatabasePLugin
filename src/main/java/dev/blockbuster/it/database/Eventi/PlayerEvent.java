package dev.blockbuster.it.database.Eventi;

import dev.blockbuster.it.database.Database;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PlayerEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws SQLException{
        Player p = e.getPlayer();

        ResultSet res = Database.prepareStatement("SELECT COUNT(UUID) FROM player_info WHERE UUID = '" + p.getUniqueId() + "';").executeQuery();
        res.next();

        if (res.getInt(1) == 0) {
            Database.prepareStatement(
                    "INSERT INTO player_info(UUID, RANK, MONEY, JOINDATE) VALUES ('" + p.getUniqueId() + "',"
                            + "'GUEST', DEFAULT, DEFAULT);").executeUpdate();
        } else {
            ResultSet rs = Database.prepareStatement("SELECT * FROM player_info WHERE UUID = '" + p.getUniqueId() + "';").executeQuery();
            rs.next();

            String rank = rs.getString("RANK");
            int money = rs.getInt("MONEY");
            Timestamp ts = rs.getTimestamp("JOINDATE");

            System.out.println("\n\nRank: " + rank);
            System.out.println("Money: " + money);
            System.out.println("First join: " + ts + "\n\n");
        }

    }
}
