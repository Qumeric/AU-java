package info.qumeric.hw;

public class Message {
    String topic;
    String text;

    public Message(String _topic, String _text) {
        topic = _topic;
        text = _text;
    }

    public String getTopic() {
        return topic;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Topic: " + topic + "\n" + "Text: " + text;
    }
}
