package exercise;

// BEGIN
public class ListThread extends Thread {
    private SafetyList safetyList;

    ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++ ) {
            try {
                Thread.sleep(1);
                safetyList.add(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
// END
