# 🏦 Banking System — POO Project

A simple banking system developed in Java as a **Object-Oriented Programming (OOP)** course project. The system simulates basic banking operations through a graphical interface using `JOptionPane`.

---

## 🚀 Features

- ✅ Create multiple bank accounts (**Conta Poupança** and **Conta Corrente**) with account number (`nroConta`) and owner information
- ✅ Deposit funds into a specific account
- ✅ Withdraw funds with account-specific rules (overdraft `limite` for checking accounts, non-negative balance for savings accounts)
- ✅ Transfer funds between accounts (**Conta Corrente** only, with origin, destination, and balance/limit validation)
- ✅ View account statement including account type and update/operation history
- ✅ Manager area:
  - View balances by account (account number, account type, owner name, balance)
  - View total balance across all customer accounts
  - Update all accounts (`Atualizar Contas`) with interest rate and monthly fee
  - Enable/disable banking operations
- ✅ Graphical interface using `JOptionPane`

---

## 🛠️ Technologies

- **Language:** Java
- **IDE:** IntelliJ IDEA
- **UI:** `javax.swing.JOptionPane`

---

## 📁 Project Structure

```
banking-system-poo/
└── src/
    └── br/
        └── com/
            └── banco/
                ├── model/
                │   ├── Proprietario.java    # Owner entity
                │   ├── ContaBancaria.java   # Abstract bank account base class
                │   ├── ContaPoupanca.java   # Savings account
                │   └── ContaCorrente.java   # Checking account
                └── app/
                    └── Banco.java           # Main class with menu
```

---

## 🧩 Classes Overview

### `Proprietario` (Owner)
Represents the bank account owner.

| Member | Type | Description |
|---|---|---|
| `nome` | `String` | Owner's full name |
| `cpf` | `String` | Owner's CPF (Brazilian tax ID) |
| `endereco` | `String` | Owner's address |
| `email` | `String` | Owner's e-mail |
| `toString()` | `String` | Returns formatted owner information |

---

### `ContaBancaria` (Abstract Bank Account)
Abstract base class representing a bank account. Uses **composition** with `Proprietario`.

| Member | Type | Description |
|---|---|---|
| `nroConta` | `String` | Account number |
| `proprietario` | `Proprietario` | The account owner |
| `saldo` | `double` | Current balance |
| `depositar(double valor)` | `String` | Non-abstract method that adds funds to the balance |
| `sacar(double valor)` | `abstract String` | Abstract method to withdraw funds according to account rules |
| `atualizar(double taxa)` | `abstract String` | Abstract method to update balance with interest rate |
| `extrato()` | `String` | Returns full account statement with type and operation history |

---

### `ContaPoupanca` (Savings Account)
Extends `ContaBancaria`.
- `sacar(double valor)`: allows withdrawal only when `saldo - valor >= 0`.
- `atualizar(double taxa)`: updates balance using `2 * taxa`.

---

### `ContaCorrente` (Checking Account)
Extends `ContaBancaria` and adds `limite` (`double`) and `mensalidade` (`double`).
- `sacar(double valor)`: allows withdrawal up to `saldo + limite`.
- `atualizar(double taxa)`: updates balance with `taxa` and subtracts `mensalidade`.
- `transferir(ContaBancaria destino, double valor)`: transfers funds to another account (exclusive to `ContaCorrente`).

---

### `Banco` (Bank)
Main class containing the interactive menu and system logic.

| Member | Type | Description |
|---|---|---|
| `operacoesHabilitadas` | `static boolean` | Enables/disables banking operations |
| `contas` | `static List<ContaBancaria>` | Collection of registered bank accounts |
| `main(String[] args)` | `void` | Application entry point |

---

## 📋 Menu Options

```
1 - Create account (Conta Poupanca / Conta Corrente)
2 - Withdraw
3 - Deposit
4 - View statement
5 - Transfer (Conta Corrente only)
6 - Manager area
    1 - Balances per account
    2 - Total balance of all clients
    3 - Update accounts (Atualizar Contas)
    4 - Enable/disable operations
    5 - Back
7 - Exit
```

---

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/banking-system-poo.git
   ```
2. Open the project in **IntelliJ IDEA**
3. Run the `Banco.java` class (`src/br/com/banco/app/Banco.java`)

---

## 📚 OOP Concepts Applied

| Concept | Where |
|---|---|
| **Encapsulation** | Private attributes + getters/setters in all classes |
| **Abstraction** | Abstract class `ContaBancaria` with abstract `sacar`, `atualizar`, and `getTipoConta` |
| **Inheritance & Polymorphism** | `ContaPoupanca` and `ContaCorrente` extending `ContaBancaria` |
| **Constructor** | `Proprietario`, `ContaBancaria`, `ContaPoupanca`, `ContaCorrente` |
| **Composition** | `ContaBancaria` has-a `Proprietario` |
| **Static field** | `operacoesHabilitadas` and `contas` in `Banco` |
| **Method override** | `toString()` in `Proprietario`; `sacar()`, `atualizar()`, `getTipoConta()` in subclasses |

---

## 👤 Author

Developed as a course project for the **Object-Oriented Programming** subject.
