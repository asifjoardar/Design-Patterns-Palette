# Singleton Design Pattern

## 🧠 Purpose and Intent
The **Singleton Design Pattern** ensures that a class has only one instance and provides a global point of access to that instance. It is used to control object creation, preventing multiple instances of a class in an application.

## 🔑 Also Known As
- **Single Point of Control**
- **Global Instance Pattern**

## 📝 In Simple Terms
Instead of creating multiple instances of a class, the **Singleton Pattern** ensures that only one instance exists and is shared throughout the application.

## 📖 What Wikipedia Says
> "The singleton pattern is a software design pattern that restricts the instantiation of a class to one single instance. This is useful when exactly one object is needed to coordinate actions across the system."

## 🌍 Real-World Analogy
Imagine a group with a single leader:
- In any group, such as a sports team, company department, or project team, there is usually one leader responsible for making final decisions and guiding the group.
- If multiple people tried to assume the leadership role at the same time, conflicts and confusion would arise.
- Regardless of who holds the leadership position at a given time, the title of "leader" remains singular, acting as a global point of access for decision-making and coordination.

## 💡 When Should You Use It?
- When **only one instance** of a class should exist.
- When a **global access point** to an instance is needed.
- When managing **shared resources** such as database connections, logging, or configuration settings.

## 🚀 Real-World Uses in Software
- **Database Connection Management** (Ensuring a single connection instance)
- **Logging Systems** (Only one logger instance throughout the application)
- **Thread Pools** (Managing a fixed number of reusable threads)
- **Configuration Management** (Centralized access to configuration settings)

## 🛠️ Structure

![singleton](https://raw.githubusercontent.com/asifjoardar/Design-Patterns-Palette/refs/heads/master/singleton/singleton.png)

### Explanation:
- **`Singleton`**: A class with a private constructor and a static method (`getInstance`) that returns the single instance.
- **Ensures only one instance exists** by storing it in a static field.
- **Global access point** is provided through `getInstance()`.

## ⚖️ Pros and Cons
### ✅ Pros
- **Ensures a single instance**, avoiding redundant object creation.
- **Saves memory and optimizes performance** by reusing the same instance.
- **Centralized control** over shared resources.

### ❌ Cons
- **Introduces global state**, making testing difficult.
- **Can create hidden dependencies**, leading to tightly coupled code.
- **Not thread-safe by default**, requiring additional synchronization in multithreaded environments.

## 🔗 How It Connects with Other Patterns
- **Abstract Factories**, **Builders** and **Prototypes** can all be implemented as Singletons.

## 📚 Resources to Learn More

- Books:
  - Design Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma et al. (the "Gang of Four" book).
  - Head First Design Patterns by Eric Freeman and Elisabeth Robson.

- Online Articles:
  - [Refactoring Guru - Singleton Design Pattern](https://refactoring.guru/design-patterns/singleton)

- Videos:
  - [Singleton Design Pattern - Christopher Okhravi](https://youtu.be/hUE_j6q0LTQ?list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc)

---
