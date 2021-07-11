import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class blockBreakEvent implements Listener {

    private ConfigFile valueConfig;
    private ConfigFile config;
    private Main Main;
    private Map<UUID, Integer>  stackStorage;
    private Map<String, Integer> valueStorage;

    public blockBreakEvent(ConfigFile config, ConfigFile valueConfig, Main main) {
        this.config = config;
        this.valueConfig = valueConfig;
        this.Main = main;
        this.stackStorage = main.stackStorage;
        this.valueStorage = main.valueStorage;
    }

    public void onBreak(BlockBreakEvent e) {

        String block = e.getBlock().getWorld().getName();
        e.getPlayer().sendMessage(block);
        if(valueStorage.containsKey(block)) {
            int stackplus = valueStorage.get(block);
            int stack = stackStorage.get(e.getPlayer().getUniqueId());
            stack = stack + stackplus;
            if(stack >= 10) {
                stack = stack - 10;
                e.getPlayer().sendMessage("you did task");
            }
            stackStorage.put(e.getPlayer().getUniqueId(), stack);
            ItemStack item = new ItemStack(Material.GOLD_INGOT);
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), item);
            e.getPlayer().getInventory().addItem(item);
        }


    }


}
