package workerapp.commands;

import workerapp.managers.CollectionManager;
import workerapp.workerclass.Worker;

import java.util.Hashtable;

public class ShowCmd implements Commands {
    private final CollectionManager collectionManager;

    public ShowCmd(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) {
        Hashtable<String, Worker> col = collectionManager.getCollection();
        if (col.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            col.forEach((key, worker) ->
                    System.out.println(key + ":" + worker));
        }
    }

    @Override
    public String getName() {
        return "show";
    }
}
