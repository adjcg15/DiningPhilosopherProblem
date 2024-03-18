public class Fork {
    private int number;
    private boolean isTaken;

    public Fork(int number) {
        this.number = number;
        this.isTaken = false;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isTaken() {
        return this.isTaken;
    }

    public void take() {
        this.isTaken = true;
    }

    public void setFree() {
        this.isTaken = false;
    }
}
