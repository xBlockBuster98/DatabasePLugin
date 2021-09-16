package dev.blockbuster.it.database;

import dev.blockbuster.it.database.Eventi.PlayerEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Database extends JavaPlugin {

    private String HOST, USERNAME, PASSWORD, DATABASE;
    private int PORT;

    private static Connection con;

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new PlayerEvent(), this);

        HOST = "localhost";
        PORT = 3306;
        DATABASE = "BungeeCord";
        USERNAME = "root";
        PASSWORD = "";

        try {
            openConnection();

            System.out.println("\nConnessione al database effettuata con successo!\n");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    private void openConnection() throws SQLException {
        if (con != null && !con.isClosed()) {

        }
        con = DriverManager.getConnection(
                "jdbc:mysql://" + this.HOST + ":" + this.PORT + "/" +
                this.DATABASE, this.USERNAME, this.PASSWORD);
    }

    public static PreparedStatement prepareStatement(String string) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(string);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

}
