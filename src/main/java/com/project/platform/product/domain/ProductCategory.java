package com.project.platform.product.domain;

import java.util.Arrays;

public enum ProductCategory {
    TOP("상의", new ProductSubCategory[]{
            ProductSubCategory.T_SHIRT,
            ProductSubCategory.SHIRT,
            ProductSubCategory.HOODIE,
    }),
    OUTER("아우터", new ProductSubCategory[]{
            ProductSubCategory.JACKET,
            ProductSubCategory.COAT,
            ProductSubCategory.VEST,
    }),
    PANTS("바지", new ProductSubCategory[]{
            ProductSubCategory.JEANS,
            ProductSubCategory.SLACKS,
            ProductSubCategory.SHORTS,
    }),
    SHOES("신발", new ProductSubCategory[]{
            ProductSubCategory.SNEAKERS,
            ProductSubCategory.BOOTS,
            ProductSubCategory.SANDALS,
    }),
    BAG("가방", new ProductSubCategory[]{
            ProductSubCategory.HANDBAG,
            ProductSubCategory.BACKPACK,
            ProductSubCategory.TOTE_BAG,
    }),
    NONE("없음", new ProductSubCategory[]{});

    private final String viewName;
    private final ProductSubCategory[] subCategories;

    ProductCategory(String viewName, ProductSubCategory[] subCategories) {
        this.viewName = viewName;
        this.subCategories = subCategories;
    }

    public static ProductCategory findCategory(ProductSubCategory subCategory) {
        return Arrays.stream(ProductCategory.values())
                .filter(category -> hasSubCategory(category, subCategory))
                .findFirst()
                .orElse(NONE);
    }

    private static boolean hasSubCategory(ProductCategory from, ProductSubCategory searchTarget) {
        return Arrays.stream(from.subCategories)
                .anyMatch(subCategory -> subCategory == searchTarget);
    }

    @Override
    public String toString() {
        return viewName;
    }
}
