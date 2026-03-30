package workerapp.commands;

import workerapp.managers.CollectionManager;
import workerapp.managers.InputManager;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class FilterLessThanEndDateCmd implements Commands {
    public final InputManager inputManager;
    private final CollectionManager collectionManager;

    public FilterLessThanEndDateCmd(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String args) {
        if (args == null) {
            System.out.println("Ошибка: укажите endDate.");
            return;
        }
        ZonedDateTime date;
        try {
            date = ZonedDateTime.parse(args);
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: неверный формат даты. Используйте ISO формат.");
            return;
        }
        collectionManager.getCollection().values().stream()
                .filter(worker -> worker.getEndDate() != null && worker.getEndDate().isBefore(date))
                .forEach(System.out::println);

    }

    @Override
    public String getName() {
        return "filter_less_than_end_date";
    }
}
