package workerapp.commands;

import workerapp.managers.CollectionManager;
import workerapp.managers.InputManager;
import workerapp.workerclass.Worker;

/**
 * Класс, ответственный за добавление нового работника
 */
public class InsertCmd implements Commands {
    public final InputManager inputManager;
    private final CollectionManager collectionManager;

    public InsertCmd(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String args) {
        if (args == null) {
            System.out.println("Ошибка: укажите ключ. Пример: insert key123");
            return;
        }
        String key = args.trim();
        if (collectionManager.getCollection().containsKey(key)) {
            System.out.println("Ошибка: ключ уже существует.");
            return;
        }
        Worker worker = inputManager.readWorker();
        collectionManager.add(key, worker);
        System.out.println("Элемент добавлен с id " + worker.getId());
    }

    @Override
    public String getName() {
        return "insert";
    }
}
