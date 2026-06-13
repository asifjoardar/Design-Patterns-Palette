# Cross Platform GUI Elements

**Pattern:** [Factory Method](../README.md)

## 📖 The Story (the problem)
Imagine you are building an application that runs on multiple platforms:

* Web browsers (using HTML buttons)
* Desktop environments (using native Windows buttons)

Each platform needs a different button, but the rest of your app logic should stay the same. The challenge:

* How do we avoid rewriting the core logic every time we support a new platform?
* How do we keep things consistent while still producing platform-specific buttons?

## 💡 The Solution (using the Factory Method pattern)
Instead of creating the platform-specific button directly with `new`, we let a subclass decide which button to build.

* **`Button`** — the product interface every button implements (`render`, `onClick`).
* **`HtmlButton` / `WindowsButton`** — the concrete products, one per platform.
* **`Dialog`** — the creator. Its `renderWindow()` calls the **factory method** `createButton()` and uses whatever button comes back.
* **`HtmlDialog` / `WindowsDialog`** — concrete creators that override `createButton()` to return their own button.

Adding a new platform means adding one new `Dialog` subclass — the rendering logic in `Dialog` never changes.

## 💻 In Code
```java
// Each dialog decides which button to create via its factory method.
Dialog dialog = new HtmlDialog();   // or: new WindowsDialog()
dialog.renderWindow();              // renders using the button from createButton()
```

## 🛠️ UML Diagram

![Cross Platform GUI Elements uml](uml.png)

## 🎯 What We Gain
* **Single Responsibility:** `Dialog` handles rendering; button creation lives in the subclasses.
* **Open/Closed:** add a new button (e.g. a Mac button) by adding a new subclass, not editing old code.
* **Code reuse:** the rendering logic in `Dialog` is shared across every platform.
* **Scalability:** new products slot in without touching the core logic.

## ⚠️ Watch Out For
* **More complexity:** extra subclasses can be overkill for very simple cases.
* **Harder to trace:** creation logic is spread across subclasses.
* **Creator/product coupling:** each dialog is tied to its button type.
