package com.yelloelefant.exampleplugin;

import com.yelloelefant.exampleplugin.commands.Commands;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin implements Listener {

    private static ExamplePlugin instance;

    public static ExamplePlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Commands commands = new Commands();
        // Plugin startup logic
        System.out.println("my first plugin has started");
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("home").setExecutor(commands);
        getCommand("setHome").setExecutor(commands);
        getCommand("back").setExecutor(commands);
        this.saveDefaultConfig();
        System.out.println(getConfig().toString());
        commands.setLocations();

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
