# Currency Exchange Android App

## Overview

This Android app provides functionality to list all currency exchanges for the EUR currency and allows users to convert a currency to another with a specified amount. The app is built using the MVVM architecture, Jetpack Compose for UI, and Hilt for dependency injection.

## Features

- **Currency Exchange List**: View a list of all currency exchanges for the EUR currency.
- **Currency Converter**: Convert a specified amount from one currency to another.
- **Pre-fetching Data**: While the splash screen is displayed, the app fetches the latest currency exchange rates to be used within the app.

## Project Structure

- ui: Contains the UI components.
- data: Contains the data models and repository classes.
- domain: Contains the use cases.
- di: Contains the dependency injection modules.

## Improvements
- Multi-module architecture: To better organize the project and separate concerns.
- TOML for dependency management: To simplify and manage dependencies efficiently.
- LeakCanary integration: To detect and fix memory leaks during development.
