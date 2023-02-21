import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

class MNISTDataReader {

    public Data readTrainingData() {
        return readData("Data/train");
    }

    public Data readTestData() {
        return readData("Data/t10k");
    }

    private Data readData(String pathname) {
        Data data = new Data();

        Path labelsPath = Paths.get(pathname + "-labels-idx1-ubyte");
        Path imagesPath = Paths.get(pathname + "-images-idx3-ubyte");

        try {
            int magicNumber, numberOfItems, nRows, nCols;
            DataInputStream imagesInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(imagesPath))));
            magicNumber = imagesInputStream.readInt();
            if(magicNumber != 2051) {
                throw new IOException("Invalid image magic number.");
            }

            numberOfItems = imagesInputStream.readInt();
            nRows = imagesInputStream.readInt();
            nCols = imagesInputStream.readInt();

            data.setSize(numberOfItems, new int[]{nRows, nCols});

            for(int i = 0; i < numberOfItems; i++) {
                for(int j = 0; j < nRows * nCols; j++) {
                    data.setImageElement(i, j, (double)imagesInputStream.read() / 255);
                }
            }
            imagesInputStream.close();

            DataInputStream labelsInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(labelsPath))));
            magicNumber = labelsInputStream.readInt();
            if(magicNumber != 2049) {
                throw new IOException("Invalid label magic number.");
            }
            numberOfItems = labelsInputStream.readInt();

            for(int i = 0; i < numberOfItems; i++) {
                data.setLabel(i, labelsInputStream.readByte());
            }
            labelsInputStream.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}