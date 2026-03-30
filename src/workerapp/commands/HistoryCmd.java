package workerapp.commands;

import java.util.Deque;

public class HistoryCmd implements Commands {
    private final Deque<String> history;

    public HistoryCmd(Deque<String> history) {
        this.history = history;
    }

    @Override
    public void execute(String args) {
        System.out.println("Последние команды:");
        for (String cmd : history) {
            System.out.println("  " + cmd);
        }
    }

    @Override
    public String getName() {
        return "history";
    }
}
