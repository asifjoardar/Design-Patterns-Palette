package org.asif.decorators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileDataSource implements DataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileDataSource.class);

    private final String name;

    public FileDataSource(String name) {
        this.name = name;
    }

    @Override
    public void writeData(String data) {
        File file = new File(name);
        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException ex) {
            LOGGER.error("Failed to write data to {}", name, ex);
        }
    }

    @Override
    public String readData() {
        char[] buffer = null;
        File file = new File(name);
        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException ex) {
            LOGGER.error("Failed to read data from {}", name, ex);
        }
        return new String(buffer);
    }
}
