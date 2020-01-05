package casanova.com.casanova;



import android.content.Context;
import android.graphics.Typeface;

public final class Font {

    static String bold = "Font/arial_6.ttf";
    static String fontawesome = "Font/ARIALN.TTF";
    static String light = "Font/futural.TTF";
    static String mistral = "Font/GOTHIC_3.TTF";
    static String regular = "Font/GOTHICB_3.TTF";
    static String segoeregular = "Font/swzcondn_1.TTF";
    static String Segoe = "Font/tt0009m_0.ttf";
    static String heading = "Font/SourceSansProSemibold.otf";
    static String sub_heading="Font/SourceSansProRegular.otf";
    static String lato="Font/LatoBold.ttf";
    static String sans="Font/SourceSansProSemibold.otf";
    static String sanslight="Font/SourceSansProLight.otf";
    static String sansBold="Font/SourceSansProBold.otf";


    public static Typeface getbold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), bold);

    }
    public static Typeface getfontawesome(Context context) {
        return Typeface.createFromAsset(context.getAssets(), fontawesome);

    }
    public static Typeface getlight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), light);

    }
    public static Typeface getmistral(Context context) {
        return Typeface.createFromAsset(context.getAssets(), mistral);

    }
    public static Typeface getregular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), regular);

    }
    public static Typeface getsegoeregular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), segoeregular);

    }
    public static Typeface getSegoe(Context context) {
        return Typeface.createFromAsset(context.getAssets(), Segoe);

    }
    public static Typeface getHeading(Context context) {
        return Typeface.createFromAsset(context.getAssets(), heading);

    }

    public static Typeface getSubheading(Context context) {
        return Typeface.createFromAsset(context.getAssets(), sub_heading);

    }

    public static Typeface getLatoHeading(Context context) {
        return Typeface.createFromAsset(context.getAssets(), lato);

    }
    public static Typeface getSansHeading(Context context) {
        return Typeface.createFromAsset(context.getAssets(), sans);

    }

    public static Typeface getSansProHeading(Context context) {
        return Typeface.createFromAsset(context.getAssets(), sanslight);

    }

    public static Typeface getSansProBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), sansBold);

    }

}
