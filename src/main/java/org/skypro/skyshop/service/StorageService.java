package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        products = new HashMap<>();
        articles = new HashMap<>();
        fillTestData();
    }
    private void fillTestData() {

        Product bread = new SimpleProduct(
                UUID.randomUUID(),
                "Хлеб",
                50
        );

        Product milk = new DiscountedProduct(
                UUID.randomUUID(),
                "Молоко",
                80,
                20
        );

        Product cheese = new FixPriceProduct(
                UUID.randomUUID(),
                "Сыр"
        );

        Product tea = new DiscountedProduct(
                UUID.randomUUID(),
                "Чай",
                120,
                10
        );

        products.put(bread.getId(), bread);
        products.put(milk.getId(), milk);
        products.put(cheese.getId(), cheese);
        products.put(tea.getId(), tea);

        Article article1 = new Article(
                UUID.randomUUID(),
                "Как выбрать чай",
                "Чай бывает черный, зеленый и травяной"
        );

        Article article2 = new Article(
                UUID.randomUUID(),
                "Все о молоке",
                "Молоко бывает коровье и растительное"
        );

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
    }
    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }
    public Collection<Searchable> getAllSearchables() {

        List<Searchable> result = new ArrayList<>();

        result.addAll(products.values());
        result.addAll(articles.values());

        return result;
    }
}
