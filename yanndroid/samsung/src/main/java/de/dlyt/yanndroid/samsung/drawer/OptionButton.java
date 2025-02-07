package de.dlyt.yanndroid.samsung.drawer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.Styleable;
import androidx.annotation.Nullable;
import androidx.appcompat.util.SeslMisc;
import androidx.core.content.ContextCompat;

import com.google.android.material.textview.MaterialTextView;

import de.dlyt.yanndroid.samsung.R;

public class OptionButton extends LinearLayout {

    private Drawable mIcon;
    private String mText;
    private Boolean mSelected;
    private Boolean mCounterEnabled;
    private Integer mCounter;


    private int mOnTextColor;
    private int mOffTextColor;


    private LinearLayout optionbutton;
    private MaterialTextView textView;
    private ImageView imageview;
    private MaterialTextView counter;


    public OptionButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setClickable(true);
        setFocusable(true);

        TypedArray attr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OptionButton, 0, 0);
        mText = attr.getString(R.styleable.OptionButton_text);
        mIcon = attr.getDrawable(R.styleable.OptionButton_icon);
        mSelected = attr.getBoolean(R.styleable.OptionButton_selected, false);
        mCounterEnabled = attr.getBoolean(R.styleable.OptionButton_counterEnabled, false);
        mCounter = attr.getInteger(R.styleable.OptionButton_counter, 0);
        attr.recycle();


        TypedArray switchbar_attr = context.obtainStyledAttributes(attrs, Styleable.styleable.SeslSwitchBar, R.attr.seslSwitchBarStyle, 0);
        mOnTextColor = switchbar_attr.getColor(Styleable.styleable.SeslSwitchBar_seslSwitchBarTextActivatedColor, ContextCompat.getColor(getContext(), R.color.sesl_switchbar_text_color));
        mOffTextColor = switchbar_attr.getColor(Styleable.styleable.SeslSwitchBar_seslSwitchBarTextColor, ContextCompat.getColor(getContext(), R.color.sesl_switchbar_text_color));
        switchbar_attr.recycle();


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.samsung_drawer_optionbutton, this, true);

        optionbutton = findViewById(R.id.optionbutton);
        textView = findViewById(R.id.optionbutton_text);
        imageview = findViewById(R.id.optionbutton_icon);
        counter = findViewById(R.id.optionbutton_counter);

        textView.setText(mText);
        imageview.setImageDrawable(mIcon);
        counter.setVisibility(mCounterEnabled ? VISIBLE : GONE);
        counter.setText(String.valueOf(mCounter));
        setButtonSelected(mSelected);

    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
        textView.setText(mText);
    }

    public void setIcon(Drawable icon) {
        this.mIcon = icon;
        imageview.setImageDrawable(mIcon);
    }

    public void setButtonSelected(Boolean selected) {
        this.mSelected = selected;
        setTextColor(mSelected);
        textView.setTypeface(null, mSelected ? Typeface.BOLD : Typeface.NORMAL);
    }

    public void toggleButtonSelected() {
        setButtonSelected(!mSelected);
    }

    public Boolean isButtonSelcted() {
        return mSelected;
    }

    public void setButtonEnabled(Boolean enabled) {
        setEnabled(enabled);
        optionbutton.setEnabled(enabled);
        imageview.setEnabled(enabled);
        counter.setEnabled(enabled);
        setTextColor(mSelected);
    }

    private void setTextColor(boolean z) {
        float f;
        textView.setTextColor(z ? this.mOnTextColor : this.mOffTextColor);
        if (isEnabled()) {
            f = 1.0f;
        } else if (!SeslMisc.isLightTheme(getContext()) || !z) {
            f = 0.4f;
        } else {
            f = 0.55f;
        }
        textView.setAlpha(f);
    }

    public Integer getCounter() {
        return mCounter;
    }

    public void setCounter(Integer integer) {
        this.mCounter = integer;
        counter.setText(String.valueOf(mCounter));
    }

    public void setCounterEnabled(Boolean enabled) {
        this.mCounterEnabled = enabled;
        counter.setVisibility(mCounterEnabled ? VISIBLE : GONE);
    }

    public void toggleCounterEnabled() {
        setCounterEnabled(!mCounterEnabled);
    }

    public Boolean isCounterEnabled() {
        return mCounterEnabled;
    }

}
