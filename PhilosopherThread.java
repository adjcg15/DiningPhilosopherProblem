import java.util.Random;

public class PhilosopherThread implements Runnable {
    private Fork leftFork;
    private Fork rightFork;
    private boolean takeForksFromLeftToRight;

    public PhilosopherThread(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.takeForksFromLeftToRight = true;
    }

    public boolean getTakeForksFromLeftToRight() {
        return this.takeForksFromLeftToRight;
    }

    public void setTakeForksFromLeftToRight(boolean takeForksFromLeftToRight) {
        this.takeForksFromLeftToRight = takeForksFromLeftToRight;
    }

    @Override 
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " se prepara para comer...");
            if(takeForksFromLeftToRight) {
                takeLeftFork();
                takeRightFork();
            } else {
                takeRightFork();
                takeLeftFork();
            }
            eat();
            releaseForks();
            think();
        }
    }

    private void think() {
        System.out.println(Thread.currentThread().getName() + " está pensando...");

        Random rand = new Random();
        int thinkingTime = rand.nextInt(3000 - 1000 + 1) + 1000;
        try {
            Thread.sleep(thinkingTime);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " no pudo pensar!!!");
        }
    }

    public void takeLeftFork() {
        synchronized (leftFork) {
            while (leftFork.isTaken()) {
                try {
                    leftFork.wait();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " no pudo tomar su tenedor izquierdo!!!");
                }
            }

            leftFork.take();
            System.out.println(Thread.currentThread().getName() + " toma su tenedor izquierdo...");
        }
    }

    public void takeRightFork() {
        synchronized (rightFork) {
            while (rightFork.isTaken()) {
                try {
                    rightFork.wait();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " no pudo tomar su tenedor derecho!!!");
                }
            }
            rightFork.take();
            System.out.println(Thread.currentThread().getName() + " toma su tenedor derecho...");
        }
    }

    private void eat() {
        System.out.println(Thread.currentThread().getName() + " está comiendo...");

        Random rand = new Random();
        int eatingTime = rand.nextInt(3000 - 1000 + 1) + 1000;
        try {
            Thread.sleep(eatingTime);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " no pudo comer!!!");
        }
    }

    private void releaseForks() {
        synchronized (leftFork) {
            leftFork.setFree();
            leftFork.notify();
        }
        synchronized (rightFork) {
            rightFork.setFree();
            rightFork.notify();
        }

        System.out.println(Thread.currentThread().getName() + " deja de comer...");
    }
}