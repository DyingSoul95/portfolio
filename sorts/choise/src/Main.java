public class Main {
    public static void main(String[] args) {

        String[] str = new String[]{"Russia", "UK", "USA", "Колумбия", "Эстония"};
        double[] db = new double[]{-67.1, 0.5, -74.8, 55.6, -9.4};

        sortSales(str, db);
    }

    private static void sortSales(String[] names, double[] data) {

        double max;
        double min;

        if (names.length == data.length && names.length != 0) {
            for (int i = data.length - 1, k = 0; i > data.length / 2; i--, k++) {

                int maxId = k;
                String maxName = names[k];
                max = data[maxId];

                for (int j = k + 1; j <= i; j++) {
                    if (data[j] > max) {
                        max = data[j];
                        maxName = names[j];
                        maxId = j;
                    }
                }

                data[maxId] = data[i];
                names[maxId] = names[i];
                data[i] = max;
                names[i] = maxName;

                printData(names, data);

                int minId = i - 1;
                String minName = names[minId];
                min = data[minId];

                for (int j = i - 2; j > k; j--) {
                    if (data[j] < min) {
                        min = data[j];
                        minName = names[j];
                        minId = j;
                    }
                }

                if(data[k] > data[minId]) {
                    data[minId] = data[k];
                    names[minId] = names[k];
                    data[k] = min;
                    names[k] = minName;
                }

                printData(names, data);
            }
        } else if (names.length != data.length) {
            System.out.println("Corrupted Data");
        } else {
            System.out.println("Empty Data");
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