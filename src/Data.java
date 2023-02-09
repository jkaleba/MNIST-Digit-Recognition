import java.util.Arrays;
import java.util.Random;
import Maths.*;

class Data {
    private Vector[] images;
    private Vector[] labels;

    public Data() {

    }
    public Data(int size) {
        this.setSize(size);
    }
    public Data(int size, int[] imgDimension) {
        this.setSize(size, imgDimension);
    }

    public void setSize(int size) {
        this.images = new Vector[size];
        this.labels = new Vector[size];
    }
    public void setSize(int size, int[] imageDimension) {
        this.images = new Vector[size];
        this.labels = new Vector[size];

        for(int i = 0; i < size; i++) {
            this.images[i] = new Vector(imageDimension[0] * imageDimension[1]);
            this.labels[i] = new Vector(10);
        }
    }

    Vector[] getImages() {
        return images;
    }
    Vector getImage(int idx) {
        return images[idx];
    }

    int length() {
        return labels.length;
    }

    Vector getLabel(int idx) {
        return labels[idx];
    }

    void setLabel(int idx, int value) {
        labels[idx].fill(0);
        labels[idx].set(value, 1.0);
    }
    void setImage(int idx, Vector image) {
        this.images[idx] = image;
    }

    void setImageElement(int idx, int i, double value) {
        images[idx].set(i, value);
    }

    void printLabels() {
        System.out.println(Arrays.toString(labels));
    }


    void swap(int src, int dest) {
        Vector tempImg = images[src]; Vector tempLbl = labels[src];
        images[src] = images[dest]; labels[src] = labels[dest];
        images[dest] = tempImg; labels[dest] = tempLbl;
    }

    void shuffle() {
        int index;
        Random random = new Random();
        for(int i = labels.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            this.swap(index, i);
        }
    }

    void printImage(int idx) {
        for(int i = 0; i < 28; i++) {
            for(int j = 0; j < 28; j++) {
                System.out.print(images[idx].get(i * 28 + j) == 0 ? " " : "1" + " ");
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

    }
}
