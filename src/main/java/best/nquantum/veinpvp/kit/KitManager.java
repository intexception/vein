package best.nquantum.veinpvp.kit;

import best.nquantum.veinpvp.kit.impl.BasicKit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KitManager {
    private final List<Kit> kits = new ArrayList<>();

    public KitManager() {
        initializeKits();
    }

    public void initializeKits() {
        kits.addAll(Arrays.asList(new BasicKit()));
    }

    public List<Kit> getKits() {
        return kits;
    }
}
