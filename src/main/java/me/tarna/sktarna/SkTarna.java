package me.tarna.sktarna;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.google.inject.Injector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class SkTarna extends JavaPlugin {

    public static SkTarna instance;
    private static SkriptAddon addon;
    private static Boolean loaded = false;

    @Override
    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);

        if(!loaded) {
            try {
                addon.loadClasses("me.tarna.sktarna", "elements");
            }catch (IOException e) {
                Bukkit.getLogger().warning("Error loading SkTarna classes");
                e.printStackTrace();
            }
        }

        instance = this;
        loaded = true;

        getLogger().info("SkTarna Enabled!");

    }

    @Override
    public void onDisable() {

        instance = null;
        getLogger().info("SkTarna Disabled!");

    }
}
