package comtr.ahmhkn.safevault.data;

import comtr.ahmhkn.safevault.Main;
import comtr.ahmhkn.safevault.utilities.Serialize;
import comtr.ahmhkn.safevault.utilities.Uuid;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class Vaults {

    public static HashSet<Inventory> vaultInventories = new HashSet<>();
    public static ConcurrentHashMap<Player, Integer> openedVaults = new ConcurrentHashMap<>();

    public static void saveVault(String playerName, Inventory inventory, int vaultNumber){
        StringBuilder items = new StringBuilder();
        for (int slot = 0 ; slot < inventory.getSize() ; slot++) {
            if (inventory.getItem(slot) != null) {
                ItemStack item = inventory.getItem(slot);
                String itemStackString = "";

                try {
                    itemStackString = Serialize.serializeItem(item);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (slot == inventory.getSize()-1){
                    items.append(itemStackString);
                } else {
                    items.append(itemStackString).append("##");
                }
            } else {
                if (slot == inventory.getSize()-1){
                    items.append("null");
                } else {
                    items.append("null").append("##");
                }
            }
        }


        String finalItems = items.toString();
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            File f = new File(Main.getInstance().getDataFolder()+"/database/"+Uuid.getUUID(playerName)+".yml");
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
            yml.set("vault"+vaultNumber, finalItems);
            try {
                yml.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Inventory loadVault(String playerName, int vaultNumber) {
        Inventory inventory = Main.getInstance().getServer().createInventory(null, 54, "§5SafeVault");
        File f = new File(Main.getInstance().getDataFolder()+"/database/"+ Uuid.getUUID(playerName) +".yml");
        if (!f.exists())
            return inventory;
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        String input = yml.getString("vault"+vaultNumber, "null");
        if (input.equalsIgnoreCase("null"))
            return inventory;

        for (int slot = 0; slot < inventory.getSize(); slot++) {

            String[] parts = input.split("##");

            String itemString = parts[slot];

            if (parts[slot].equalsIgnoreCase("null")) {
                inventory.setItem(slot, null);
            } else {
                ItemStack itemtoreturn = null;
                try {
                    itemtoreturn = Serialize.deserializeItem(itemString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                inventory.setItem(slot, itemtoreturn);
            }
        }
        return inventory;
    }
}
