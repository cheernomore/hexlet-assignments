package exercise;

class SafetyList {
    // BEGIN
    private int size = 10;
    private int[] list = new int[size];
    private int lastIndex = 0;

    public synchronized void add(int element) {

        if (lastIndex >= size) {
            size = size * 2;
            int[] newList = new int[size];

            for (int i = 0; i < lastIndex; i++) {
                newList[i] = list[i];
            }

            list = newList;
        }

        list[lastIndex] = element;
        lastIndex = lastIndex + 1;
    }

    public int get(int index) {
        if (index < 0 || index > lastIndex) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return list[index];
    }

    public int getSize() {
        return lastIndex;
    }
    // END
}
