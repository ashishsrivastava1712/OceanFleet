# OceanFleet — Commercial Vessel Performance Registry

An operational maritime records management console created in Java to track, search, and benchmark commercial cargo and transport vessel velocities.

---

### Key Capabilities

- **Vessel Data Ingestion:** Collects vessel records (ID, Name, Speed, Category) via structured delimiter parsing (`ID:Name:Speed:Type`).
- **Targeted Record Query:** Direct linear retrieval of ship records based on unique vessel identification codes.
- **Velocity Benchmarking:** Dynamic evaluation of the highest recorded average cruising speed across all logged ships, returning all vessels matching that benchmark.
- **Architectural Encapsulation:** Employs defensive copying techniques on all mutable collections to safeguard internal repository state.

---

### Technical Specifications

```
Runtime Environment : Java SE 17 (OpenJDK)
Build Management    : Apache Maven 3.9+
Verification Suite  : JUnit Jupiter 5
Collection Strategy : java.util.ArrayList (Indexed Traversal)
Git Branching Model : GitFlow (develop / feature branches)
```

---

### Data Entity Model

| Attribute | Java Type | Description |
|:---|:---:|:---|
| `vesselId` | `String` | Alphanumeric unique registration code |
| `vesselName` | `String` | Commercial vessel name or callsign |
| `averageSpeed` | `double` | Recorded cruise velocity in knots |
| `vesselType` | `String` | Operational classification (Cargo, Tanker, Cruise, etc.) |

---

### Development Stages & Milestones

The project was implemented in an iterative GitFlow lifecycle:

1. **`feature/UC1-create-vessel-model`** — Defines the core `Vessel` POJO with full encapsulation.
2. **`feature/UC2-store-vessel-records`** — Introduces `VesselUtil` providing collection-backed list storage.
3. **`feature/UC3-search-vessel-by-id`** — Implements ID-based linear search query functionality.
4. **`feature/UC4-high-performance-vessels`** — Adds peak velocity identification and multi-vessel filter algorithms.
5. **`feature/UC5-console-user-interface`** — Implements `UserInterface` for interactive command-line operations.
6. **`feature/UC6-oops-refactoring`** — Hardens OOP design by implementing defensive copying for list getters and setters.

All milestones are continuously verified through unit tests prior to integration into `develop`.
