import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


class MNISTDataReader {

    public Data readData() {
        Data data = new Data();

        Path labelsPath = Paths.get("Data/train-labels-idx1-ubyte");
        Path imagesPath = Paths.get("Data/train-images-idx3-ubyte");

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

            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(imagesPath))));
            for(int i = 0; i < 4; i++) {
                dataInputStream.readInt();
            }

            for(int i = 0; i < numberOfItems; i++) {
                for(int j = 0; j < nRows; j++) {
                    for(int k = 0; k < nCols; k++) {
                        data.setImageElement(i, j, k, (double)dataInputStream.read() / 255);
                    }
                }
            }
            dataInputStream.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
