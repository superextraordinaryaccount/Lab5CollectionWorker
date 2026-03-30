package workerapp.commands;

/**
 * Интерфейс команд
 */
public interface Commands {
    void execute(String args) throws Exception;

    String getName();
}
