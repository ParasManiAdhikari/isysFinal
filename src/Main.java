import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "";
        classifier(fileName);
    }

    private static void classifier(String fileName) {
        List<MessageData> trainData;
        try {
            trainData = readFromFile("src/data/train.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MarkovClassifier classifier = new MarkovClassifier();
        classifier.train(trainData);
    }

    public static List<MessageData> readFromFile(String fileName) throws IOException {
        List<MessageData> messageList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                char phase = line.charAt(0);
                List<Character> messwerten = new ArrayList<>();
                for (int i = 2; i < line.length(); i += 2) {
                    messwerten.add(line.charAt(i));
                }

                MessageData messageData = new MessageData(phase, messwerten);
                messageList.add(messageData);
            }
        }

        return messageList;
    }
}