# Builder Design Pattern

## 🧠 Purpose and Intent
The Builder design pattern's mission is to separate the construction of a complex object from its representation. This allows the same construction process to create different representations and helps manage complex object creation step by step.

## 🔑 Also Known As
None commonly used

## 📝 In Simple Terms
The Builder pattern helps you construct complex objects step by step, like building a custom computer where you separately choose the CPU, RAM, storage, and other components. Instead of a massive constructor with many parameters, you get a clean, readable way to create objects.

## 📖 What Wikipedia Says
The builder pattern is a design pattern designed to provide a flexible solution to various object creation problems in object-oriented programming. It's particularly useful when you need to create an object with numerous possible configurations.

## 🌍 Real-World Analogy
Think of ordering a custom sandwich at a deli. Instead of saying everything at once ("I want a wheat bread sandwich with turkey, lettuce, tomato, mayo, and pickles"), you build it step by step:
1. Choose the bread
2. Select the meat
3. Pick the vegetables
4. Add condiments
   Each step is independent, and you can skip steps or change the order!

## 💡 When Should You Use It?
- When object construction involves multiple steps
- When you need to create different variations of the same product
- When you want to encapsulate complex creation logic
- When constructor parameters are getting out of hand (telescoping constructor problem)
- When some object construction steps are optional

## 🚀 Real-World Uses in Software
- Document Generators: Creating PDF, HTML, or other document types using the same construction process
- Query Builders: Database query construction in ORMs
- UI Builders: Creating complex user interface elements
- Configuration Objects: Building complex configuration settings

## 🛠️ Structure
Here's a simplified UML diagram for the Builder pattern:
```
+-------------+          +------------+
|   Director  |          |  Builder   |
|-------------|          |------------|
| - builder   |<>------->| + buildA() |
| + construct |          | + buildB() |
|             |          | + getResult|
+-------------+          +------------+
∆
|
+----------------+
| ConcreteBuilder|
|----------------|
| + buildA()     |
| + buildB()     |
| + getResult()  |
+----------------+
```
- Director: Optional class that defines the order of building steps
- Builder: Interface declaring all possible building steps
- ConcreteBuilder: Implements the Builder interface to create specific products
- Product: The complex object being built

## ⚖️ Pros and Cons
### ✅ Pros
- Clean Code: Avoids telescoping constructors
- Flexibility: Can create different representations using the same process
- Encapsulation: Construction details are hidden from the client
- Step-by-Step: Clear separation of construction steps

### ❌ Cons
- Complexity: Requires creating multiple new classes
- Overhead: Might be overkill for simple objects
- Mutable State: Builder must maintain object state during construction

## 🔗 How It Connects with Other Patterns
- Factory Pattern: Builder focuses on step-by-step construction, while Factory creates objects in a single step
- Abstract Factory: Can use Builder to create complex products within an Abstract Factory
- Composite Pattern: Builders are often used to construct complex composite structures
- Fluent Interface: Builder often implements a fluent interface (method chaining) for a more readable API

## 📚 Resources to Learn More

- Books:
  - Design Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma et al. (the "Gang of Four" book).
  - Head First Design Patterns by Eric Freeman and Elisabeth Robson.

- Online Articles:
  - [Refactoring Guru - Builder Pattern](https://refactoring.guru/design-patterns/builder)