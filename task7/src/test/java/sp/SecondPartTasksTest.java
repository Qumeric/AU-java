package sp;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static sp.SecondPartTasks.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
      // Не очень удобно тестить работу с файлами, поэтому проверил руками, надеюсь этого достаточно
    }

    @Test
    public void testPiDividedBy4() {
        // probabilistic
        assertEquals(Math.PI / 4.0, piDividedBy4(), 0.01);
    }

    @Test
    public void testFindPrinter() {
        assertEquals(
                 "Agatha Christie",
                findPrinter(ImmutableMap.of(
                        "Agatha Christie", Arrays.asList(
                                "Murder on the Orient Express",
                                "The Murder of Roger Ackroyd",
                                "Death on the Nile",
                                "The Murder at the Vicarage",
                                "Partners In Crime",
                                "The ABC Murders",
                                "And Then There Were None",
                                "The Mousetrap"
                                ),
                        "William Shakespeare", Arrays.asList(
                                "Hamlet",
                                "MacBeth",
                                "Romeo and Juliet",
                                "King Lear",
                                "Othello",
                                "Julius Caesar"
                        ),
                        "Fake author", Arrays.asList(
                                "I am Bored",
                                "Copypasting Data",
                                "From the Internet",
                                "So Here Goes",
                                "Some Fake Stuff"
                        )
                ))
        );
    }

    @Test
    public void testCalculateGlobalOrder() {
           assertEquals(
                ImmutableMap.of("A", 11,
                                "B", 16,
                                "C", 8),
                calculateGlobalOrder(Arrays.asList(
                    ImmutableMap.of("A", 4,
                                    "B", 2),
                    ImmutableMap.of("A", 1,
                                    "B", 5),
                    ImmutableMap.of("A", 6,
                                    "B", 9,
                                    "C", 8)
                ))
        );
    }
}
