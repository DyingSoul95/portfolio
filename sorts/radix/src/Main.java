import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        double[] data = {170, 45, 75, -90, 802, -2, -24, 66, 23, 234, 3, 232, 44};
        String[] names = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};

        sortSales(data, names);
        printData(names, data);
    }

    public static void sortSales(double[] data, String[] names) {

        List<Double>[] positiveBuckets = new ArrayList[10];
        for (int i = 0; i < positiveBuckets.length; i++) {
            positiveBuckets[i] = new ArrayList<>();
        }

        List<String>[] positiveNames = new ArrayList[10];
        for (int i = 0; i < positiveNames.length; i++) {
            positiveNames[i] = new ArrayList<>();
        }

        List<Double>[] negativeBuckets = new ArrayList[10];
        for (int i = 0; i < negativeBuckets.length; i++) {
            negativeBuckets[i] = new ArrayList<>();
        }

        List<String>[] negativeNames = new ArrayList[10];
        for (int i = 0; i < negativeNames.length; i++) {
            negativeNames[i] = new ArrayList<>();
        }

        // sort
        boolean flag = false;
        double tmp = -1, divisor = 1;
        while (!flag) {
            flag = true;

            for (int i = 0; i < data.length; i++) {
                tmp = data[i] / divisor;
                int redix = (int) (tmp % 10);
                if (tmp < 0) {
                    redix *= -1;
                    negativeBuckets[redix].add(data[i]);
                    negativeNames[redix].add(names[i]);
                } else {
                    positiveBuckets[redix].add(data[i]);
                    positiveNames[redix].add(names[i]);
                }
                if (flag && tmp > 0) {
                    flag = false;
                }
            }

            int a = 0;
            for (int b = 9; b >= 0; b--) {
                for (int i = 0; i < negativeBuckets[b].size(); i++) {
                    data[a] = negativeBuckets[b].get(i);
                    names[a++] = negativeNames[b].get(i);
                }
                negativeBuckets[b].clear();
                negativeNames[b].clear();
            }
            for (int c = 0; c < 10; c++) {
                for (int i = 0; i < positiveBuckets[c].size(); i++) {
                    data[a] = positiveBuckets[c].get(i);
                    names[a++] = positiveNames[c].get(i);
                }
                positiveBuckets[c].clear();
                positiveNames[c].clear();
            }

            divisor *= 10;
        }
    }

    private static void printData(String[] names, double[] data) {
        System.out.print("{");
        for (int i = 0; i < data.length; i++) {
            System.out.print(names[i] + ": " + data[i] + ", ");
        }
        System.out.println("\b\b}");
    }
}