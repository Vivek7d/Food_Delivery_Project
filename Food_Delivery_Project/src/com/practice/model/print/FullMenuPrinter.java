package com.practice.model.print;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.practice.model.FoodItem;

public class FullMenuPrinter {

    public static void printMenu(List<FoodItem> foodItems) {
        if (foodItems == null || foodItems.isEmpty()) {
            System.out.println("No food items available.");
            return;
        }

        System.out.println("\n===================== FULL MENU =====================\n");

        // Step 1: Get all unique cuisines
        Set<String> cuisines = foodItems.stream()
                .map(FoodItem::getCuisineType)
                .collect(Collectors.toCollection(TreeSet::new)); // Sorted set

        for (String cuisine : cuisines) {
            System.out.println("🍽️ " + cuisine + " Cuisine:");

            // Step 2: Filter items for this cuisine
            List<FoodItem> cuisineItems = foodItems.stream()
                    .filter(item -> item.getCuisineType().equalsIgnoreCase(cuisine))
                    .collect(Collectors.toList());

            // Step 3: Get all unique categories for this cuisine
            Set<String> categories = cuisineItems.stream()
                    .map(FoodItem::getCategory)
                    .collect(Collectors.toCollection(TreeSet::new)); // Sorted set

            for (String category : categories) {
                System.out.println("\n➡️ " + category + ":\n");

                // Table header
                System.out.printf("%-5s %-25s %-12s %-12s %-15s%n",
                        "ID", "Name", "Price", "Discount", "Final Price");
                System.out.println("--------------------------------------------------------------------------");

                // Table rows
                for (FoodItem food : cuisineItems) {
                    if (food.getCategory().equalsIgnoreCase(category)) {
                        System.out.printf("%-5d %-25s ₹%-11.2f %-11.1f ₹%-14.2f%n",
                                food.getId(),
                                food.getName(),
                                food.getPrice(),
                                food.getDiscount(),
                                food.getDiscountedPrice());
                    }
                }
            }

            System.out.println(); 
        }

        System.out.println("======================================================\n");
    }
}
