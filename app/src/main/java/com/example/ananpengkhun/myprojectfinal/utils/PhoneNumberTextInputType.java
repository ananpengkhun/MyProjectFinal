package com.example.ananpengkhun.myprojectfinal.utils;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by Chaiyapong Pipithanachoti on 8/10/16 AD.
 * MFEC.co.ltd
 */
public class PhoneNumberTextInputType extends DVTextInputType {
    public static final String TAG = PhoneNumberTextInputType.class.getSimpleName();
    private static final int MAX_LENGTH = 10;

    private int[] dashPositions = {4, 8};
    private boolean isFormatting = false;
    private onButtonChangeListener callBack;

    public interface onButtonChangeListener {
        void onButtonChange(boolean isEnable);
    }

    public PhoneNumberTextInputType(EditText editText, Context context) {
        super(editText);
        try{
            callBack = (onButtonChangeListener) context;
        }catch (ClassCastException e){
            callBack = null;
        }

    }


    @Override
    public TextWatcher getTextWatcher() {
        return textWatcher;
    }

    @Override
    public InputFilter[] getInputFilters() {
        return inputFilters;
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!isFormatting) {
                Log.v(TAG, "afterTextChanged()....s=" + s);
                isFormatting = true;

                String str = s.toString();
                int length = str.length();

                if (str.endsWith("-")) {
                    s.delete(length - 1, length);
                } else {
                    boolean dash = false;
                    for (int dashPosition : dashPositions) {
                        if (length == dashPosition) {
                            dash = true;
                            break;
                        }
                    }

                    if (dash) {
                        s.insert(length - 1, "-");
                    }
                }

                isFormatting = false;
                int position = s.length(); // end of buffer, for instance
                Selection.setSelection(s, position);
                checkMaxLength(position);
            }
        }
    };

    private void checkMaxLength(int length) {
        if (callBack !=null){
            if (length == 12) {
                callBack.onButtonChange(true);
            } else {
                callBack.onButtonChange(false);
            }
        }

    }

    private InputFilter[] inputFilters = new InputFilter[]{new InputFilter() {


        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                   int dstart, int dend) {

            Log.v(TAG, "filter()....source=" + source + ", start=" + start + ", end=" + end + ", dest=" + dest + ", dstart=" + dstart + ", dend=" + dend);

            if (source.length() > 0) {

                if (".".equals(source)) {
                    return "";
                } else if (dstart == dend) {
                    if (dest.length() == dstart) {
                        String input = dest.toString().replace("-", "");
                        if (input.length() >= MAX_LENGTH) {
                            return "";
                        }
                    } else {
                        // Next version
                    }
                } else {
                    // Next version
                }
            }

            return null;
        }
    }};
}
