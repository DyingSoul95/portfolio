public class Main {

    public static void main(String[] args) {

        String[] str = new String[]{"Russia", "UK", "USA", "Колумбия", "Эстония", "Куба", "Нигерия"};
        double[] db = new double[]{255, 1001, 36, 480, 450, 33, 100};

        sortStoreSales(db, str);
        printData(str, db);

    }


    static void sortStoreSales(double[] dbArray, String[] strArray) {
        if (strArray.length == dbArray.length && strArray.length != 0) {
            double[] leftArray = new double[dbArray.length / 2];
            double[] rightArray = new double[dbArray.length - leftArray.length];

            String[] leftStrArray = new String[strArray.length / 2];
            String[] rightStrArray = new String[strArray.length - leftArray.length];

            for (int i = 0; i < dbArray.length; i++) {
                if (i < leftArray.length) {
                    leftArray[i] = dbArray[i];
                    leftStrArray[i] = strArray[i];
                } else {
                    rightArray[i - leftArray.length] = dbArray[i];
                    rightStrArray[i - leftStrArray.length] = strArray[i];
                }
            }

            if (rightArray.length > 1) {
                sortStoreSales(leftArray, leftStrArray);
                sortStoreSales(rightArray, rightStrArray);
            }

            extracted(dbArray, strArray, leftArray, rightArray, leftStrArray, rightStrArray);
        } else if (strArray.length != dbArray.length) {
            System.out.println("Corrupted Data");
        } else {
            System.out.println("Empty Data");
        }
    }

    private static void extracted(double[] dbArray, String[] strArray, double[] leftArray, double[] rightArray, String[] leftStrArray, String[] rightStrArray) {
        int leftId = 0;
        int rightId = 0;
        for (int i = 0; i < dbArray.length; i++) {
            if (leftId == leftArray.length && rightId < rightArray.length) {
                strArray[i] = rightStrArray[rightId];
                dbArray[i] = rightArray[rightId++];
                printData(strArray, dbArray);
            } else if (rightId == rightArray.length && leftId < leftArray.length) {
                strArray[i] = leftStrArray[leftId];
                dbArray[i] = leftArray[leftId++];
                printData(strArray, dbArray);
            } else {
                if (leftArray[leftId] < rightArray[rightId]) {
                    strArray[i] = leftStrArray[leftId];
                    dbArray[i] = leftArray[leftId++];
                    printData(strArray, dbArray);

                } else {
                    strArray[i] = rightStrArray[rightId];
                    dbArray[i] = rightArray[rightId++];
                    printData(strArray, dbArray);
                }
            }
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