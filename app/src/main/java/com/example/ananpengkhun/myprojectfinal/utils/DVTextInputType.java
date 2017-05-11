package com.example.ananpengkhun.myprojectfinal.utils;

import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Chaiyapong Pipithanachoti on 8/10/16 AD.
 * MFEC.co.ltd
 */
public abstract class DVTextInputType {
    protected EditText editText;

    protected DVTextInputType(EditText editText) {
        this.editText = editText;
    }

    public EditText getEditText() {
        return editText;
    }

    public abstract TextWatcher getTextWatcher();

    public abstract InputFilter[] getInputFilters();
}
