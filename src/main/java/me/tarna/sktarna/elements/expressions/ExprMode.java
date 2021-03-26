package me.tarna.sktarna.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

public class ExprMode extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprMode.class, Number.class, ExpressionType.PROPERTY, "mode of %numbers%");
    }

    Expression<Number> numbers;

    @Override
    protected Number[] get(Event event) {
        int maxValue=0, maxCount=0;
        Number[] numbersToCheck = numbers.getArray(event);
        for (Number value : numbersToCheck) {
            int count = 0;
            for (Number number : numbersToCheck) {
                if (number.equals(value)) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = (int) value;
            }
        }

        return new Number[] {maxValue};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        numbers = (Expression<Number>) expressions[0];
        return true;
    }
}
