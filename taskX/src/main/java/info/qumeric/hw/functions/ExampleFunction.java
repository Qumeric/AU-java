package info.qumeric.hw.functions;

import info.qumeric.hw.Message;

import java.util.HashSet;
import java.util.Set;

public class ExampleFunction implements Function {
    private static final Set<String> TOPICS = new HashSet<>();
    static {
        TOPICS.add("Simple");
    }

    @Override
    public Set<String> getTopics() {
        return TOPICS;
    }

    @Override
    public Message apply(Message message) {
        return null;
    }
}
