# Encoding and Compression

**Pattern:** [Decorator](../README.md)

## 📖 The Story (the problem)
Imagine a system that stores sensitive data such as employee salary records. Saving it as-is is risky:

* Sensitive data should be **encrypted** for security.
* Large data should be **compressed** to save space.

The quick fix is to cram encryption and compression into the `FileDataSource` class itself. That backfires:

* It breaks the **Single Responsibility Principle** — one class now stores *and* transforms data.
* It gets hard to maintain as new needs (hashing, logging) pile on.
* It's inflexible — you can't easily turn features on or off, or change their order.

## 💡 The Solution (using the Decorator pattern)
The Decorator pattern adds behavior by **wrapping** an object instead of editing it. Each decorator does one job, then hands the rest off to whatever it wraps.

* **`DataSource`** — the component interface (`writeData` / `readData`).
* **`FileDataSource`** — the concrete component; reads and writes the file.
* **`DataSourceDecorator`** — the base decorator; holds a wrapped `DataSource` and forwards calls to it.
* **`EncryptionDecorator` / `CompressionDecorator`** — concrete decorators that add one step each.

Because every decorator wraps another `DataSource`, you can stack them in any order. In this example the data is **compressed first and then encrypted** on the way out (and reversed on the way in). Compressing before encrypting is the sensible order — encrypted data looks random and barely compresses.

## 💻 In Code
```java
// Wrap a plain file source with compression on top of encryption.
DataSource source = new CompressionDecorator(
        new EncryptionDecorator(new FileDataSource("output.txt")));

source.writeData(salaryRecords);      // compress -> encrypt -> write to file
String restored = source.readData();  // read -> decrypt -> decompress
```

## 🛠️ UML Diagram

![Encoding and Compression uml](uml.png)

## 🎯 What We Gain
* **Single Responsibility:** each class does one thing (store, encrypt, or compress).
* **Flexible composition:** decorators can be stacked in any combination.
* **Extensibility:** add a new decorator (e.g. hashing) without changing existing code.
* **Runtime behavior:** you can wrap an object dynamically based on conditions.

## ⚠️ Watch Out For
* **Complexity:** many nested decorators can be harder to follow.
* **Debugging:** tracing data through several layers takes more effort.
* **Performance overhead:** each layer adds work, especially heavy steps like encryption.
