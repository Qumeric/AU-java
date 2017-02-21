package info.qumeric.hw.receivers;

import java.util.HashSet;
import java.util.Set;

public class SimpleReceiver implements BroadcastReceiver {
    private static final Set<String> TOPICS = new HashSet<>();
    static {
        TOPICS.add("Simple");
    }
    @Override
    public Set<String> getTopics() {
        return TOPICS;
    }
}
