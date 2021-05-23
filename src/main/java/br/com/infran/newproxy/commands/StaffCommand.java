package br.com.infran.newproxy.commands;


import br.com.infran.newproxy.ProxyPlugin;
import lombok.AllArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedDataManager;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class StaffCommand {

    private final LuckPerms luckPerms = LuckPermsProvider.get();

    @Command(
            name = "staff",
            aliases = {"staffs"},
            permission = "hades.staff"
    )
    public void handleStaffChat(Context<CommandSender> context) {
        final QueryOptions queryOptions = QueryOptions.defaultContextualOptions();

        StringBuilder message = new StringBuilder();

        int count = 0;

        for (User user : luckPerms.getUserManager().getLoadedUsers()) {
            final CachedDataManager cachedData = user.getCachedData();
            final CachedPermissionData permissionData = cachedData.getPermissionData(queryOptions);

            if (!permissionData.checkPermission("hades.staff").asBoolean()) continue;

            final Player player = ProxyPlugin.getInstance().getServer().getPlayer(user.getUniqueId());

            final CachedMetaData metaData = this.luckPerms.getPlayerAdapter(Player.class).getMetaData(player);

            count++;

            if (count == 1) {
                message.append("\n")
                        .append(" §eMembros da equipe conectados:")
                        .append("\n");
            }

            message.append("  ")
                    .append("§8➟ " + metaData.getPrefix().replaceAll("&", "§"))
                    .append(player.getName()).append(" ")
                    .append("\n");
        }
        if (count == 0) {
            message.append("\n")
                    .append("§cNenhum membro da equipe disponível :(")
                    .append("\n");
        }
        message.append(" ");

        context.sendMessage(message.toString());
    }
}