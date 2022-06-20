import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

    /*Задание 2#
    Дан текстовый файл file.txt, необходимо считать файл в список объектов User и создать новый файл user.json.

    Предполагаем, что каждая строка содержит одинаковое количество "колонок", разделенный пробелом.*/

public class Exercise2 {
    private static final String FILE =
            "C:\\Users\\deagl\\IdeaProject\\java-homework10\\resources\\file2.txt";
    private static final String RESULT_FILE =
            "C:\\Users\\deagl\\IdeaProject\\java-homework10\\resources\\file2.json";


    public void readAndCreateFile() {
        File file = new File(FILE);
        ArrayList<User> users = new ArrayList<>();
        if (!file.exists()) {
            throw new RuntimeException("Файл " + file.getName() + " не существует!");
        }

        try (Stream<String> stream = Files.lines(Paths.get(FILE))) {
            stream.skip(1).forEach(s -> {
                    users.add(new User(s.split(" ")[0], Integer.parseInt(s.split(" ")[1])));
            });
        } catch (IOException e) {
            System.err.println("Нельзя записать в открытый файл " + file.getName() + "!");
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(RESULT_FILE))) {
            String res = new Gson().toJson(users);
            fileOutputStream.write(res.getBytes(StandardCharsets.UTF_8));
            System.out.println("Файл " + RESULT_FILE + " успешно создан!");
        } catch (IOException e) {
            System.err.println("Нельзя записать в открытый файл " + file.getName() + "!");
        }
    }

    class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
