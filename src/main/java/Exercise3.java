import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

    /*Задание 3#
    Напишите метод, который будет подсчитывать частоту каждого слова в файле words.txt.

    Предпалагаем, что:
    1. words.txt содержит только слова в нижнем регистре, разделенные пробелом
    2. Каждое слово содержит только символы-буквы в нижнем регистре.
    3. Слова разделены одим или несколькими пробелами, либо переносом строки.*/

public class Exercise3 {
    private static final String ABSOLUTE_FILE =
            "C:\\Users\\deagl\\IdeaProject\\java-homework10\\resources\\words.txt";

    public void countWordsFile() {
        File file = new File(ABSOLUTE_FILE);
        if (!file.exists()) {
            throw new RuntimeException("Файл " + file.getName() + " не существует!");
        }

        try (Stream<String> stream = Files.lines(Paths.get(ABSOLUTE_FILE))) {
            Map<String, Long> wordFreq = stream.flatMap(s -> Stream.of(s.split(" ")))
                    .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k, v) -> k, LinkedHashMap::new));
            for (var entry : wordFreq.entrySet()) {
                System.out.print(entry.getKey() + " " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Нельзя записать в открытый файл " + file.getName() + "!");
        }
    }
}
