package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.skypro.skyshop.exeption.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasketServiceTest {

    @Test
    void addProductShouldThrowExceptionWhenProductNotExists() {

        ProductBasket basket = mock(ProductBasket.class);
        StorageService storage = mock(StorageService.class);

        UUID id = UUID.randomUUID();

        when(storage.getProductById(id)).thenReturn(Optional.empty());

        BasketService basketService = new BasketService(basket, storage);

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(id));
    }

    @Test
    void addProductShouldCallBasketAddProduct() {

        ProductBasket basket = mock(ProductBasket.class);
        StorageService storage = mock(StorageService.class);

        UUID id = UUID.randomUUID();

        Product product = new SimpleProduct(id, "Чай", 100);

        when(storage.getProductById(id)).thenReturn(Optional.of(product));

        BasketService basketService = new BasketService(basket, storage);

        basketService.addProduct(id);

        verify(basket).addProduct(id);
    }

    @Test
    void getUserBasketShouldReturnEmptyBasket() {

        ProductBasket basket = mock(ProductBasket.class);
        StorageService storage = mock(StorageService.class);

        when(basket.getProducts()).thenReturn(Collections.emptyMap());

        BasketService basketService = new BasketService(basket, storage);

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getItems().isEmpty());
    }

    @Test
    void getUserBasketShouldReturnBasketWithProducts() {

        ProductBasket basket = mock(ProductBasket.class);
        StorageService storage = mock(StorageService.class);

        UUID id = UUID.randomUUID();

        Product product = new SimpleProduct(id, "Хлеб", 50);

        Map<UUID, Integer> map = new HashMap<>();
        map.put(id, 2);

        when(basket.getProducts()).thenReturn(map);

        when(storage.getProductById(id)).thenReturn(Optional.of(product));

        BasketService basketService = new BasketService(basket, storage);

        UserBasket userBasket = basketService.getUserBasket();

        assertEquals(1, userBasket.getItems().size());
        assertEquals(100, userBasket.getTotal());
    }
}
