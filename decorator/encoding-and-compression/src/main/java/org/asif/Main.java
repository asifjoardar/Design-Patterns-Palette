package org.asif;

import org.asif.decorators.*;

public final class Main {
    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(String[] args) {
        String salaryRecords = """
                Name, Salary
                John Smith, 100000
                Steven Jobs, 912000
                """;
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(new FileDataSource("output.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("output.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
