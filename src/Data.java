import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class Data {

    private double[][][] images;
    private int[] labels;

    public Data() {

    }
    public Data(int size) {
        this.setSize(size);
    }
    public Data(int size, int[] imgDimension) {
        this.setSize(size, imgDimension);
    }

    public void setSize(int size) {
        this.images = new double[size][][];
        this.labels = new int[size];
    }
    public void setSize(int size, int[] imageDimension) {
        this.images = new double[size][imageDimension[0]][imageDimension[1]];
        this.labels = new int[size];
    }

    double[][][] getImages() {
        return images;
    }
    double[][] getImage(int idx) {
        return images[idx];
    }

    int length() {
        return labels.length;
    }

    int[] getLabels() {
        return labels;
    }
    int getLabel(int idx) {
        return labels[idx];
    }

    void setLabel(int idx, int label) {
        this.labels[idx] = label;
    }

    void setImage(int idx, double[][] image) {
        this.images[idx] = image;
    }

    void setImageElement(int i, int j, int k, double value) {
        this.images[i][j][k] = value;
    }

    void printLabels() {
        System.out.println(Arrays.toString(labels));
    }
    void printImage(int idx) {
        for(int i = 0; i < images[idx].length; i++) {
            for(int j = 0; j < images[idx][i].length; j++) {
                String printVal = images[idx][i][j] == 0 ? "" : "1";
                System.out.print(printVal + " ");
            }
            System.out.println();
        }
    }
    void printImages() {
        for(int i = 0; i < this.images.length; i++) {
            printImage(i);
        }
    }

    void showImage(int idx) {
        for(int i = 0; i < images[idx].length; i++) {
            for(int j = 0; j < images[idx][i].length; j++) {
                String printVal = images[idx][i][j] == 0 ? "" : String.valueOf(images[idx][i][j]);
                System.out.print(printVal + " ");
            }
            System.out.println();
        }
    }

    void swap(int src, int dest) {

        double[][] tempImg = images[src];
        int tempLbl = labels[src];

        images[src] = images[dest];
        labels[src] = labels[dest];

        images[dest] = tempImg;
        labels[dest] = tempLbl;
    }

    void shuffle() {
        int index;
        double[][] imgTemp;
        int lblTemp;
        Random random = new Random();
        for(int i = labels.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            this.swap(index, i);
        }
    }
}
