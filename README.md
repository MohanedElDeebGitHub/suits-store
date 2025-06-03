# suits-store
College group assignment for a suits store that focuses on implementing 10 Design Patterns with basic GUI

## **1. Singleton Pattern**
**Where:** `model.cart.ShoppingCart`
**Implementation:** Uses eager initialization with a private static instance and private constructor. The `getInstance()` method provides global access to the single cart instance.

## **2. Decorator Pattern**
**Where:** `model.suit` package
**Implementation:** 
- `SuitDecorator` is the abstract decorator
- `FancySuit` and `TayloredSuit` are concrete decorators that wrap `BasicSuit`
- Allows dynamic addition of features/costs to suits (basic → fancy → tailored)

## **3. Strategy Pattern**
**Where:** `model.paymentStrategy` package
**Implementation:**
- `PaymentStrategy` interface defines the strategy
- `CreditCardPayment`, `PayPalPayment` are concrete strategies
- `PaymentContext` uses the strategy and can switch between payment methods at runtime

## **4. State Pattern**
**Where:** `model.paymentStrategy.payStates` package
**Implementation:**
- `PayState` interface defines state behavior
- `PendingPayState`, `DonePayState`, `FailedPayState` are concrete states
- `PaymentContext` maintains current state and delegates state-specific behavior

## **5. Prototype Pattern**
**Where:** `model.Prototype` interface, implemented by multiple classes
**Implementation:**
- Base `Prototype` interface with `clone()` method
- `ShoppingCart`, `Product`, and suit classes implement it
- Allows creating copies of objects without knowing their concrete classes

## **6. Builder Pattern**
**Where:** `service.commonCarts` package
**Implementation:**
- `SuitCartBuilder` interface defines building steps
- `FancyMeetingBuilder` is concrete builder for creating predefined cart combinations
- `CommonCartsDirector` orchestrates the building process

## **7. Proxy Pattern**
**Where:** `service.connectionProxy` package
**Implementation:**
- `DatabaseAccess` interface defines access methods
- `DatabaseAccessProxy` controls access to `RealDatabaseAccessVerifier`
- Provides lazy initialization and access control to database operations

## **8. Adapter Pattern**
**Where:** `model.paymentStrategy.specialPay` package
**Implementation:**
- `InstapayAdapter` adapts the incompatible `Instapay` class to work with the `PaymentStrategy` interface
- Converts `pay(amount, bank)` to `pay(amount)` interface

## **9. Command Pattern**
**Where:** `service.OrderQOLServices` package
**Implementation:**
- `Command` interface defines execution method
- `SameOrderAddition` is concrete command for adding products to cart
- `QOLInvoker` executes commands, providing decoupling between requester and receiver

## **10. Facade Pattern**
**Where:** `service.middleware.SystemsHandler`
**Implementation:**
- Provides simplified interface to complex validation subsystems
- Coordinates between `PayValidator` and `ProductValidator`
- Hides complexity of system validation from clients

## **Pattern Interactions:**

**Complex Combinations:**
- **Strategy + State:** `PaymentContext` uses both patterns together - strategy for payment method selection and state for payment status tracking
- **Decorator + Prototype:** Suit decorators implement prototype for cloning decorated objects
- **Singleton + Command:** Shopping cart (singleton) is manipulated through commands
- **Adapter + Strategy:** `InstapayAdapter` adapts external payment system to fit into strategy pattern

**GUI Patterns (Implicit):**
- **Observer Pattern:** Present in Swing components (button listeners, window events)
- **MVC Pattern:** Separation between model (business logic), view (GUI), and implicit controllers in event handlers