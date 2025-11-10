# 🌍 Currency Converter App  
*A modern Android app that fetches and displays live exchange rates using asynchronous JSON processing.*

## 📱 Overview
This Android application allows users to view, search, and convert between multiple currencies using **real-time data from the Exchange Rate API**.  
The project demonstrates **asynchronous processing (AsyncTask)**, **JSON parsing**, and **Material Design UI** principles — perfect for academic or practical projects.

## 🧩 Features
✅ Fetches live exchange rates from the internet  
✅ Displays all currency rates in a modern, scrollable list  
✅ Supports filtering/searching by currency code  
✅ Converts amounts between selected currencies  
✅ Runs background tasks using `AsyncTask`  
✅ Clean, Material Design-inspired user interface  
✅ Fully compatible with Android 8.0 (API 26) and above  

## 🧠 Technologies Used
| Component | Purpose |
|------------|----------|
| **Java (Android)** | Core application logic |
| **AsyncTask** | Background data loading |
| **RecyclerView + CardView** | Display currency list |
| **TextInputLayout, AutoCompleteTextView** | User input |
| **JSON Parsing (org.json)** | Data extraction |
| **ExchangeRate API** | Data source (https://open.er-api.com/v6/latest/USD) |
| **Material Design** | UI and UX components |

## 🧱 Project Structure
```
app/
├── java/com/example/currencyapp/
│   ├── MainActivity.java       
│   ├── DataLoader.java          
│   ├── Parser.java             
│   ├── CurrencyAdapter.java     
│
├── res/
│   ├── layout/
│   │   ├── activity_main.xml    
│   │   ├── item_currency.xml   
│   │
│   ├── drawable/
│   │   ├── bg_gradient.xml   
│   │   ├── bg_card_rounded.xml  
│   │   ├── ic_currency_app.xml
│   │
│   ├── xml/
│   │   ├── network_security_config.xml
│   │   ├── backup_rules.xml
|   |   ├── data_extraction_rules.xml
|   |
│   ├── values/
│   │   ├── ic_launcher_background.xml
│   │   ├── strings.xml
│   │   ├── themes.xml
│
└── AndroidManifest.xml
```

## 🚀 How It Works
1. **MainActivity** starts and triggers `DataLoader` (AsyncTask).  
2. **DataLoader** fetches the JSON from  
   `https://open.er-api.com/v6/latest/USD`.  
3. **Parser** extracts all currency rates into a `HashMap<String, Double>`.  
4. The app populates a `RecyclerView` with currency codes and rates.  
5. Users can:
   - Filter currencies via search.  
   - Tap a currency to select as “Convert From.”  
   - Choose “Convert To” currency from a dropdown.  
   - Enter an amount → result updates instantly.

## ⚙️ Installation

### Prerequisites
- Android Studio (2022.3 or later)
- Android SDK 26+
- Active Internet connection

### Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/MubaraqYusuf/CurrencyApp.git
   ```
2. Open the project in **Android Studio**.
3. Run the app on an emulator or device.

## 🔐 Permissions
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 🧰 API Used
**Open Exchange Rate API**  
🔗 [https://open.er-api.com/v6/latest/USD](https://open.er-api.com/v6/latest/USD)

Example Response:
```json
{
  "result": "success",
  "base_code": "USD",
  "rates": {
    "EUR": 0.91,
    "GBP": 0.78,
    "JPY": 151.2
  }
}
```

## 👨‍💻 Author
**Mubaraq Yusuf**    
📍 MADT Lab #5 – Async Processing

## 🏁 License
This project is open-source and available for educational use.  
You may modify and redistribute it under the MIT License.
