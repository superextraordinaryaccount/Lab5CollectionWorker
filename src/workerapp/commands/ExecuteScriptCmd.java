package workerapp.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Класс, ответственный за работу скриптов, защиту от рекурсии
 */
public class ExecuteScriptCmd implements Commands {
    private static final int MAX_SCRIPT_DEPTH = 10;
    private final Deque<Scanner> scannerStack = new ArrayDeque<>();
    private final Deque<String> scriptFiles = new ArrayDeque<>();
    private final CommandDisp executor;


    public ExecuteScriptCmd(CommandDisp executor) {
        this.executor = executor;
    }


    @Override
    public void execute(String fileName) {
        if (fileName == null) {
            System.out.println("Ошибка: укажите имя файла.");
            return;
        }

        if (scannerStack.size() >= MAX_SCRIPT_DEPTH) {
            System.out.println("Ошибка: превышена максимальная глубина рекурсии для исполнения скриптов.");
            return;
        }

        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            System.out.println("Ошибка: файл не существует или недоступен для чтения.");
        }

        String canPath;
        try {
            canPath = file.getCanonicalPath();
        } catch (IOException e) {
            System.err.println("Ошибка при получении канонического пути: " + e.getMessage());
            return;
        }

        if (scriptFiles.contains(canPath)) {
            System.out.println("Ошибка: файл " + canPath + " уже выполняется (рекурсивный вызов).");
            return;
        }

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
            scannerStack.addFirst(fileScanner);
            scriptFiles.addFirst(canPath);
            System.out.println("Выполнение скрипта: " + fileName);
            while (fileScanner.hasNextLine()) {
                String cmdLine = fileScanner.nextLine();
                System.out.println("> " + cmdLine);
                executor.executeCmd(cmdLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении скрипта: " + e.getMessage());
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
            if (!scannerStack.isEmpty() && scannerStack.peek() == fileScanner) {
                scannerStack.pop();
            }
            if (!scriptFiles.isEmpty() && scriptFiles.peek().equals(canPath)) {
                scriptFiles.pop();
            }
        }

    }

    @Override
    public String getName() {
        return "execute_script";
    }
}
