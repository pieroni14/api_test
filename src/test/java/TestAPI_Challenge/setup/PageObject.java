package TestAPI_Challenge.setup;

import org.jetbrains.annotations.NotNull;

public class PageObject {
    public static final String BASE_URL = "https://dummyjson.com";
    public	static final String BASIC_TEST = "/test";
    public static final String PATH_USERS = "/users";
    public static final String AUTH_PRODUCT ="/auth/products";
    public static final String LOGIN_URL = "/auth/login";
    public static final String CREATING_PRODUCT = "/products/add";
    public static final String PRODUCTS_URL = "/products";

    @NotNull
    public static String getUri( String endpoint) {
        String uri= BASE_URL + endpoint;
        return uri;
    }



}
