package info.qumeric.hw.functions;

import info.qumeric.hw.Message;

import java.util.Set;

public interface Function {
    /**
     * Get allowed topics
     */
    Set<String> getTopics();

    /**
     * Evaluate function on given message
     */
    Message apply(Message message);
}
