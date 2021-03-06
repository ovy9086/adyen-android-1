/*
 * Copyright (c) 2018 Adyen N.V.
 *
 * This file is open source and available under the MIT license. See the LICENSE file for more info.
 *
 * Created by caiof on 26/11/2018.
 */

package com.adyen.checkout.ui.internal.openinvoice.control;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;

import com.adyen.checkout.core.model.InputDetail;
import com.adyen.checkout.util.internal.SimpleTextWatcher;

public class SimpleEditTextValidator extends ValidationCheckDelegateBase {

    private InputDetail mInputDetail;
    private EditText mEditText;

    public SimpleEditTextValidator(@NonNull InputDetail inputDetail, @NonNull EditText editText) {
        mInputDetail = inputDetail;
        mEditText = editText;

        //check validation when text changes
        editText.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                isValid();
            }
        });
    }

    @NonNull
    @Override
    public ValidationState getValidationState() {
        if (mInputDetail.isOptional()) {
            return ValidationState.VALID;
        }

        if (TextUtils.isEmpty(mEditText.getText())) {
            return ValidationState.FIELD_EMPTY;
        } else {
            return ValidationState.VALID;
        }
    }
}
