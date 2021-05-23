package br.com.infran.newproxy.commands;

import br.com.infran.newproxy.ProxyPlugin;
import lombok.AllArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class StaffChatCommand {

    private final LuckPerms luckPerms = LuckPermsProvider.get();

    @Command(
            name = "staffchat",
            aliases = {"staffchat", "sc", "s"},
            permission = "hades.staff"
    )
    public void handleStaffChat(Context<CommandSender> sender, String[] args) {
        final String name = sender.getSender().getName();
        final Player player = ProxyPlugin.getInstance().getServer().getPlayer(name);

        final StringBuilder stringBuilder = new StringBuilder();

        for(String arg : args) {
            stringBuilder.append(arg).append(" ");
        }

        final String message = stringBuilder.toString().trim();

        for (User user : luckPerms.getUserManager().getLoadedUsers()) {
            if (user == null) return;

            CachedMetaData metaData = this.luckPerms.getPlayerAdapter(Player.class).getMetaData(player);

            player.sendMessage("§d§l[SC] " + metaData.getPrefix().replaceAll("&", "§") + name + "§7: §f" + message);
        }
    }
}