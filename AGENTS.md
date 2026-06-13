# AGENTS.md

Operating guide for AI coding agents working in **Design Patterns Palette**. Read this first.
It is self-contained; the linked files are the canonical source of truth for any detail.

## Project

**Design Patterns Palette** — *"Your ultimate cookbook of design patterns, serving code and
clarity on a platter."* A curated, open-source collection of design patterns taught through
**practical, real-world examples**. The whole point is to avoid the abstract, clichéd examples
found elsewhere (no cars, no pizzas): every example should model a believable scenario (payment
strategies, deployment configuration, event subscriptions) that makes *why, when and how* to use
the pattern obvious. Good work here is a clear, runnable, well-documented example with teaching
value. Licensed under MIT.

## Tech stack

- **Language:** Java 21
- **Build:** Maven, multi-module reactor (`groupId org.asif`, version `1.0-SNAPSHOT`)
- **Dependencies (managed in the root `pom.xml`):** Spring Boot 3.4.3 (BOM), Gson 2.12.1,
  SLF4J 2.0.16 + Logback 1.5.16, Lombok (`provided`), JUnit 5. Individual examples may add their
  own deps locally (e.g. `strategy/payment` uses Jackson).
- **Quality gate:** Checkstyle (`checkstyle.xml`).

## Repository structure

```
Design-Patterns-Palette/
├── pom.xml                  # root reactor; lists every pattern module
├── checkstyle.xml           # enforced coding rules (read before writing code)
├── README.md                # human overview + pattern coverage/status table
├── CONTRIBUTING.md          # full contribution workflow
├── <pattern-name>/          # one module per design pattern (e.g. strategy, builder)
│   ├── pom.xml
│   ├── README.md            # explains the pattern
│   └── <example-name>/      # a concrete example (sub-module; parent = the pattern)
│       ├── pom.xml
│       ├── README.md        # explains this example
│       ├── <example>.puml   # PlantUML diagram + exported uml.png
│       └── src/
│           ├── main/java/org/asif/...      # source (always under org.asif)
│           └── main/resources/data/*.json  # optional fixtures
```

The pattern coverage/status table (implemented vs. not) lives in `README.md` — keep it current.

## Anatomy of an example

Mirror this skeleton when creating or extending an example:

- **`org.asif.Main`** — the runnable entry point. Idiom: `public final class Main` with a
  private constructor that throws `UnsupportedOperationException`; `main` drives a small demo.
- **Pattern subpackage(s)** — the interface + implementations, named descriptively after the
  domain (e.g. `strategies/PayStrategy`, `PayByCreditCard`, `PayByPayPal`) — never generic
  names like `Handler1`.
- **Domain subpackage(s)** — context/model classes (e.g. `order/Order`).
- **`util/`** — helpers and mock data (e.g. `DummyData`, `MockDataLoader`).
- **`resources/data/*.json`** — optional fixtures.
- **`README.md` + `.puml`/`uml.png`** — explanation and diagram for the example. Write the
  README to the [Example README template](CONTRIBUTING.md#example-readme-template): lead with the
  real-life problem in plain language, map the pattern roles to this example's real class names,
  and include a short snippet copied from the source.

## Build, run & test

```bash
mvn clean install                          # build (and test) the whole reactor
mvn -pl <pattern>/<example> -am clean package   # build one example + its deps
mvn test                                   # run JUnit 5 tests
mvn checkstyle:check                       # verify coding rules — must pass
```

Run an example (the example POMs declare **no** exec/spring-boot plugin, so build first; the
runtime classpath must include dependencies such as SLF4J/Logback):

```bash
mvn -q -pl strategy/payment -am clean package
cd strategy/payment
mvn -q dependency:build-classpath -Dmdep.outputFile=cp.txt
java -cp "target/classes:$(cat cp.txt)" org.asif.Main
```

Or just run the `Main` class from your IDE (simplest — it puts dependencies on the classpath for
you). Plain `java -cp target/classes …` will fail at runtime with `NoClassDefFoundError` for any
example that logs via SLF4J. (`mvn exec:java -Dexec.mainClass=org.asif.Main` may work as a
convenience but is not configured, so don't rely on it.)

## Coding rules (must pass Checkstyle)

`mvn checkstyle:check` runs against main **and** test sources; only files under `resources/` are
suppressed. Severity is `warning` and the build treats warnings as failures, so **zero
violations** is the bar. Concretely:

- **Naming:** standard Java conventions for types, methods, members, constants, parameters,
  packages. All code lives under `org.asif`.
- **Imports:** no unused, redundant, or illegal imports.
- **Size:** methods ≤ 60 lines; ≤ 8 parameters.
- **Structure:** always use braces (`NeedBraces`); correct modifier order; `switch` must have a
  `default`; no multiple variable declarations on one line; no empty statements.
- **Class design:** utility classes are `final` with a hidden (private) constructor
  (`FinalClass`, `HideUtilityClassConstructor`); fields are non-public (`protected` allowed).
- **No magic numbers** (annotation values and `hashCode` are exempt) — name constants.
- **Misc:** uppercase `L` for `long` literals; files end with a newline; `TODO` comments are
  flagged (resolve them).
- **Logging:** use SLF4J for library/pattern code rather than `System.out`; interactive demos in
  `Main` may print to the console. Lombok is available where it reduces boilerplate.

## Adding a pattern or example

Condensed from `CONTRIBUTING.md`:

1. Open the matching issue (New Pattern / New Example template) and wait for maintainer approval.
2. Branch off the latest `master`: `git checkout -b feature/<your-feature-name>`.
3. Add the module/example following the structure above — `pom.xml`, source under `org.asif`,
   a `README.md`, and a `.puml` diagram.
4. Run `mvn checkstyle:check` and `mvn -pl <path> -am package`; fix everything.
5. If a pattern's implementation status changes, update the coverage table in `README.md`.
6. Open a PR linked to the issue, with a clear description and the relevant labels
   (e.g. `new pattern`, `new example`).

## Agent guardrails

- **Stay real-world.** Examples must model a believable scenario and teach the pattern clearly.
- **Stay scoped.** Touch only the module/example you're working on; don't refactor unrelated code.
- **Keep deps lean.** Prefer what the root POM already manages; add new dependencies only when
  genuinely needed, and at the example level.
- **Don't claim success you haven't verified.** Run `mvn checkstyle:check` and build/run the
  affected example before saying it works; end every file with a newline.

## Sources of truth

- [`README.md`](README.md) — project overview and pattern coverage table
- [`CONTRIBUTING.md`](CONTRIBUTING.md) — full contribution workflow and issue templates
- [`checkstyle.xml`](checkstyle.xml) — the exact, enforced coding rules
