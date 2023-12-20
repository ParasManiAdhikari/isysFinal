import java.util.List;

public class MessageData {
    private char phase;
    private List<Character> messwerten;

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