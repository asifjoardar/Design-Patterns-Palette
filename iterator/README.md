# Iterator Design Pattern

## 🧠 Purpose and Intent

The **Iterator Design Pattern** provides a way to access elements of an **aggregate object(collection of objects)** sequentially without exposing its underlying structure. It enables traversal through a collection while maintaining a consistent and simple interface.

## 🔑 Also Known As

- **Cursor Pattern**
- **Traversal Pattern**

## 📝 In Simple Terms

Imagine you’re reading a book. You don’t need to know how the pages are stored or organized; you just flip through them one by one. The Iterator Pattern works similarly, allowing you to traverse a collection of items without knowing how the collection is implemented.

## 📚 What Wikipedia Says

> "In object-oriented programming, the iterator pattern is a design pattern in which an iterator is used to traverse a container and access the container’s elements."

## 🌍 Real-World Analogy

**TV Remote Control**: A remote control acts as an iterator for your TV channels. You can move to the next or previous channel without knowing how the channels are stored or managed internally.

**Playlist Navigation**: When you navigate through a playlist, you use next and previous buttons to move between songs without worrying about how the playlist is structured.

## 💡 When Should You Use It?

- When you need to **traverse a collection** without exposing its internal structure.
- When different ways of iterating over a collection are required (e.g., forward, backward, filtered).
- When you need **a uniform way** to iterate over different data structures (e.g., arrays, linked lists, trees).

## 🚀 Real-World Uses in Software

- **Java's `Iterator` and `ListIterator` Interfaces** (Standard Java collections use iterators for traversal.)
- **Python's Iterators (`__iter__` and `__next__`)** (Built-in support for iteration in Python.)
- **C++ STL Iterators** (Used for navigating through standard template library collections.)
- **Database Result Sets** (Iterating over database query results.)

## 🛠️ Structure

![iterator](https://raw.githubusercontent.com/asifjoardar/Design-Patterns-Palette/refs/heads/master/iterator/iterator.png)

### Explanation:

- **`Aggregate`**: Defines an interface for creating an iterator.
- **`Iterator`**: Defines the interface (`first()`, `next()`, `isDone()`, `currentItem()`) for traversal.
- **`ConcreteAggregate`**: Implements the collection and provides an iterator to traverse it.
- **`ConcreteIterator`**: Implements the traversal logic for a specific collection.
- **`Client`**: Uses the iterator to traverse the collection without accessing its internal structure.

## ⚖️ Pros and Cons

### ✅ Pros

- **Encapsulates iteration logic**, keeping it separate from collection logic.
- **Supports different traversal methods** (e.g., forward, backward, custom filters).
- **Provides a uniform interface** to traverse various data structures.

### ❌ Cons

- **May add complexity** if simple iteration suffices.
- **Can be less efficient** than direct access, especially for performance-critical applications.

## 🔗 How It Connects with Other Patterns

- **Composite Pattern**: Often used together when iterating over hierarchical structures (e.g., tree traversal).
- **Factory Pattern**: Can be used to create different iterators based on collection type.
- **Observer Pattern**: Iterators can notify observers when elements change during iteration.

## 📚 Resources to Learn More

- Books:
  - Design Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma et al. (the "Gang of Four" book).
  - Head First Design Patterns by Eric Freeman and Elisabeth Robson.

- Online Articles:
  - [Refactoring Guru - Iterator Pattern](https://refactoring.guru/design-patterns/iterator)

- Videos:
  - [Iterator Design Pattern - Christopher Okhravi](https://youtu.be/uNTNEfwYXhI)

---
