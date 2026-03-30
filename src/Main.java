import workerapp.commands.InfoCmd;
import workerapp.managers.*;
import workerapp.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        // Список изначальных команд,не включая работу со скриптами и историей
        List<Commands> commands = Arrays.asList(
                new HelpCmd(),
                new InfoCmd(collectionManager),
                new InsertCmd(collectionManager,inputManager),
                new ShowCmd(collectionManager),
                new ClearCmd(collectionManager),
                new SaveCmd(collectionManager,fileManager),
                new ExitCmd(),
                new RemoveGreaterCmd(collectionManager,inputManager),
                new RemoveLowerCmd(collectionManager,inputManager),
                new MaxByEndDateCmd(collectionManager),
                new CountByPersonCmd(collectionManager,inputManager),
                new FilterLessThanEndDateCmd(collectionManager,inputManager),
                new UpdateCmd(collectionManager,inputManager)

        );

        CommandDisp executor = new CommandDisp(commands);

        System.out.println("Программа запущена. Введите help для списка команд.");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            executor.executeCmd(line);
        }
    }
}