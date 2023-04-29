package com.yelloelefant.exampleplugin.commands;

import com.yelloelefant.exampleplugin.ExamplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Commands implements CommandExecutor {
    public Location prevLocation;
    public ExamplePlugin MainPlugin = ExamplePlugin.getInstance();
    public HashMap<String, Location> homes = new HashMap<String, Location>();
    public HashMap<String, Location> backs = new HashMap<String, Location>();

    @Override
    public boolean onCommand(@NotNull CommandSender Sender, @NotNull Command cmd, @NotNull String label,
            @NotNull String[] args) {

        FileConfiguration config = MainPlugin.getConfig();

        String playerName = Sender.getName();
        if (!(Sender instanceof Player)) {
            Sender.sendMessage("only for players dumb ass");
            return true;
        }
        Player player = (Player) Sender;

        // home
        if (cmd.getName().equalsIgnoreCase("home")) {
            Location locationToTeleport = homes.get(playerName);
            if (locationToTeleport == null) {
                player.sendMessage("plz set a home first");
                return true;
            }

            prevLocation = player.getLocation();
            backs.put(playerName, prevLocation);

            player.teleport(locationToTeleport);
            return true;
        }

        // back
        if (cmd.getName().equalsIgnoreCase("back")) {
            Location locationToTeleport = backs.get(playerName);
            if (locationToTeleport == null) {
                player.sendMessage("No where to teleport");
                return true;
            }
            player.teleport(locationToTeleport);
            return true;
        }

        // setHome
        if (cmd.getName().equalsIgnoreCase("setHome")) {
            Location home = player.getLocation();

            String x = String.valueOf(home.x());
            String y = String.valueOf(home.y());
            String z = String.valueOf(home.z());
            String locationString = x + "," + y + "," + z;

            config.addDefault(playerName + "-home", home);
            config.options().copyDefaults(true);
            MainPlugin.saveConfig();
            player.sendMessage("home set at: " + locationString);
            System.out.println(playerName + " set home to:" + locationString);

            homes.put(playerName, home);

            return true;
        }
        return true;
    }

    public void setLocations() {
        FileConfiguration config = MainPlugin.getConfig();
        OfflinePlayer[] players = Bukkit.getOfflinePlayers();
        for (OfflinePlayer offlinePlayer : players) {
            String playerName = offlinePlayer.getName();
            Location homeLocation = config.getLocation(playerName + "-home");
            homes.put(playerName, homeLocation);
        }
    }
}
