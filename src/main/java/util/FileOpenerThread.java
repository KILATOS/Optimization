package util;

import java.io.IOException;

public class FileOpenerThread extends Thread {
    @Override
    public void run() {
        try {
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd /d D: && .\\newFile.txt\"");
            Thread.sleep(100);
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
