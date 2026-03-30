package workerapp.commands;

import workerapp.managers.CollectionManager;
import workerapp.managers.InputManager;

/**
 * Класс команды, удаляющей элемент по ключу
 */
public class RemoveKeyCmd implements Commands {
    private final CollectionManager collectionManager;

    public RemoveKeyCmd(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) {
        if (args == null) {
            System.out.println("Ошибка: укажите ключ. Пример: remove_key key123");
            return;
        }
        String key = args.trim();
        if (collectionManager.getCollection().remove(key) != null) {
            System.out.println("Элемент удалён.");
        } else {
            System.out.println("Ключ не найден.");
        }

    }

    public String getName() {
        return "remove_key";
    }
}
