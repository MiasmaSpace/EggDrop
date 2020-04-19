package me.rhapso.eggdrop;

import org.bukkit.plugin.java.JavaPlugin;

public class pluginMain extends JavaPlugin {

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable(){
        //Fired when the server enables the plugin
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }
}
