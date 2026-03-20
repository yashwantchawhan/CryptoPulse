# 🚀 CryptoPulse

A modern Android app to track crypto markets, manage portfolio, and explore wallet activity — built using Clean Architecture, Jetpack Compose, and Web3 APIs.

## 📱 Overview

CryptoPulse is a lightweight crypto tracking application that allows users to:

- 📊 View real-time cryptocurrency prices
- 💼 Track personal portfolio with profit/loss insights
- 🔗 Explore blockchain wallet balances and transactions

This project was built to understand how real-world crypto platforms (like OKX, Binance) handle market data, portfolio management, and blockchain interactions.

## 🧠 Features

### 📊 Market

- View top cryptocurrencies
- Real-time price updates
- 24-hour price change
- Clean, responsive UI

### 💼 Portfolio

- Add custom holdings (BTC, ETH, etc.)
- Track:
  - Current value
  - Profit/Loss (PnL)
  - Percentage gain/loss
- Persistent storage using Room

### 🔗 Wallet Viewer

- Enter Ethereum wallet address
- Fetch:
  - Wallet balance (ETH)
  - Recent transactions
- Blockchain data via explorer APIs

## 🏗️ Architecture

This project follows **Clean Architecture + MVVM** principles:

```
Presentation (Compose UI + ViewModel)
        ↓
Domain (UseCases + Models)
        ↓
Data (Repository + API + DB)
```

### Key Design Decisions:

- Separation of concerns (UI, business logic, data)
- Reactive state handling using Flow
- Scalable modular structure
- Testable components

## ⚙️ Tech Stack

### 🧩 Core

- Kotlin
- Coroutines + Flow
- Jetpack Compose
- Material 3

### 🏛️ Architecture

- MVVM
- Clean Architecture
- Repository Pattern

### 🔌 Dependencies

- Hilt (Dependency Injection)
- Retrofit + OkHttp (Networking)
- Room (Local DB)
- DataStore (Preferences)
- Coil (Image loading)

## 🌐 APIs Used

### Market Data

**[CoinGecko API](https://www.coingecko.com/en/api)**

Used for:
- Coin prices
- Market data

### Wallet Data

**[Etherscan API](https://etherscan.io/apis)**

Used for:
- Wallet balance
- Transaction history

## 🔐 Crypto Concepts Covered

This app demonstrates key crypto concepts:

- Wallet address & public key usage
- Blockchain transaction structure
- Unit conversion (Wei → ETH)
- Market price vs portfolio valuation
- Profit & Loss (PnL) calculations

## 🧪 Example Logic

### Portfolio Value

```
currentValue = quantity × currentPrice
```

### Profit/Loss

```
pnl = (currentPrice - avgBuyPrice) × quantity
```

### ETH Conversion

```
1 ETH = 10^18 wei
```

## 📂 Project Structure

```
com.cryptopulse
│
├── data
│   ├── remote (API, DTOs)
│   ├── local (Room DB)
│   ├── repository
│   └── mapper
│
├── domain
│   ├── model
│   ├── repository
│   └── usecase
│
├── presentation
│   ├── market
│   ├── portfolio
│   ├── wallet
│   └── navigation
│
├── di (Hilt modules)
└── core
```

## ▶️ Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/yourusername/cryptopulse.git
```

### 2. Open in Android Studio

### 3. Add API Key

Update `Constants.kt`:

```kotlin
ETHERSCAN_API_KEY = "your_api_key_here"
```

### 4. Run the app 🚀

## 🧩 Future Improvements

- Real-time price updates via WebSocket
- Advanced charts (candlestick)
- Multi-chain wallet support (BTC, SOL)
- Push notifications for price alerts
- Paging for large datasets
- Offline-first caching strategy

## 🧪 Testing

Unit tests for:
- UseCases
- Mappers
- ViewModels
