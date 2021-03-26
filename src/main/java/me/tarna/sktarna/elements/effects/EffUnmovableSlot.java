package me.tarna.sktarna.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class EffUnmovableSlot extends Effect {

    static {
        Skript.registerEffect(EffUnmovableSlot.class, "set slot[s] %integers% of %inventory% to be (un|in)movable");
    }

    private Expression<Integer[]> slots;
    private Expression<Inventory> inv;
    private Integer[][] s;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        slots = (Expression<Integer[]>) exprs[0];
        inv = (Expression<Inventory>) exprs[1];

        return true;
    }

    @Override
    protected void execute(Event e) {
        this.s = slots.getArray(e);
    }

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    public void OnInventoryClick(InventoryClickEvent e) {

    }

}
