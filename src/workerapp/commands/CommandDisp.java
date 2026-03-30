package workerapp.commands;

import java.util.*;

/**
 * Класс, ответственный за запуск и историю команд
 */
public class CommandDisp {
    private final Map<String, Commands> commands = new HashMap<>();
    private final Deque<String> history = new ArrayDeque<>(12);
    private final Deque<Scanner> scannerStack = new ArrayDeque<>();

    public CommandDisp(List<Commands> startcmd) {
        for (Commands cmd : startcmd) {
            record(cmd);
        }
        record(new ExecuteScriptCmd(this));
        record(new HistoryCmd(history));
    }

    public void record(Commands cmd) {
        commands.put(cmd.getName(), cmd);
    }

    public void executeCmd(String line) {
        if (line.trim().isEmpty()) return;
        String[] parts = line.trim().split("\\s+", 2);
        String declaredCommand = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : null;

        history.addFirst(declaredCommand);
        if (history.size() > 12) {
            history.removeLast();
        }

        Commands cmd = commands.get(declaredCommand);
        if (cmd == null) {
            System.out.println("Неизвестная команда. Введите help для справки.");
            return;
        }
        try {
            cmd.execute(argument);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения команды: " + e.getMessage());
        }
    }
}
