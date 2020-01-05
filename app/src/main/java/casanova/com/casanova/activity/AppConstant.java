package casanova.com.casanova.activity;

/**
 * Created by kansagara mehul on 14-Sep-17.
 */

public class AppConstant {

    public static String OS = "ANDROID";
    public static String BUYER = "BUYER";
    public static String SELLER = "SELLER";
    public static String Licence = "Licence";
    public static String BOTH = "BOTH";
    public static String COMBO = "COMBO";

    public static String isFromNotification = "isFromNotification";

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "ah_firebase";

    public static final String SMS_ORIGIN = "IM-SWIFTR";
    public static final String OTP_DELIMITER = "Use ";
    public static final String CATAGORY = "category";
    public static final String SUBCATAGORY = "subcategory";

    public static final String LANGUAGE_HINDI = "hi";
    public static final String LANGUAGE_ENGLISH = "en";
}
