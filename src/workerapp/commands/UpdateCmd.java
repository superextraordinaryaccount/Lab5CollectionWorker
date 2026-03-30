package workerapp.commands;

import workerapp.managers.CollectionManager;
import workerapp.managers.InputManager;
import workerapp.workerclass.Worker;

public class UpdateCmd implements Commands {
    public final InputManager inputManager;
    private final CollectionManager collectionManager;

    public UpdateCmd(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String args) {
        if (args == null) {
            System.out.println("Ошибка: укажите id. Пример: update 5");
            return;
        }
        long id;
        try {
            id = Long.valueOf(args.trim());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: id должно быть числом типа long.");
            return;
        }
        Worker old = collectionManager.getById(id);
        if (old == null) {
            System.out.println("Элемент с id " + id + " не найден.");
            return;
        }
        Worker newWorker = inputManager.readWorker();
        // Сохраняеются старые id и creationDate
        newWorker.setId(old.getId());
        newWorker.setCreationDate(old.getCreationDate());
        // Обновляется в коллекции
        String key = collectionManager.getKeyById(id);
        collectionManager.getCollection().put(key, newWorker);
        System.out.println("Элемент обновлён.");
    }

    @Override
    public String getName() {
        return "update";
    }
}
