package com.yelloelefant.exampleplugin;

import com.yelloelefant.exampleplugin.commands.Commands;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Commands commands = new Commands();
        // Plugin startup logic
        System.out.println("my first plugin has started");
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("home").setExecutor(commands);
        getCommand("setHome").setExecutor(commands);
        getCommand("back").setExecutor(commands);

        saveDefaultConfig();

        // commands.setLocations();

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        System.out.println("a player has joined the server");
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        System.out.println(event.getItemDrop().getEntityId());
    }

}
