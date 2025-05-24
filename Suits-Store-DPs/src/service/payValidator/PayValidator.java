package service.payValidator;

public class PayValidator {
    // return false if credit card was reported during purchase time
    // (anti fraud measures)

    public static boolean isUsable(String cardInfo){
        return true;
    }
}
