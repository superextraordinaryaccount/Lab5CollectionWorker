package workerapp.managers;
import workerapp.workerclass.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Управляет коллекцией Hashtable, хранит дату инициализации, генерирует id.
 */
public class CollectionManager {
    private final Hashtable<String, Worker> collection = new Hashtable<>();
    private final LocalDate initializationDate = LocalDate.now();
    private long currentMaxId = 0;

    /**
     * Возвращает коллекцию.
     */
    public Hashtable<String, Worker> getCollection() {
        return collection;
    }

    /**
     * Возвращает дату инициализации.
     */
    public LocalDate getInitializationDate() {
        return initializationDate;
    }

    /**
     * Генерирует новый уникальный id (больше всех существующих).
     */
    public long generateId() {
        return ++currentMaxId;
    }

    /**
     * Обновляет максимальный id на основе текущей коллекции.
     * Вызывается после загрузки.
     */
    public void updateMaxId() {

       currentMaxId = collection.values().isEmpty() ? 0:
               collection.values().stream().mapToLong(Worker::getId).max().getAsLong();

    }


    /**
     * Добавляет элемент по ключу, устанавливая ему сгенерированный id и текущую дату.
     */
    public void add(String key, Worker worker) {
        worker.setId(generateId());
        worker.setCreationDate(LocalDate.now());
        collection.put(key, worker);
    }

    /**
     * Обновляет существующий элемент по id (поля, кроме id и creationDate).
     */
    public boolean updateById(long id, Worker newWorker) {
        for (String key : collection.keySet()) {
            if (collection.get(key).getId() == id) {
                Worker old = collection.get(key);
                old.setName(newWorker.getName());
                old.setCoordinates(newWorker.getCoordinates());
                old.setSalary(newWorker.getSalary());
                old.setStartDate(newWorker.getStartDate());
                old.setEndDate(newWorker.getEndDate());
                old.setPosition(newWorker.getPosition());
                old.setPerson(newWorker.getPerson());
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет элемент по ключу.
     */
    public void remove(String key) {
        collection.remove(key);
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() {
        collection.clear();
        currentMaxId = 0;
    }

    /**
     * Возвращает элемент по id или null.
     */
    public Worker getById(long id) {
        return collection.values().stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает ключ по id.
     */
    public String getKeyById(long id) {
        return collection.entrySet().stream()
                .filter(e -> e.getValue().getId() == id)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
