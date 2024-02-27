package tests.exception;

public class ExcelNotFoundException extends Exception {

    private static  String EXCEPTION_MESSAGE = "OKUNACAK %S% DOSYASI BULUNAMADI";
// buna sonra bakacaz %s% bursaası parametrik olabilir sonraki mevzu ama mutlaka anla
    public ExcelNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }
}
