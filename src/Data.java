class Data {

    double[][][] images;
    int[] labels;

    public Data() {

    }

    public void setSize(int size, int[] imageDimension) {
        this.images = new double[size][imageDimension[0]][imageDimension[1]];
        this.labels = new int[size];
    }

    double[][][] getImages() {
        return images;
    }

    int[] getLabels() {
        return labels;
    }

    void setLabel(int idx, int label) {
        this.labels[idx] = label;
    }

    void setImageElement(int i, int j, int k, double value) {
        this.images[i][j][k] = value;
    }


}
