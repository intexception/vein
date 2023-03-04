package best.nquantum.veinpvp.data;

public class PlayerStats {
    private int level;
    private int xp;
    private int kills;
    private int deaths;
    private double kdRatio;
    private int killStreak;


    public int getXp() {
        return xp;
    }

    public PlayerStats setXp(int xp) {
        this.xp = xp;
        return this;
    }

    public PlayerStats giveXp(int amount) {
        this.xp += amount;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public PlayerStats setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getKills() {
        return kills;
    }

    public PlayerStats setKills(int kills) {
        this.kills = kills;
        return this;
    }

    public int getDeaths() {
        return deaths;
    }

    public PlayerStats setDeaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public double getKdRatio() {
        return kdRatio;
    }

    public PlayerStats setKdRatio(double kdRatio) {
        this.kdRatio = kdRatio;
        return this;
    }

    public int getKillStreak() {
        return killStreak;
    }

    public PlayerStats setKillStreak(int killStreak) {
        this.killStreak = killStreak;
        return this;
    }
}
