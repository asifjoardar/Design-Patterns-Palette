# State Design Pattern

## 🧠 Purpose and Intent

The **State Design Pattern** allows an object to change its behavior when its internal state changes. It helps manage complex conditional logic by encapsulating state-specific behaviors into separate classes.

## 🔑 Also Known As

- **Objects for States**
- **State Machine Pattern**

## 📝 In Simple Terms

Instead of using large `if-else` or `switch` statements to handle different states, the **State Pattern** moves the state-specific logic into separate classes. The context delegates state transitions and behavior execution to these state classes.

## 📖 What Wikipedia Says

> "The state pattern is a behavioral software design pattern that implements a state machine using objects. This pattern is used when an object's behavior is dependent on its state and it must change its behavior at runtime depending on that state."

## 🌍 Real-World Analogy

Imagine a **traffic light system**:

- When the light is **Green**, cars move.
- When the light turns **Yellow**, cars slow down.
- When the light turns **Red**, cars stop.

Instead of handling all these conditions in one class, we create separate state classes (`GreenState`, `YellowState`, `RedState`), and the **traffic light object** delegates its behavior to the current state.

## 💡 When Should You Use It?

- When an object's behavior depends on its state and needs to change dynamically at runtime.
- When you want to **eliminate complex conditional logic** that manages state transitions.
- When you need a **scalable and maintainable** way to manage multiple states.

## 🚀 Real-World Uses in Software

- **TCP Connection Management** (e.g., `Closed`, `Listening`, `Established` states)
- **Document Workflow Systems** (e.g., `Draft`, `Review`, `Published` states)
- **Game Development** (e.g., `IdleState`, `RunningState`, `JumpingState` for characters)

## 🛠️ Structure

![](https://raw.githubusercontent.com/asifjoardar/Design-Patterns-Palette/refs/heads/master/state/state.png)

### Explanation:

- **Context**: The class that contains a reference to the current state object. It delegates state-specific behavior to the current state.
- **State**: An interface or abstract class that defines the common interface for all concrete states.
- **ConcreteStateA and ConcreteStateB**: Classes that implement the State interface and provide behavior specific to a particular state.

## ⚖️ Pros and Cons

### ✅ Pros

- **Encapsulates state-specific behavior** in separate classes, making the code **cleaner**.
- **Improves maintainability** by eliminating large `if-else` or `switch` statements.
- **Easier to extend** by adding new states without modifying existing code.
- **Encourages Single Responsibility Principle (SRP)** by separating concerns.

### ❌ Cons

- **Increases the number of classes**, making the codebase larger.
- **Can be overkill** for simple state transitions.
- **State transition logic** might still be distributed across multiple classes, making debugging harder.

## 🔗 How It Connects with Other Patterns

- **Strategy Pattern**: Similar, but Strategy focuses on interchangeable behaviors, while State focuses on state-driven behavior changes.
- **Command Pattern**: Can be used with State to execute specific state-based commands.
- **Observer Pattern**: Can be used to notify when the state changes.

## 📚 Resources to Learn More

- Books:
  - Design Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma et al. (the "Gang of Four" book).
  - Head First Design Patterns by Eric Freeman and Elisabeth Robson.

- Online Articles:
  - [Refactoring Guru - State Pattern](https://refactoring.guru/design-patterns/state)

- Videos:
  - [State Design Pattern - Christopher Okhravi](https://youtu.be/N12L5D78MAA)

---
