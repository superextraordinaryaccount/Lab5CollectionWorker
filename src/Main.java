import workerapp.managers.*;
import workerapp.commands.CommandExecutor;

import java.util.Scanner;

/**
 * Главный класс.
 * Читает имя файла из переменной окружения WORKER_CSV, загружает коллекцию,
 * запускает интерактивный режим.
 */
public class Main {
    public static void main(String[] args) {
        String filename = System.getenv("WORKER_CSV");
        FileManager fileManager = new FileManager(filename);
        CollectionManager collectionManager = new CollectionManager();

        // Загрузка из файла
        collectionManager.getCollection().putAll(fileManager.load());
        collectionManager.updateMaxId();

        Scanner scanner = new Scanner(System.in);
        InputManager inputManager = new InputManager(scanner, System.out);
        CommandExecutor executor = new CommandExecutor(collectionManager, fileManager, inputManager);

        System.out.println("Программа запущена. Введите help для списка команд.");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            executor.executeCommand(line);
        }
    }
}