import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MarkovClassifier {
    private final Map<Character, List<Character>> transitionProbabilities;

    public MarkovClassifier() {
        this.transitionProbabilities = new HashMap<>();
    }

    public void train(List<MessageData> trainingData) {
        for (MessageData data : trainingData) {
            char phase = data.getPhase(); // U
            List<Character> messwerten = data.getMesswerten(); // h m n m h m m h n m n m h n n m n m n h

            // Add transitions to the map
            for (int i = 0; i < messwerten.size() - 1; i++) {
                char current = messwerten.get(i);
                char next = messwerten.get(i + 1);

                transitionProbabilities.computeIfAbsent(current, k -> new ArrayList<>()).add(next);
            }

            // Handle the transition from the last messwerten to the phase
            char lastMesswerten = messwerten.get(messwerten.size() - 1);
            transitionProbabilities.computeIfAbsent(lastMesswerten, k -> new ArrayList<>()).add(phase);
        }

    }

    public char predict(List<Character> messwerten) {
        if (messwerten.isEmpty()) {
            throw new IllegalArgumentException("Input messwerten sequence is empty");
        }

        char current = messwerten.get(0);

        for (int i = 1; i < messwerten.size(); i++) {
            List<Character> possibleNextStates = transitionProbabilities.get(current);

            if (possibleNextStates == null || possibleNextStates.isEmpty()) {
                // No information about next states, return a random phase
                return getRandomPhase();
            }

            current = possibleNextStates.get(new Random().nextInt(possibleNextStates.size()));
        }

        return current; // Predicted phase based on messwerten sequence
    }

    private char getRandomPhase() {
        // In case of uncertainty or no information, return a random phase
        List<Character> allPhases = new ArrayList<>(transitionProbabilities.keySet());
        return allPhases.get(new Random().nextInt(allPhases.size()));
    }
}
