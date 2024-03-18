public class Main {
    public static void main(String[] args) {
        Fork fork1 = new Fork(1);
        Fork fork2 = new Fork(2);
        Fork fork3 = new Fork(3);
        Fork fork4 = new Fork(4);
        Fork fork5 = new Fork(5);

        PhilosopherThread p3 = new PhilosopherThread(fork3, fork4);
        p3.setTakeForksFromLeftToRight(false);

        Thread philosopher1 = new Thread(new PhilosopherThread(fork1, fork2), "Filósofo 1");
        Thread philosopher2 = new Thread(new PhilosopherThread(fork2, fork3), "Filósofo 2");
        Thread philosopher3 = new Thread(p3, "Filósofo 3");
        Thread philosopher4 = new Thread(new PhilosopherThread(fork4, fork5), "Filósofo 4");
        Thread philosopher5 = new Thread(new PhilosopherThread(fork5, fork1), "Filósofo 5");

        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();
    }
}
