# Decorator Design Pattern

## 🧠 Purpose and Intent
The Decorator pattern’s purpose is to add functionality to an object dynamically, without changing its structure. It allows behaviors to be wrapped around existing methods of an object, providing more flexibility than static inheritance.

## 🔑 Also Known As
Wrapper Pattern

## 📝 In Simple Terms
Think of it like dressing up a basic coffee order. You start with plain coffee, and if you want to add milk, sugar, or caramel, you wrap that functionality around the original order, making it richer without modifying the base coffee class.

## 📖 What Wikipedia Says
_"In object-oriented design, the decorator pattern is a design pattern that allows behavior to be added to individual objects, dynamically, without affecting the behavior of other objects from the same class."_

## 🌍 Real-World Analogy
Imagine a basic car. You can decorate it with features like a sunroof, leather seats, or a premium sound system. Each of these features wraps additional functionality around the base car model, enhancing it without altering its core structure.

## 💡 When Should You Use It? (Applicability)

* When you want to add responsibilities or functionalities to an object without altering its structure.
* When it's better to use composition over inheritance. Instead of creating multiple subclasses for every possible combination of features, you can use decorators to wrap and enhance functionality dynamically.
* When you need to add features dynamically at runtime.

## 🚀 Real-World Uses in Software

* **Java I/O Streams:** Java’s `InputStream`, `OutputStream`, `Reader`, and `Writer` classes use the Decorator pattern. You can wrap a basic file reader with a buffer or a compression feature.
* **Graphical User Interface (GUI):** In GUI libraries, you can decorate basic components like text boxes or buttons with scrollbars, borders, or shadows.

## 🛠️ Structure

Here’s a simplified UML structure of the Decorator pattern:

```
    +-------------------+                  +------------------------+
    |    Component      |                  |    Decorator           |
    |-------------------|                  |------------------------|
    | + operation():void|<-----------------| - component: Component |
    +-------------------+                  | + operation(): void    |
              |                            +------------------------+
              |                                  /           \
    +-------------------+                       /             \
    | ConcreteComponent |                      /               \
    |-------------------|                     /                 \
    |+ operation():void |       +----------------------+  +----------------------+
    +-------------------+       |   ConcreteDecoratorA |  |   ConcreteDecoratorB |
                                |----------------------|  |----------------------|
                                | + operation(): void  |  | + operation(): void  |
                                +----------------------+  +----------------------+
```

* **Component:** Defines the interface for objects that can have responsibilities added to them dynamically.
* **ConcreteComponent:** This is the actual object that will have additional functionality added via decorators. It implements the Component interface.
* **Decorator:** The base decorator class that holds a reference to a Component and implements the Component interface. It wraps the component and forwards the operation() call to it while allowing concrete decorators to modify or extend the behavior.
* **ConcreteDecorator:** These are the specific decorators that add functionality to the ConcreteComponent. They override operation() to enhance the original functionality, either before or after forwarding the request to the component.

### Explanation:

This structure allows dynamic composition of behaviors by stacking multiple decorators around the ConcreteComponent.

## ⚖️ Pros and Cons

### ✅ Pros

* **Flexible and Reusable:** You can add, remove, or combine functionalities at runtime without altering the underlying object or class.
* **Single Responsibility Principle:** Each class has a clear purpose; components and decorators are kept separate and focused.
* **Open for Extension:** New decorators can be added without modifying existing code.

### ❌ Cons

* **Complexity:** Adding many layers of decorators can make the code harder to read and debug.
* **Creation of Many Small Objects:** If you chain many decorators, the number of small objects created can increase, which may impact performance or memory.

## 🔗 How It Connects with Other Patterns

* **Adapter Pattern:** Both Adapter and Decorator patterns involve wrapping objects, but the Adapter changes the interface while the Decorator enhances the functionality without altering the interface.
* **Composite Pattern:** Decorator is often used when objects need to be composed (i.e., decorated) to add functionality, while Composite allows for building tree structures of objects.
* **Strategy Pattern:** Decorator dynamically changes an object's behavior, whereas Strategy dynamically selects an algorithm at runtime. Both provide flexible behavior, but for different purposes.

## 📚 Resources to Learn More

- Books:
  - Design Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma et al. (the "Gang of Four" book).
  - Head First Design Patterns by Eric Freeman and Elisabeth Robson.

- Online Articles:
  - [Refactoring Guru - Decorator Pattern](https://refactoring.guru/design-patterns/decorator)