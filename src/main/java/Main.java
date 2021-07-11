import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    private ConfigFile config;
    private ConfigFile valuesConfig;
    public final Map<UUID, Integer> stackStorage = new HashMap<UUID, Integer>();
    public final Map<String , Integer> valueStorage = new HashMap<String, Integer>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new blockBreakEvent(config, valuesConfig, this),this);
        getCommand("coinupdate").setExecutor(new coinUpdate(valuesConfig, this));
        valuesConfig = new ConfigFile(this, "valuesConfig");
        config = new ConfigFile(this, "config");

        valuesConfig.getKeys(false).forEach((key) -> {
            String block_name = valuesConfig.getString(key);
            valueStorage.put(block_name, valuesConfig.getInt(key));
        });

        config.getKeys(false).forEach((key) -> {
            UUID uuid = UUID.fromString(key);
            if(config.get(key) == null){
                return;
            }
            stackStorage.put(uuid, config.getInt(key));
        });

    }


    @Override
    public void onDisable() {

        stackStorage.forEach((uuid, integer) -> {
            config.set(uuid.toString(), integer.toString());
        });

        valuesConfig.save();
        config.save();

    }

}
