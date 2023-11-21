package com.project.platform.product.domain;

public enum ProductSubCategory {
    // Top
    T_SHIRT("티셔츠"),
    SHIRT("셔츠"),
    HOODIE("후드"),


    // Outer
    JACKET("재킷"),
    COAT("코트"),
    VEST("베스트"),

    // Pants
    JEANS("청바지"),
    SLACKS("슬랙스"),
    SHORTS("반바지"),

    // Shoes
    SNEAKERS("스니커즈"),
    BOOTS("부츠"),
    SANDALS("샌들"),

    // Bag
    HANDBAG("핸드백"),
    BACKPACK("백팩"),
    TOTE_BAG("토트백");

    private final String viewName;

    ProductSubCategory(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String toString() {
        return viewName;
    }
}
