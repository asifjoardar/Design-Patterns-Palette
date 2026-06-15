# Command Design Pattern

## 🧠 Purpose and Intent
The Command pattern turns a request into a stand-alone object that holds everything needed to perform an action. This lets you pass requests around, store them, queue them, log them, and even undo them — all without the sender knowing how the work is actually done.

## 🔑 Also Known As
- Action
- Transaction

## 📝 In Simple Terms
Instead of calling a method directly, you wrap the action (and the data it needs) inside an object. Now the action can be handed off, stored in a list, run later, or reversed — the code that triggers it doesn't need to know any of the details.

## 📖 What Wikipedia Says
> "In object-oriented programming, the command pattern is a behavioral design pattern in which an object is used to encapsulate all information needed to perform an action or trigger an event at a later time."

## 🌍 Real-World Analogy
Think of ordering food at a restaurant. You tell the waiter what you want, and they write it on a ticket (the command). The ticket carries everything the kitchen needs. The waiter doesn't cook, and the cook never talks to you — the ticket connects them. Tickets can be queued, prioritized, and even remade if something goes wrong.

## 💡 When Should You Use It?
- When you want to parameterize objects with an action to perform.
- When you need to queue, schedule, or run requests at different times.
- When you want to support undo/redo.
- When you want to log or replay a sequence of operations.

## 🚀 Real-World Uses in Software
- **GUI Buttons and Menu Items:** Each button or menu entry triggers a command object, so the same action can be reused by a button, a shortcut, and a menu.
- **Task Queues and Job Scheduling:** Requests are stored as command objects and executed by workers later.
- **Undo/Redo in Editors:** Each edit is a command that knows how to apply and reverse itself.
- **Transactions:** A series of commands can be executed, committed, or rolled back as a unit.

## 🛠️ Structure

Here’s a simplified UML diagram for the Command pattern:

```
+----------------+        +-------------------+        +------------------+
|    Invoker     |------->|     Command       |        |     Receiver     |
|----------------|        |-------------------|        |------------------|
| - command      |        | + execute(): void |        | + action(): void |
| + setCommand() |        +-------------------+        +------------------+
| + run()        |                 ^                            ^
+----------------+                 |                            |
                          +-------------------+                 |
                          | ConcreteCommand   |-----------------+
                          |-------------------|
                          | - receiver        |
                          | + execute(): void |
                          +-------------------+
```

- **Command:** An interface with a single `execute()` method.
- **ConcreteCommand:** Holds a reference to a Receiver and calls the right action on it inside `execute()`.
- **Receiver:** The object that actually knows how to do the work.
- **Invoker:** Holds a command and triggers it by calling `execute()` — without knowing what it does.
- **Client:** Creates the concrete command and wires it to its receiver.

## ⚖️ Pros and Cons

### ✅ Pros
- **Decoupling:** The invoker is fully separated from the object that performs the action.
- **Single Responsibility:** Classes that trigger operations are separate from classes that perform them.
- **Open/Closed:** New commands can be added without changing existing code.
- **Powerful Features:** Makes undo/redo, queuing, scheduling, and logging straightforward.

### ❌ Cons
- **More Classes:** Each action usually becomes its own command class.
- **Overkill for Simple Cases:** For a single direct call, a command object adds needless indirection.

## 🔗 How It Connects with Other Patterns
- **Composite:** A macro-command (a command made of several commands) is built using the Composite pattern.
- **Memento:** Often paired with Command to store state needed for undo.
- **Strategy:** Both wrap behavior in an object, but Command represents a *request*, while Strategy represents an interchangeable *algorithm*.

## 📚 Resources to Learn More

- Books:
  - Design Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma et al. (the "Gang of Four" book).
  - Head First Design Patterns by Eric Freeman and Elisabeth Robson.

- Online Articles:
  - [Refactoring Guru - Command Pattern](https://refactoring.guru/design-patterns/command)
