package info.qumeric.hw.filters;

import info.qumeric.hw.Message;

import java.util.HashSet;
import java.util.Set;

public class SimpleFilter implements Filter {
    private static final Set<String> TOPICS = new HashSet<>();
    static {
        TOPICS.add("Simple");
        TOPICS.add("Easy");
        TOPICS.add("Light");
    }
    @Override
    public Set<String> getTopics() {
        return TOPICS;
    }

    @Override
    public boolean filter(Message message) {
        return TOPICS.contains(message.getTopic());
    }
}
