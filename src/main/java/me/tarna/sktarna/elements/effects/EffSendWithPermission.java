package me.tarna.sktarna.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;

public class EffSendWithPermission extends Effect {

    static {
        Skript.registerEffect(EffSendWithPermission.class, "(send|message) %strings% to players with perm[ission] %string%");
    }

    private Expression<String> messages;
    private Expression<String> perm;

    @Override
    public boolean init(final Expression<?>[] vars, final int matchedPattern, final Kleenean isDelayed, final SkriptParser.ParseResult parser) {
        messages = (Expression<String>) vars[0];
        perm = (Expression<String>) vars[1];
        return true;
    }

    @Override
    public void execute(final Event e) {
        for(String m : messages.getArray(e)) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                final Expression<String> perm = this.perm;
                if(p.hasPermission((Permission) perm)) {
                    p.sendMessage(m);
                }
            }
        }
    }

    @Override
    public String toString(final Event e, final boolean debug) {
        return null;
    }

}
