package info.qumeric.hw.senders;

import info.qumeric.hw.Coordinator;
import info.qumeric.hw.Message;

public class ExampleSender implements BroadcastSender {
    private static final String TOPIC = "Simple";
    private Coordinator coordinator;

    @Override
    public String getTopic() {
        return TOPIC;
    }

    @Override
    public void setCoordinator(Coordinator _coordinator) {
        coordinator = _coordinator;
    }

    @Override
    public void run() {
        Message message = new Message("Simple", "An example message from ExampleSender");
        coordinator.newMessage(message);
    }
}
