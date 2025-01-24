# 🚀 Deployment Configuration System

---

📖 **The Story Behind the Problem**

In modern software systems, configuring deployments for various environments (e.g., Development, Staging, Production) often requires distinct parameter sets, such as region, scaling options, network policies, and storage types.

The challenge is:

- How do we create flexible, environment-specific deployment configurations without duplicating code?
- How can we simplify the process of defining and reusing deployment logic for new environments as they emerge?

The Builder Design Pattern solves this problem by separating the construction of complex objects (like deployment configurations) from their representation. This allows you to create configurations step-by-step and reuse the same construction process for different configurations.

---

💡 **Solution**

The Builder Design Pattern provides a structured way to construct deployment configurations through step-by-step assembly while keeping the client code independent of the object construction logic.

---

## Practical Flow in This Example:

1. **Core Configuration Object**:
    - The `DeploymentConfiguration` class defines all possible deployment parameters (e.g., `region`, `environment`, `maxInstances`, `networkPolicy`, etc.).
    - It ensures immutability and encapsulates deployment settings in a single object.

2. **Builder Interface**:
    - `DeploymentConfigurationBuilder` defines a common interface for building configurations.
    - Each method allows incremental construction of specific parameters.

3. **Concrete Builders**:
    - `DevelopmentDeploymentConfigurationBuilder`, `StagingDeploymentConfigurationBuilder`, and `ProductionDeploymentConfigurationBuilder` implement the builder interface to define environment-specific default values.

4. **Director**:
    - The `DeploymentDirector` class orchestrates the construction process, ensuring consistency and reusability of configuration assembly logic.

---

## 🛠️ UML Diagram 

![Deployment Configuration System uml](uml.png)

---

## 💻 Example Usage

Here’s how you can use the Deployment Configuration system:

```java
public class Main {
    public static void main(String[] args) {
        ProductionDeploymentConfigurationBuilder productionDeploymentConfigurationBuilder =
                new ProductionDeploymentConfigurationBuilder();

        productionDeploymentConfigurationBuilder
                .environment(DeploymentEnvironment.PRODUCTION)
                .region(Region.US_WEST_2)
                .maxInstances(10)
                .minInstances(5)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.OPEN)
                .loggingLevels(List.of(LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR))
                .storageType(StorageType.ARCHIVE);

        DeploymentConfiguration configuration2 = productionDeploymentConfigurationBuilder.build();
        Deployment deployment = new Deployment(configuration2);
        deployment.deploy();
    }
}
```

---

## 🎯 What We Achieve 

**1. Single Responsibility Principle:**
Each concrete builder is responsible only for constructing deployment configurations specific to its environment (e.g., Development, Staging, Production).

**2. Open/Closed Principle:**
Adding a new environment (e.g., Testing) is as simple as creating a new builder class without modifying existing ones.

**3. Code Reusability:**
The director allows for reusable configuration processes, while client code remains clean and focused on deploying the configurations.

**4. Better Flexibility:**
The Builder pattern enables dynamic, step-by-step assembly of configurations while keeping the client code decoupled from the underlying construction logic.


