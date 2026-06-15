# Composite Design Pattern

## 🧠 Purpose and Intent
The Composite pattern lets you compose objects into tree structures to represent part-whole hierarchies. It lets clients treat a single object and a whole group of objects the same way.

## 🔑 Also Known As
- Object Tree

## 📝 In Simple Terms
You build a tree where both single items (leaves) and groups of items (composites) share the same interface. Because they look the same to the outside world, you can work with one item or an entire group using the exact same code.

## 📖 What Wikipedia Says
> "The composite pattern describes a group of objects that are treated the same way as a single instance of the same type of object. The intent of a composite is to 'compose' objects into tree structures to represent part-whole hierarchies."

## 🌍 Real-World Analogy
Think of folders and files on your computer. A folder can contain files or other folders, which can contain more files, and so on. If you ask for the size of a file, you get that file's size; if you ask for the size of a folder, you get the total of everything inside it — but you ask both in exactly the same way.

## 💡 When Should You Use It?
- When you need to represent a part-whole hierarchy as a tree.
- When you want clients to treat individual objects and groups of objects uniformly.
- When the difference between a "single thing" and a "group of things" shouldn't matter to the code using them.

## 🚀 Real-World Uses in Software
- **File Systems:** Files and folders share the same interface, and folders contain both.
- **GUI Component Trees:** A panel can hold buttons or other panels, and you render the whole tree the same way.
- **Organization Charts:** A manager (with reports) and an individual employee are handled uniformly.
- **Menus and Submenus:** A menu item and a submenu full of items behave the same to the renderer.

## 🛠️ Structure

Here’s a simplified UML diagram for the Composite pattern:

```
            +------------------------+
            |       Component        |
            |------------------------|
            | + operation(): void    |
            +------------------------+
                 ^                ^
                 |                |
      +-----------------+   +--------------------------+
      |      Leaf       |   |        Composite         |
      |-----------------|   |--------------------------|
      | + operation()   |   | - children: Component[]  |
      +-----------------+   | + add(Component)         |
                            | + remove(Component)      |
                            | + operation(): void      |
                            +--------------------------+
```

- **Component:** The common interface for both simple and complex objects in the tree.
- **Leaf:** A single object with no children; it does the actual work in `operation()`.
- **Composite:** A node that holds child components and implements `operation()` by delegating to its children.
- **Client:** Works with all objects through the Component interface, unaware whether it holds a leaf or a composite.

## ⚖️ Pros and Cons

### ✅ Pros
- **Uniform Treatment:** Clients use single objects and groups through one interface.
- **Easy to Extend:** New leaf or composite types can be added without changing client code.
- **Works Naturally with Recursion:** Tree operations (totals, rendering, traversal) become simple.
- **Open/Closed:** New component kinds fit in without modifying existing ones.

### ❌ Cons
- **Can Be Too General:** A shared interface can make it hard to restrict what a composite may contain.
- **Type Safety:** Enforcing rules (e.g. "this group only accepts certain children") becomes harder.
- **Overhead for Simple Cases:** A flat list may be simpler than a tree when there's no real hierarchy.

## 🔗 How It Connects with Other Patterns
- **Decorator:** Both rely on recursive composition; Decorator adds responsibilities to one object, while Composite builds a tree of many.
- **Iterator:** Often used to traverse the elements of a composite tree.
- **Visitor:** Lets you run an operation across an entire composite structure.
- **Builder:** Can be used to construct complex composite trees step by step.

## 📚 Resources to Learn More

- Books:
  - Design Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma et al. (the "Gang of Four" book).
  - Head First Design Patterns by Eric Freeman and Elisabeth Robson.

- Online Articles:
  - [Refactoring Guru - Composite Pattern](https://refactoring.guru/design-patterns/composite)
