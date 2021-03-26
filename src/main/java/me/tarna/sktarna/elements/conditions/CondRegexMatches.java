package me.tarna.sktarna.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.tarna.sktarna.lib.Regex;
import org.bukkit.event.Event;

import java.util.regex.Pattern;

public class CondRegexMatches extends Condition {

    static {
        Skript.registerCondition(CondRegexMatches.class,
                "%string% matches [regex] %string%",
                "%strings% [regex] does(n't| not) match %string%");
    }

    private Expression<String> message;
    private Expression<?> regex;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        message = (Expression<String>) exprs[0];
        regex = exprs[1].getConvertedExpression(Object.class);
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public boolean check(Event e) {
        final Pattern p = Regex.getInstance().getPattern(regex.getSingle(e));
        if (p == null)
            return false;
        return message.check(e, from -> p.matcher(from).matches(), isNegated());
    }

    @Override
    public String toString(Event e, boolean debug) {
        return message + " regex match " + regex;
    }

}
