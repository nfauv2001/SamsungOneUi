package com.samsung.android.sdk.pen.settingui.colorpicker;

import com.samsung.android.sdk.pen.util.color.SpenNormalColorTheme;

public class SpenPickerNormalColorTheme extends SpenNormalColorTheme implements SpenIPickerColorTheme {
    SpenPickerNormalColorTheme() {
    }

    @Override
    public boolean getContentColor(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr2 == null) {
            return false;
        }
        System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
        return true;
    }
}
