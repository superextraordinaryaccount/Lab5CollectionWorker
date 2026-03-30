package workerapp.commands;

import workerapp.managers.CollectionManager;
import workerapp.managers.InputManager;
import workerapp.workerclass.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemoveLowerCmd implements Commands {
    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public RemoveLowerCmd(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String args) {
        String prompt = "Введите пограничное значение зарплаты";
        long salaryThreshold = inputManager.readLong(prompt, 1, true);
        List<String> KeysToRemove = new ArrayList<>();
        for (Map.Entry<String, Worker> entry : collectionManager.getCollection().entrySet()) {
            if (entry.getValue().getSalary() < salaryThreshold) {
                KeysToRemove.add(entry.getKey());
            }
        }
        for (String keys : KeysToRemove) {
            collectionManager.getCollection().remove(keys);
        }
        System.out.println("Удалены элементы с зарплатой меньше чем " + salaryThreshold);
    }

    @Override
    public String getName() {
        return "remove_lower";
    }
}
