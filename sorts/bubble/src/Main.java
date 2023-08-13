public class Main {
    public static void main(String[] args) {

        String[] str = new String[]{"Russia", "UK", "USA", "Колумбия", "Эстония", "Куба", "Нигерия"};
        double[] db = new double[]{255, 1001, -36, 480, 450, 33, 100};

        sortSales(str, db);
    }

    private static void sortSales(String[] names, double[] data) {

        if (names.length == data.length && names.length != 0) {
            for (int i = 1; i < data.length; ++i) {
                double min;
                String minName;

                for (int j = 0; j < data.length - i; ++j) {
                    if (data[j] < data[j + 1]) {
                        min = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = min;

                        minName = names[j];
                        names[j] = names[j + 1];
                        names[j + 1] = minName;

                        printData(names, data);
                    }
                }
            }
        }
        if (names.length != data.length) {
            System.out.println("Corrupted Data");
        }
        if (names.length == 0 || data.length == 0) {
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