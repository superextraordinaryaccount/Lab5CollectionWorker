package workerapp.commands;

import workerapp.managers.CollectionManager;

/**
 * Класс, ответственный за очистку коллекции
 */
public class ClearCmd implements Commands {
    private final CollectionManager collectionManager;

    public ClearCmd(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) {
        collectionManager.clear();
        System.out.println("Коллекция очищена.");
    }

    @Override
    public String getName() {
        return "clear";
    }

}
