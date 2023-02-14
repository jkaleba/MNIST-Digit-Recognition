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
            nRows = 28; nCols = 28;
            DataInputStream labelsInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(labelsPath))));
            magicNumber = labelsInputStream.readInt(); numberOfItems = labelsInputStream.readInt();

            data.setSize(numberOfItems, new int[]{nRows, nCols});

            for(int i = 0; i < numberOfItems; i++) {
                data.setLabel(i, labelsInputStream.readByte());
            }
            labelsInputStream.close();

            DataInputStream imagesInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(imagesPath))));
            for(int i = 0; i < 4; i++) {
                imagesInputStream.readInt();
            }

            for(int i = 0; i < numberOfItems; i++) {
                for(int j = 0; j < nRows * nCols; j++) {
                    data.setImageElement(i, j, (double)imagesInputStream.read() / 255);
                }
            }
            imagesInputStream.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}