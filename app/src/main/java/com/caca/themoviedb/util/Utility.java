package com.caca.themoviedb.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author caca rusmana
 */
public class Utility {

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }
}
