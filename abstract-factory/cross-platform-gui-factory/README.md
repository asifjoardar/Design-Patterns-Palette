# Cross Platform GUI Factory

**Pattern:** [Abstract Factory](../README.md)

## 📖 The Story (the problem)
Imagine building a cross-platform application that runs on MacOS, Windows (and Linux). Each platform needs its own *family* of UI elements — Buttons and Checkboxes — that look and behave differently.

The challenge is:

* How do we create platform-specific components without duplicating code?
* How do we switch between whole families (MacOS vs Windows) without rewriting logic?
* How do we make sure we never accidentally mix a MacOS button with a Windows checkbox?

## 💡 The Solution (using the Abstract Factory pattern)
The Abstract Factory groups the creation of a related family of objects behind one factory interface. Each platform gets its own factory that produces a matching set, so the client always gets a consistent family.

* **`Button` / `Checkbox`** — the abstract products.
* **`MacOSButton` / `WindowsButton` / `LinuxButton`** and **`MacOSCheckbox` / `WindowsCheckbox` / `LinuxCheckbox`** — the concrete products.
* **`GUIFactory`** — the abstract factory, with `createButton()` and `createCheckbox()`.
* **`MacOSFactory` / `WindowsFactory` / `LinuxFactory`** — concrete factories, each building one family.
* **`Application`** — the client. It receives a `GUIFactory` and uses it, never naming a concrete product.

## 💻 In Code
```java
// Choose one platform's factory; the app builds a matching family.
GUIFactory factory = new MacOSFactory();   // or: new WindowsFactory() / new LinuxFactory()
Application app = new Application(factory);
app.paint();   // paints a button + checkbox from the same family
```

## 🛠️ UML Diagram

![Cross Platform GUI Elements uml](uml.png)

## 🎯 What We Gain
* **Single Responsibility:** each factory only knows how to build its own family.
* **Open/Closed:** add a new family (e.g. a web theme) by adding a new factory — no changes to the client.
* **Platform independence:** the client code never depends on a concrete product.
* **Family consistency:** products from one factory always belong together.

## ⚠️ Watch Out For
* **Adding a new product type is costly:** introducing, say, a `Slider` means changing the `GUIFactory` interface and every factory.
* **More classes:** a factory and a product per platform adds up.
* **Families must stay in sync:** every factory has to provide the whole set.
