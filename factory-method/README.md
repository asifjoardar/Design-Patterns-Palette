# Factory Method Design Pattern

## 🧠 Purpose and Intent

The **Factory Method Design Pattern** provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created. It helps achieve loose coupling and enhances code scalability.

## 🔑 Also Known As

- **Virtual Constructor**
- **Factory Pattern**

## 📝 In Simple Terms

Instead of instantiating objects directly using `new`, the **Factory Method** defines an interface for creating an object but allows subclasses to decide which class to instantiate.

## 📚 What Wikipedia Says

> "In class-based programming, the factory method pattern is a creational pattern that provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created."

## 🌍 Real-World Analogy

Imagine a **bakery** where you can order different types of bread:

- Instead of baking the bread yourself, you place an order at the bakery.
- The bakery decides the exact process of making the bread.
- You only care about getting the correct type of bread, not how it's made internally.

The bakery acts as a **factory**, deciding which specific bread to bake based on the order.

## 💡 When Should You Use It?

- When a class can't anticipate the type of objects it needs to create.
- When you want to delegate object creation to subclasses.
- When you want to enforce **loose coupling** between components.
- When the object creation process contains logic that shouldn't be in the client code.

## 🚀 Real-World Uses in Software

- **Java's `Calendar.getInstance()`** - Returns a calendar instance suited to the locale and time zone.
- **Logging Frameworks (e.g., SLF4J, Log4J)** - Provides different logger implementations.
- **Database Connection Factories** - Abstracts database connection logic from client code.
- **GUI Toolkits** - Creates UI components dynamically.

## 🛠️ Structure

![factory-method](https://raw.githubusercontent.com/asifjoardar/Design-Patterns-Palette/refs/heads/master/factory-method/factory-method.png)

### Explanation:

- **Product (Interface)**: Defines the interface for the objects that will be created.
- **ProductA and ProductB**: Concrete implementations of the Product interface.
- **Creator (Abstract Class)**: Declares the factory method createProduct(), which is intended to return a Product.
- **ConcreteCreatorA and ConcreteCreatorB**: Subclasses of Creator that override createProduct() to return specific products (ProductA or ProductB).
- **Dependency from Creator to Product**: The Creator interacts with Product without knowing which specific subclass will be instantiated.

**Notes in the Diagram**:
- The Creator class does not create an object directly; instead, it calls createProduct(), which is implemented in subclasses.
- ConcreteCreatorA returns an instance of ProductA, ensuring that the object creation is handled dynamically.

## ⚖️ Pros and Cons

### ✅ Pros

- **Encapsulates object creation**, making the system more flexible.
- **Supports the Open/Closed Principle**, allowing new products without modifying existing code.
- **Encourages loose coupling** between client code and concrete classes.

### ❌ Cons

- **Can introduce complexity** by requiring subclassing.
- **Might lead to excessive subclassing** when there are many product types.

## 🔗 How It Connects with Other Patterns

- **Abstract Factory**: Uses multiple factory methods to create related objects.
- **Singleton**: A factory method can ensure that only one instance of a class is created.
- **Prototype**: Factories may use cloning instead of direct instantiation.
- **Builder**: Can work alongside a factory method to create complex objects step by step.

## 📚 Resources to Learn More

- Books:
  - Design Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma et al. (the "Gang of Four" book).
  - Head First Design Patterns by Eric Freeman and Elisabeth Robson.

- Online Articles:
  - [Refactoring Guru - Factory Method Pattern](https://refactoring.guru/design-patterns/factory-method)

- Videos:
  - [Factory Method Design Pattern - Christopher Okhravi](https://youtu.be/EcFVTgRHJLM)

---
