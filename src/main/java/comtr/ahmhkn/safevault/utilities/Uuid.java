package comtr.ahmhkn.safevault.utilities;

import comtr.ahmhkn.safevault.Main;
import org.bukkit.Bukkit;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Uuid {

    public static UUID getUUID(String playerName) {
        if (Main.getInstance().getServer().getOnlineMode())
            return Bukkit.getOfflinePlayer(playerName).getUniqueId();
        else
            return UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes(StandardCharsets.UTF_8));
    }

}
