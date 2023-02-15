package co.com.sofka.catalog.controller;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String id) {
        super("Could not find the item " + id);
    }
}
