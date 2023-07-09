package comtr.ahmhkn.safevault;

import comtr.ahmhkn.safevault.commands.MainCommand;
import comtr.ahmhkn.safevault.listeners.CloseInventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        registerCommands();
        registerEvents();
    }


    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new CloseInventory(), plugin);
    }

    private void registerCommands() {
        getCommand("safevault").setExecutor(new MainCommand());
    }


    public static Main getInstance() {
        return plugin;
    }
}
