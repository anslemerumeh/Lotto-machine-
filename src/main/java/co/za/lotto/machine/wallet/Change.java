package co.za.lotto.machine.wallet;

public class Change {
    private int r1Coins;
    private int r2Coins;
    private int r5Coins;
    private int r10Notes;
    private int r50Notes;
    private int r100Notes;

    public Change(int r1Coins, int r2Coins, int r5Coins, int r10Notes, int r50Notes, int r100Notes) {
        this.r1Coins = r1Coins;
        this.r2Coins = r2Coins;
        this.r5Coins = r5Coins;
        this.r10Notes = r10Notes;
        this.r50Notes = r50Notes;
        this.r100Notes = r100Notes;
    }

    public int getR1Coins() {
        return r1Coins;
    }

    public int getR2Coins() {
        return r2Coins;
    }

    public int getR5Coins() {
        return r5Coins;
    }

    public int getR10Notes() {
        return r10Notes;
    }

    public int getR50Notes() {
        return r50Notes;
    }

    public int getR100Notes() {
        return r100Notes;
    }
}

