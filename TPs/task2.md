# Task 2: Implement the First UI in MainActivity

## Code Explanation

The codebase is structured around Sougna application using **MVVM Architecture**. Here are the key components:

1. **ProductViewModel**: Handles the business logic for products.
2. **CategoryViewModel**: Manages the logic for product categories.
3. **ProductRegistry**: Acts as a registry for products.
4. **Category & Product Models**: Define the data structure for categories and products.

## Task

Your task is to implement the first UI in `MainActivity` based on the design provided in `FIrstUI.jpg`. The UI should include:

- A list of products.
- A navigation bar to switch between categories.
- A product detail view when a product is selected.
- A search bar to filter products by name or category.

### Additional Requirements

1. **Add More Products and Categories**:
   - Add at least 5 more products and 2 more categories to the mock data in `ProductRegistry.kt` and `CategoryRegistry.kt`.
   - Ensure the new products and categories are displayed in the UI.

2. **Implement Search Functionality**:
   - Add a search bar to the top of the `MainActivity` UI.
   - Implement functionality to filter the product list based on the search query (e.g., by product name or category).

## Instructions

1. Open `MainActivity.kt`.
2. Implement the UI layout in `MainActivity`.
3. Use the `ProductViewModel` to fetch and display products.
4. Ensure the UI matches the design in `FIrstUI.jpg`.

## FirstUI

- ![FIrstUI.jpg](TPs/FIrstUI.jpg)

## Resources

- [FIrstUI.jpg](TPs/FIrstUI.jpg)

Good luck!
