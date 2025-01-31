# Proxy Design Pattern

## 🧠 Purpose and Intent
The **Proxy Design Pattern** provides a surrogate or placeholder for another object to control access to it. A proxy controls access to the original object, allowing you to perform something either before or after the request gets through to the original object.

## 🔑 Also Known As
- **Surrogate Pattern**
- **Placeholder Pattern**

## 📝 In Simple Terms
Instead of directly interacting with an object, you go through a proxy (middleman) that decides how and when to access the real object.

## 📖 What Wikipedia Says
> "A proxy, in its most general form, is a class functioning as an interface to something else. The proxy could interface to anything: a network connection, a large object in memory, a file, or some other resource that is expensive or impossible to duplicate."

## 🌍 Real-World Analogy
Think of a **personal assistant** working for a high-profile executive:
- The assistant (proxy) handles all communication and decides which requests reach the executive.
- If someone needs a meeting, they go through the assistant first.
- The assistant can schedule, deny, or forward only important requests, ensuring efficient resource management.

## 💡 When Should You Use It?
- When you want to **control access** to a resource (e.g., authentication and authorization).
- When **loading an object is expensive**, and you want to defer its initialization until needed (lazy initialization).
- When you need to **add logging, monitoring, or caching** without modifying the actual object.
- When working with **remote objects**, where the proxy acts as a local representation of the remote object (e.g., RMI, web services).

## 🚀 Real-World Uses in Software
- **Virtual Proxy**: Used for lazy loading large resources (e.g., loading large images in an application).
- **Protection Proxy**: Controls access to sensitive objects (e.g., role-based security systems).
- **Remote Proxy**: Represents an object located on a different server or system (e.g., Java RMI, gRPC).
- **Caching Proxy**: Stores frequently accessed data to improve performance (e.g., API response caching).

## 🛠️ Structure

![proxy](https://raw.githubusercontent.com/asifjoardar/Design-Patterns-Palette/refs/heads/master/proxy/proxy.png)

### Explanation:

- **Subject**: An interface that both the RealSubject and Proxy classes implement. It defines the common interface for RealSubject and Proxy so that a Proxy can be used anywhere a RealSubject is expected.
- **RealSubject**: The real object that the proxy represents. It defines the actual behavior.
- **Proxy**: Maintains a reference to the RealSubject and controls access to it. It can perform additional operations before or after forwarding the request to the RealSubject.

## ⚖️ Pros and Cons
### ✅ Pros
- **Encapsulates additional logic** (caching, logging, security) without modifying the real object.
- **Improves performance** by implementing lazy initialization or caching.
- **Enhances security** by restricting access to sensitive objects.
- **Supports remote communication** by representing remote objects locally.

### ❌ Cons
- **Adds extra complexity** and may introduce overhead in some cases.
- **Potential performance bottleneck** if not implemented efficiently.
- **Can make debugging harder** as requests go through an extra layer.

## 🔗 How It Connects with Other Patterns
- **Decorator Pattern**: Similar in structure but focuses on adding behavior dynamically instead of controlling access.
- **Adapter Pattern**: Used to bridge incompatible interfaces, while Proxy controls access.
- **Facade Pattern**: Simplifies access to a complex system, while Proxy restricts access.

## 📚 Resources to Learn More

- Books:
  - Design Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma et al. (the "Gang of Four" book).
  - Head First Design Patterns by Eric Freeman and Elisabeth Robson.

- Online Articles:
  - [Refactoring Guru - Proxy Design Pattern](https://refactoring.guru/design-patterns/proxy)

- Videos:
  - [Proxy Design Pattern - Christopher Okhravi](https://youtu.be/NwaabHqPHeM)

---
