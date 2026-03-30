package workerapp.commands;

import workerapp.managers.CollectionManager;

/**
 * Команда info - выдаёт информацию о коллекции
 */
public class InfoCmd implements Commands {
    private final CollectionManager collectionManager;

    public InfoCmd(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) {
        System.out.printf("""
                        Тип коллекции: %s%n
                        Дата инициализации: %s%n
                        Количество элементов: %s%n
                        """, collectionManager.getCollection().getClass().getSimpleName(),
                collectionManager.getInitializationDate(),
                collectionManager.getCollection().size());

    }

    @Override
    public String getName() {
        return "info";
    }


}
