import java.util.concurrent.ArrayBlockingQueue;

class TextAnalyzer implements Runnable {
    private ArrayBlockingQueue<String> queue;
    private char symbol;

    public TextAnalyzer(ArrayBlockingQueue<String> queue, char symbol) {
        this.queue = queue;
        this.symbol = symbol;
    }

    @Override
    public void run() {
        int maxCount = 0;
        String maxText = null;

        try {
            while (true) {
                String text = queue.take();
                int count = countSymbolOccurrences(text, symbol);
                if (count > maxCount) {
                    maxCount = count;
                    maxText = text;
                }

                if (maxText != null && maxCount % 1000 == 0) {
                    System.out.println("Текущее максимальное количество\n '" + symbol + "': " + maxCount);
                    System.out.println("Текст с максимальным количеством '" + symbol + "': " + maxText);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int countSymbolOccurrences(String text, char symbol) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == symbol) {
                count++;
            }
        }
        return count;
    }
}