package br.com.infran.newproxy;

import br.com.infran.newproxy.commands.StaffChatCommand;
import br.com.infran.newproxy.commands.StaffCommand;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import org.bukkit.plugin.java.JavaPlugin;

public class ProxyPlugin extends JavaPlugin {
    private static ProxyPlugin i;

    public ProxyPlugin() {
        i = this;
    }

    public static ProxyPlugin getInstance() {
        return i;
    }

    @Override
    public void onEnable() {
        final BukkitFrame frame = new BukkitFrame(this);
        frame.registerCommands(
                new StaffChatCommand(),
                new StaffCommand()
        );
    }

}
