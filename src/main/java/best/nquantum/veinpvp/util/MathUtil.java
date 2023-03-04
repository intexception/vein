package best.nquantum.veinpvp.util;

import org.bukkit.entity.Player;

public class MathUtil {
    public static int calculateLevel(int xp) {
        float requirementMultiplier = 1.0F;
        return (int) Math.ceil((-15.0 + Math.sqrt(225.0 + (60.0 * requirementMultiplier) * xp)) / 30.0);
    }
}
