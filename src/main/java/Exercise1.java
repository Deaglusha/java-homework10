import java.io.*;

    /*Задание 1#
    Дан текстовый файл file.txt, который содержит список номеров телефоном (один на линии). Необходимо написать
    метод, который будет считывать файл и выводить в консоль все валидные номера телефонов.

    Предполагаем, что "валидный" номер телефона - это строка в одном из двух форматов:
    (xxx) xxx-xxxx или xxx-xxx-xxxx (х обозначает цифру).*/

public class Exercise1 {
    private static final String ABSOLUTE_FILE =
            "C:\\Users\\deagl\\IdeaProject\\java-homework10\\resources\\file.txt";

    public void readAndOutputFile() {
        File file = new File(ABSOLUTE_FILE);
        if (!file.exists()) {
            throw new RuntimeException("Файл " + file.getName() + " не существует!");
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ABSOLUTE_FILE));
            String line = bufferedReader.readLine();

            while (line != null) {
                if (line.matches("\\(\\d{3}\\)\\s\\d{3}-\\d{4}")) {
                    System.out.println(line);
                } else if (line.matches("\\d{3}-\\d{3}-\\d{4}")) {
                    System.out.println(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Нельзя записать в открытый файл " + file.getName() + "!");
        }
    }
}
