import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

class TextGenerator implements Runnable {
    private ArrayBlockingQueue<String> queueA;
    private ArrayBlockingQueue<String> queueB;
    private ArrayBlockingQueue<String> queueC;

    public TextGenerator(ArrayBlockingQueue<String> queueA, ArrayBlockingQueue<String> queueB, ArrayBlockingQueue<String> queueC) {
        this.queueA = queueA;
        this.queueB = queueB;
        this.queueC = queueC;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            String text = generateText("abc", 100000);
            try {
                queueA.put(text);
                queueB.put(text);
                queueC.put(text);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}