import java.util.Arrays;
import java.util.Random;

class Data {
    private double[][] images;
    private double[][] labels;

    public Data() {

    }
    public Data(int size) {
        this.setSize(size);
    }
    public Data(int size, int[] imgDimension) {
        this.setSize(size, imgDimension);
    }

    public void setSize(int size) {
        this.images = new double[size][];
        this.labels = new double[size][10];
    }
    public void setSize(int size, int[] imageDimension) {
        this.images = new double[size][imageDimension[0] * imageDimension[1]];
        this.labels = new double[size][10];
    }

    double[][] getImages() {
        return images;
    }
    double[] getImage(int idx) {
        return images[idx];
    }

    int length() {
        return labels.length;
    }

    double[][] getLabels() {
        return labels;
    }
    double[] getLabel(int idx) {
        return labels[idx];
    }

    void setLabel(int idx, double[] labelVector) {
        labels[idx] = labelVector;
    }
    void setLabel(int idx, int value) {
        Arrays.fill(this.labels[idx], .0);
        this.labels[idx][value] = 1.0;
    }

    void setImage(int idx, double[] image) {
        this.images[idx] = image;
    }

    void setImageElement(int i, int j, double value) {
        this.images[i][j] = value;
    }

    void printLabels() {
        System.out.println(Arrays.toString(labels));
    }


    void swap(int src, int dest) {

        double[] tempImg = images[src];
        double[] tempLbl = labels[src];

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

    void printImage(int idx) {
        System.out.println(Arrays.toString(labels[idx]));

        for(int i = 0; i < images[idx].length; i += 28) {
            for(int j = i; j < i + 28; j++) {
                String printVal = images[idx][j] == 0 ? "" : "1";
                System.out.print(printVal + " ");
            }
            System.out.println();
        }
    }

    void printImages() {
        for(int i = 0; i < images.length; i++) {
            printImage(i);
        }
    }

    void fill(int value) {
        for(int i = 0; i < this.length(); i++) {
            Arrays.fill(this.labels[i], value);
            Arrays.fill(this.images[i], value);
        }
    }
}
