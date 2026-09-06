package com.javaremotero69.spring_fe.example_5;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public
class Order {

    @NotBlank(message = "Product name is required!")
    private String productName;

    @PositiveOrZero(message = "Quantity must be positive or at least 0!")
    @Max(value = 899, message = "Max quantity is 899!")
    private int quantity;
}
