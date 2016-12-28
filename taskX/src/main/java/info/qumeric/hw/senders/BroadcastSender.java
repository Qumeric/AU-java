package info.qumeric.hw.senders;

import info.qumeric.hw.Coordinator;

public interface BroadcastSender {
    /**
     * Get topic of messages sent by a <code>BroadcastSender</code>
     */
    String getTopic();

    /**
     * Set receiver of messages sent by a <code>BroadcastSender</code>
     */
    void setCoordinator(Coordinator coordinator);

    /**
     * Start message sending
     */
    void run();
}
