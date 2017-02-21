package info.qumeric.hw.receivers;

import info.qumeric.hw.Message;

import java.util.Set;

public interface BroadcastReceiver {
  /**
   * Get list of interesting for this receiver topics
   */
  Set<String> getTopics();

  /**
   * Called by coordinator and indicates receiving of a message
   */
  default void recieve(Message message) {
    System.out.println(this.getClass().toString() + " have received the message:");
    System.out.println(message);
  }
}
