package comtr.ahmhkn.safevault.listeners;

import comtr.ahmhkn.safevault.data.Vaults;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CloseInventory implements Listener {

    @EventHandler
    public void closeInventory(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player) {
            if (Vaults.vaultInventories.contains(e.getInventory())) {
                Vaults.saveVault(e.getPlayer().getName(), e.getInventory(), Vaults.openedVaults.get((Player) e.getPlayer()));
                Vaults.vaultInventories.remove(e.getInventory());
                Vaults.openedVaults.remove((Player)e.getPlayer());
            }
        }
    }
}
