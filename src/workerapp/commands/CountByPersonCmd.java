package workerapp.commands;

import workerapp.managers.CollectionManager;
import workerapp.managers.InputManager;
import workerapp.workerclass.Person;

/**
 * Класс, ответственный за счёт работников по Person
 * (Если один человек увольнялся или устраивался на работу несколько раз)
 */
public class CountByPersonCmd implements Commands {
    public final InputManager inputManager;
    private final CollectionManager collectionManager;

    public CountByPersonCmd(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String args) {
        Person person = inputManager.readPerson();
        long count = collectionManager.getCollection().values().stream()
                .filter(worker -> worker.getPerson().equals(person)).count();
        System.out.println("Количество элементов с заданным Person: " + count);
    }

    @Override
    public String getName() {
        return "count_by_person";
    }

}
