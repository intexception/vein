package best.nquantum.veinpvp.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherRemover implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWeatherChange(WeatherChangeEvent e) {
        if(e.toWeatherState()) {
            e.setCancelled(true);
            e.getWorld().setWeatherDuration(0);
            e.getWorld().setThundering(false);
        }
    }
}
