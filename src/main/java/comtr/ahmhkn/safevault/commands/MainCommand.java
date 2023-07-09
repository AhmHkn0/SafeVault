package comtr.ahmhkn.safevault.commands;

import comtr.ahmhkn.safevault.data.Vaults;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("safevault")) {
            if (sender instanceof Player) {
                Player p = ((Player) sender);
                if (args.length > 0) {
                    if (args.length == 1) {
                        String vaultNumberstr = args[0];
                        try {
                           int vaultNumer = Integer.parseInt(vaultNumberstr);
                           Inventory vault = Vaults.loadVault(p.getName(), vaultNumer);
                           p.openInventory(vault);
                           Vaults.vaultInventories.add(vault);
                           Vaults.openedVaults.put(p, vaultNumer);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            p.sendMessage("§cOnly integers");
                        }
                    }
                }
            }
        }
        return false;
    }
}
