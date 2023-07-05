package co.za.lotto.machine.lotto;

public class Lotto {
    private int numberOfBalls;
    private int ballsToDraw;
    private int numbersToDraw;

    public Lotto(int numberOfBalls, int ballsToDraw) {
        this.numberOfBalls = numberOfBalls;
        this.ballsToDraw = ballsToDraw;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public int getBallsToDraw() {
        return ballsToDraw;
    }

    public int getNumbersToDraw() {
        return numbersToDraw;
    }
}
