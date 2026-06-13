# Event Subscription (File Editor)

**Pattern:** [Observer](../README.md)

## 📖 The Story (the problem)
Imagine you are building a text editor where users open and save files. Whenever that happens, you also want other parts of the app to react — for example, send an email or write a log entry.

The quick approach is to call those components directly from the `Editor` class. But that causes problems:

* The `Editor` becomes tightly coupled to specific notification and logging code.
* Adding a new reaction (e.g. a push notification) means editing `Editor` again.
* The system gets harder to maintain and extend over time.

## 💡 The Solution (using the Observer pattern)
The Observer pattern lets the `Editor` announce that something happened, while the interested parts simply *listen*. The `Editor` never needs to know who is listening.

* **`EventListener`** — the observer interface, with a single `update(eventType, file)` method.
* **`EmailNotificationListener` / `LogOpenListener`** — the concrete observers that react to events.
* **`EventManager`** — the subject. It keeps a list of listeners per event type (`"open"`, `"save"`) and notifies them when an event fires.
* **`Editor`** — owns an `EventManager`; on open/save it fires the matching event. New listeners can be added without changing `Editor` or `EventManager`.

## 💻 In Code
```java
Editor editor = new Editor();

// Subscribe listeners to the events they care about.
editor.getEvents().subscribe("open", new LogOpenListener("/path/to/log.txt"));
editor.getEvents().subscribe("save", new EmailNotificationListener("admin@example.com"));

editor.openFile("test.txt");   // notifies every "open" listener
editor.saveFile();             // notifies every "save" listener
```

## 🛠️ UML Diagram

![File Editor uml](uml.png)

## 🎯 What We Gain
* **Loose coupling:** the `Editor` doesn't need to know the details of any notifier.
* **Scalability:** adding a new observer requires no changes to existing code.
* **Flexibility:** observers can subscribe or unsubscribe at runtime.
* **Clear organization:** the `EventManager` owns subscriptions and notifications.

## ⚠️ Watch Out For
* **Complexity:** managing subscriptions and notifications adds moving parts in larger systems.
* **Overhead:** with many observers, notifying them all can get slow.
* **Memory leaks:** forgetting to unsubscribe listeners can keep them alive forever.
