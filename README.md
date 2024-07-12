## Overview
Welcome to the Vending Machine Factory Simulator! This project aims to recreate the experience of using and managing vending machines, simulating both regular and special vending machines. The simulator allows users to create, test, and manage vending machines through a text-based menu interface.

## Features

### Main Menu Options

- **Create a Vending Machine**
  - Choose to create either a Regular or a Special Vending Machine.
  - Regular Vending Machines have item slots with unique items and limited stock.
  - Special Vending Machines can prepare customizable products based on user choices.

- **Test a Vending Machine**
  - Test Vending Features: Simulate purchasing items, handling payments, and providing change.
  - Test Maintenance Features: Restock items, set prices, collect payments, and print transaction summaries.

- **Exit**
  - Properly terminates the program.

## Vending Machine Types

### Regular Vending Machine
- Item slots display available items.
- Capacity: At least 8 slots with 10 items per slot.
- Accepts different payment denominations.
- Dispenses items and provides change.
- Displays calorie information for each item.
- Maintenance features: restocking, setting prices, collecting money, and printing transaction summaries.

### Special Vending Machine
- Inherits all features of the Regular Vending Machine.
- Can prepare customizable products by combining stored items.
- Displays the preparation process for the final product.
- Example: Customizable ramen with various toppings and broths.
