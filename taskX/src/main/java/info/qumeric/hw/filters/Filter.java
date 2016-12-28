package info.qumeric.hw.filters;

import info.qumeric.hw.Message;

import java.util.Set;

public interface Filter {
    /**
     * Get allowed topics
     */
    Set<String> getTopics();

    /**
     * Check if given message can pass
     */
    boolean filter(Message message);
}
