package workerapp.commands;

import workerapp.managers.CollectionManager;
import workerapp.workerclass.Worker;

public class MaxByEndDateCmd implements Commands {
    private final CollectionManager collectionManager;

    public MaxByEndDateCmd(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) {
        Worker maxWorker = null;
        java.time.ZonedDateTime maxDate = null;
        for (Worker worker : collectionManager.getCollection().values()) {
            if (worker.getEndDate() != null) {
                if (maxDate == null || worker.getEndDate().isAfter(maxDate)) {
                    maxDate = worker.getEndDate();
                    maxWorker = worker;
                }
            }
        }

        if (maxWorker != null) {
            System.out.println("Элемент с максимальным endDate: " + maxWorker);
        } else {
            System.out.println("Нет элемента, в котором указана EndDate.");
        }
    }

    @Override
    public String getName() {
        return "max_by_end_date";
    }
}
