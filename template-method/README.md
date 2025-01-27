# Template Method Pattern

## 🧠 Purpose and Intent

The **Template Method Design Pattern** is a behavioral design pattern that defines the program skeleton of an algorithm in a method, deferring some steps to subclasses. It lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.

## 🔑 Also Known As

- **Template Pattern**

## 📝 In Simple Terms

Imagine you’re baking a cake. The recipe (template method) provides a step-by-step process: mix ingredients, bake, and decorate. However, the type of cake (subclass) can change the specific ingredients or decoration style without altering the overall baking process.

## 📖 What Wikipedia Says

The **Template Method Pattern** is a behavioral design pattern that defines the program skeleton of an algorithm in a superclass but lets subclasses override specific steps of the algorithm without changing its structure.

## 🌍 Real-World Analogy

Think of a **construction blueprint**. The blueprint defines the structure of a house (foundation, walls, roof), but the specific materials (wood, brick) and interior design can vary based on the homeowner's preferences.

## 💡 When Should You Use It?

- When you want to **avoid code duplication** by sharing common behavior among subclasses.
- When you need to **control the order of steps** in an algorithm.
- When you want to allow subclasses to **extend or modify specific steps** without changing the overall algorithm.

## 🚀 Real-World Uses in Software

- **Java Servlet API**: The HttpServlet class in Java's Servlet API uses the Template Method Pattern. It provides methods like doGet(), doPost(), and service() as part of the template, allowing developers to override these methods to handle specific HTTP requests.
- **Spring Framework**: The JdbcTemplate class in Spring uses this pattern to handle database operations. It defines the overall flow (e.g., opening/closing connections, executing queries) while allowing developers to customize the query logic.

## 🛠️ Structure

Below is the UML diagram for the Template Method Pattern:

![template-method](https://raw.githubusercontent.com/asifjoardar/Design-Patterns-Palette/refs/heads/master/template-method/template-method.png)

### Explanation:
- **AbstractClass**: Defines the template method (`templateMethod()`) and abstract methods (`primitiveOperation1()`, `primitiveOperation2()`) that subclasses must implement.
- **ConcreteClassA** and **ConcreteClassB**: Implement the abstract methods to provide specific behavior for the steps defined in the template method.

## ⚖️ Pros and Cons

### Pros:
- **Code Reusability**: Common behavior is centralized in the superclass.
- **Flexibility**: Subclasses can override specific steps without changing the overall algorithm.
- **Control**: The superclass controls the algorithm's structure, ensuring consistency.

### Cons:
- **Rigidity**: The pattern can be inflexible if too many steps are predefined.
- **Complexity**: Overuse can lead to a large number of subclasses, making the system harder to maintain.

## 🔗 How It Connects with Other Patterns

- **Strategy Pattern**: Both patterns allow customization of behavior, but the Template Method uses inheritance, while the Strategy Pattern uses composition.
- **Factory Method Pattern**: The Factory Method is often a step within a Template Method.
- **Hook Operations**: Template Methods can use hooks (optional steps) to provide additional flexibility.

## 📚 Resources to Learn More

- **Books**:
  - *Design Patterns: Elements of Reusable Object-Oriented Software* by Erich Gamma et al. (the "Gang of Four" book).
  - *Head First Design Patterns* by Eric Freeman and Elisabeth Robson.
- **Online Articles**:
  - [Refactoring Guru - Template Method](https://refactoring.guru/design-patterns/template-method)
  - [SourceMaking - Template Method](https://sourcemaking.com/design_patterns/template_method)
- **Videos**:
  - [Template Method Pattern - Derek Banas](https://www.youtube.com/watch?v=7ocpwK9uesw)
  - [Template Method Design Pattern - Christopher Okhravi](https://www.youtube.com/watch?v=aR1B8MlwbRI)

---
