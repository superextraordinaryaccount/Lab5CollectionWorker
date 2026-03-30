package workerapp.commands;

import workerapp.managers.CollectionManager;
import workerapp.managers.FileManager;

import java.io.IOException;

public class SaveCmd implements Commands {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public SaveCmd(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String args) {
        try {
            fileManager.save(collectionManager.getCollection());
            System.out.println("Коллекция сохранена в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "save";
    }
}
