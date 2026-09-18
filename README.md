# 🏦 Banking System — POO Project

A simple banking system developed in Java as a **Object-Oriented Programming (OOP)** course project. The system simulates basic banking operations through a graphical interface using `JOptionPane`.

---

## 🚀 Features

- ✅ Create a bank account with owner information
- ✅ Deposit funds into the account
- ✅ Withdraw funds with balance validation
- ✅ View account statement
- ✅ Manager area to enable/disable banking operations
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
                │   ├── Proprietario.java   # Owner entity
                │   └── Conta.java          # Bank account entity
                └── app/
                    └── Banco.java          # Main class with menu
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

### `Conta` (Account)
Represents a bank account. Uses **composition** with `Proprietario`.

| Member | Type | Description |
|---|---|---|
| `proprietario` | `Proprietario` | The account owner |
| `saldo` | `double` | Current balance |
| `depositar(double valor)` | `String` | Adds funds to the balance |
| `sacar(double valor)` | `String` | Withdraws funds (validates balance) |
| `extrato()` | `String` | Returns full account statement |

---

### `Banco` (Bank)
Main class containing the interactive menu and system logic.

| Member | Type | Description |
|---|---|---|
| `operacoesHabilitadas` | `static boolean` | Enables/disables banking operations |
| `main(String[] args)` | `void` | Application entry point |

---

## 📋 Menu Options

```
1 - Create account
2 - Withdraw
3 - Deposit
4 - View statement
5 - Manager area (enable/disable operations)
6 - Exit
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
| **Constructor** | `Proprietario(nome, cpf, endereco, email)` |
| **Composition** | `Conta` has-a `Proprietario` |
| **Static field** | `operacoesHabilitadas` in `Banco` |
| **Method override** | `toString()` in `Proprietario` |

---

## 👤 Author

Developed as a course project for the **Object-Oriented Programming** subject.
