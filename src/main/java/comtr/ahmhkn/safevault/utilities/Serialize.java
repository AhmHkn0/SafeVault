package comtr.ahmhkn.safevault.utilities;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Serialize {

    public static String serializeItem(ItemStack itemStack) {
        String itemStackString;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(itemStack);
            dataOutput.close();
            itemStackString = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
        return itemStackString;
    }


    public static ItemStack deserializeItem(String itemString) {

        ItemStack itemtoreturn;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(itemString));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            itemtoreturn = (ItemStack) dataInput.readObject();
            dataInput.close();
            return itemtoreturn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
