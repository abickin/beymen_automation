package tests.exception;

public class ProductSearchIndexException extends Exception {

    private static  String EXCEPTION_MESSAGE = "ARAMA SIRASINDA INDEX BULUNAMADI";

    public ProductSearchIndexException() {
        super(EXCEPTION_MESSAGE);
    }
}
