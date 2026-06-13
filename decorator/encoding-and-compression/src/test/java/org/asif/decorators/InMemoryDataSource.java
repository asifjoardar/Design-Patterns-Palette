package org.asif.decorators;

/**
 * Hand-written test double that keeps the written payload in memory, so
 * decorator tests can round-trip without touching the file system.
 */
public class InMemoryDataSource implements DataSource {
    private String stored;

    @Override
    public void writeData(String data) {
        this.stored = data;
    }

    @Override
    public String readData() {
        return stored;
    }
}
