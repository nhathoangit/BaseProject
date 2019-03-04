package appscyclone.com.base.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/*
 * Created by NhatHoang on 20/04/2018.
 */
public class KeyboardUtils {
    @SuppressLint("ClickableViewAccessibility")
    public static void setupUI(View view, final Activity activity) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                hideSoftKeyboard(activity);
                return false;
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (view != null && inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if (view instanceof EditText) {
                    EditText editText = (EditText) view;
                    clearFocusEditText(new EditText[]{editText});
                }
            }
        } catch (Exception ignored) {
        }
    }

    private static void clearFocusEditText(EditText[] editTexts) {
        for (EditText editText : editTexts)
            editText.clearFocus();
    }
}
