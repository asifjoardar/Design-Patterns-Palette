# Adapter Pattern

## 🧠 Purpose and Intent
The Adapter Pattern is a structural design pattern that allows incompatible interfaces to work together. It acts as a bridge between two components, enabling them to collaborate without modifying their existing code.

## 🔑 Also Known As
- Wrapper

## 📝 In Simple Terms
Imagine you have a plug that fits into a Type-C socket but need to connect it to a device that only has a USB-A port. An adapter bridges this gap, allowing both to work seamlessly.

## 📖 What Wikipedia Says
"The adapter pattern is a software design pattern that allows the interface of an existing class to be used as another interface. It is often used to make existing classes work with others without modifying their source code."

## 🌍 Real-World Analogy
A travel adapter is a great analogy. When traveling to a country with different power socket types, you use a travel adapter to connect your devices without altering either the socket or the device plug.

## 💡 When Should You Use It?
- When you want to use an existing class but its interface does not match the one you need.
- To create a reusable class that can work with unrelated or unforeseen classes.
- When working with third-party libraries or legacy code where you cannot modify the source.

## 🚀 Real-World Uses in Software
- **Java I/O Streams:** Adapters are used in the stream classes to adapt byte streams to character streams.
- **Spring Framework:** `AdapterPattern` is often used in controllers to adapt different types of input to the expected interface.
- **Database Drivers:** Adapters translate SQL commands to the database's expected format.

## 🛠️ Structure
Here's a UML representation of the Adapter Pattern using PlantUML:

![adapter](https://raw.githubusercontent.com/asifjoardar/Design-Patterns-Palette/refs/heads/master/adapter/adapter.png)

### Explanation:
- **Target**: Defines the domain-specific interface used by the client.
- **Adaptee**: Has an existing interface that needs adaptation.
- **Adapter**: Bridges the gap between Target and Adaptee.

## ⚖️ Pros and Cons
### ✅ Pros
- Promotes code reuse by allowing existing classes to be used with new interfaces.
- Helps in adhering to the Open/Closed Principle.
- Simplifies integration with third-party libraries.

### ❌ Cons
- Increases the overall complexity of the codebase.
- May introduce additional layers, leading to performance overhead.

## 🔗 How It Connects with Other Patterns
- **Bridge Pattern:** Both separate interface and implementation, but Bridge focuses on abstraction, while Adapter focuses on compatibility.
- **Decorator Pattern:** Unlike Adapter, Decorator adds new behavior without changing the interface.
- **Facade Pattern:** Facade simplifies an interface, while Adapter alters it to fit.

## 📚 Resources to Learn More

### Books
1. "Design Patterns: Elements of Reusable Object-Oriented Software" by Gang of Four (GoF)

2. "Head First Design Patterns" by Eric Freeman

### Online Tutorials & Articles
1. Refactoring.Guru
   - https://refactoring.guru/design-patterns/adapter

