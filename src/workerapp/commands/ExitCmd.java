package workerapp.commands;

/**
 * Класс, ответственный за выход из программы
 */
public class ExitCmd implements Commands {
    @Override
    public void execute(String args) {
        System.out.println("Завершение программы.");
        System.exit(0);
    }

    @Override
    public String getName() {
        return "exit";
    }
}
