import java.util.List;

public class MessageData {
    private final char phase;
    private final List<Character> messwerten;

    public MessageData(char phase, List<Character> messwerten) {
        this.phase = phase;
        this.messwerten = messwerten;
    }

    public char getPhase() {
        return phase;
    }

    public List<Character> getMesswerten() {
        return messwerten;
    }
}