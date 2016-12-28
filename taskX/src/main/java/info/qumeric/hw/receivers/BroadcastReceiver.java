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
  void recieve(Message message);
}
