import java.util.concurrent.ArrayBlockingQueue;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queueA = new ArrayBlockingQueue<>(100);
        ArrayBlockingQueue<String> queueB = new ArrayBlockingQueue<>(100);
        ArrayBlockingQueue<String> queueC = new ArrayBlockingQueue<>(100);

        TextGenerator generator = new TextGenerator(queueA, queueB, queueC);
        TextAnalyzer analyzerA = new TextAnalyzer(queueA, 'a');
        TextAnalyzer analyzerB = new TextAnalyzer(queueB, 'b');
        TextAnalyzer analyzerC = new TextAnalyzer(queueC, 'c');

        Thread generatorThread = new Thread(generator);
        Thread analyzerThreadA = new Thread(analyzerA);
        Thread analyzerThreadB = new Thread(analyzerB);
        Thread analyzerThreadC = new Thread(analyzerC);

        generatorThread.start();
        analyzerThreadA.start();
        analyzerThreadB.start();
        analyzerThreadC.start();
    }
}