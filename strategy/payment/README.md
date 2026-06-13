# Payment System

**Pattern:** [Strategy](../README.md)

## 📖 The Story (the problem)
Imagine you are building an online store where customers can pay using either a **Credit Card** or **PayPal**. Each payment method has its own steps.

At first you hardcode all the payment logic inside the `Order` class. That quickly gets painful:

* Adding a new payment method (say Google Pay) means editing `Order` again.
* The `Order` class keeps growing and is easy to break.
* It gets harder to test each payment method on its own.
* It breaks the **Open/Closed Principle** — you can't add new behavior without changing old code.

## 💡 The Solution (using the Strategy pattern)
The Strategy pattern lets you pull each payment method out of `Order` and into its own class, all behind one common interface. `Order` just picks a strategy and runs it — it never needs to know the details.

* **`PayStrategy`** — the common interface every payment method implements. It declares:
  * `pay(int amount)`: handles the payment.
  * `collectPaymentDetails()`: gathers the information needed from the user.
* **`PayByCreditCard` / `PayByPayPal`** — the concrete strategies. Credit Card asks for card number, expiry, CVV and PIN; PayPal asks for email and password.
* **`Order`** — the context. It works with the `PayStrategy` interface only, so switching payment methods needs no change to `Order`.

## 💻 In Code
```java
// Pick any payment method — Order doesn't care which one.
PayStrategy strategy = new PayByCreditCard();   // or: new PayByPayPal()

Order order = new Order();
order.processOrder(strategy);                   // collects details via the chosen strategy

if (strategy.pay(order.getTotalCost())) {
    order.setClosed();
}
```

## 🛠️ UML Diagram

![payment uml](uml.png)

## 🎯 What We Gain
* **Cleaner code:** payment logic is separated from the order logic, so both are easier to read.
* **Easy to add new payment methods:** add a new strategy class without touching existing code.
* **Flexible and reusable:** the same strategies can be reused across the system.
* **Better testing:** each strategy can be tested on its own.

## ⚠️ Watch Out For
* **More classes to manage:** each payment method is its own class.
* **Learning curve:** developers new to the pattern need a moment to get it.
* **Slight overhead:** switching strategies adds a little indirection — negligible for most apps.
