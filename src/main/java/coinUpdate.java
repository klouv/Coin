import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class coinUpdate implements CommandExecutor {

    private ConfigFile valuesConfig;
    private Main Main;

    public coinUpdate(ConfigFile config, Main main) {
        this.valuesConfig = config;
        this.Main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 1) {
            sender.sendMessage("you need 2 arg: block_name, value");
            return true;
        }
        if (args.length >= 3 ){
            sender.sendMessage("you need 2 arg: block_name, value");
        }
        if (args[0].equals("COAL_BLOCK")) {
            String block_name = args[0].toString();
            String value = args[1].toString();
            valuesConfig.set(block_name, value);
        }
        if (args[0].equals("IRON_BLOCK")) {
            String block_name = args[0].toString();
            String value = args[1].toString();
            valuesConfig.set(block_name, value);
        }
        if (args[0].equals("DIAMOND_BLOCK")) {
            String block_name = args[0].toString();
            String value = args[1].toString();
            valuesConfig.set(block_name, value);
            sender.sendMessage("başarılı");
        }

        valuesConfig.save();

//EKLENECEKLER: configde olmayan bir block_name girilirse bunudeğeri ile beraber config e ekleyecek
//              veya istenmeyen block_name in  value 0 a eşitlenip geçersiz hale getirilecek
        return true;
    }
}
