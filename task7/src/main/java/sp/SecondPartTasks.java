package sp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().flatMap(s -> {
            try {
                return Files.lines(Paths.get(s));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        })
                .filter(s -> s.contains(sequence))
                .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random random = new Random();
        return Stream.generate(() -> 1 - Math.floor(Math.hypot(random.nextDouble(), random.nextDouble())))
                .limit(1 << 16)
                .collect(Collectors.averagingDouble(x -> x));
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
      // Assuming that compositions is not empty
      return compositions.entrySet().stream()
                .max(Comparator.comparingInt(e -> e.getValue().stream().collect(Collectors.summingInt(String::length))))
                .get().getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
      return orders.stream().reduce(new HashMap<>(),
              (map, pair) -> {
                pair.entrySet().forEach(e -> map.put(e.getKey(), map.getOrDefault(e.getKey(), 0) + e.getValue()));
                return map;
              });
    }
}
