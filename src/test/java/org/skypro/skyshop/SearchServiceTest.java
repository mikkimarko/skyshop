package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SearchServiceTest {

    @Test
    void searchShouldReturnEmptyCollectionWhenStorageIsEmpty() {

        StorageService storageService = mock(StorageService.class);

        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        SearchService searchService = new SearchService(storageService);

        Collection<SearchResult> result = searchService.search("чай");

        assertTrue(result.isEmpty());
    }

    @Test
    void searchShouldReturnEmptyCollectionWhenNothingFound() {

        StorageService storageService = mock(StorageService.class);

        Product product = new SimpleProduct(UUID.randomUUID(), "Хлеб", 50);

        when(storageService.getAllSearchables()).thenReturn(List.of(product));

        SearchService searchService = new SearchService(storageService);

        Collection<SearchResult> result = searchService.search("чай");

        assertTrue(result.isEmpty());
    }

    @Test
    void searchShouldReturnFoundProduct() {

        StorageService storageService = mock(StorageService.class);

        Product product = new SimpleProduct(UUID.randomUUID(), "Чай", 120);

        when(storageService.getAllSearchables()).thenReturn(List.of(product));

        SearchService searchService = new SearchService(storageService);

        Collection<SearchResult> result = searchService.search("чай");

        assertEquals(1, result.size());
    }
}