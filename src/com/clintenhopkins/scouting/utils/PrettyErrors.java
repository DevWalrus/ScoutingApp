package com.clintenhopkins.scouting.utils;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;

public class PrettyErrors {

    private static SpanLabel errorLabel;

    public static void throwError(String message, Image icon) {
        errorLabel = new SpanLabel(message);
        errorLabel.setIcon(icon);
    }

    public static void showError() {
        if (errorLabel != null) {
            Dialog dlg = new Dialog("Warning");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, errorLabel);
            dlg.setDisposeWhenPointerOutOfBounds(true);
            dlg.show();
        }
        errorLabel = null;
    }
}
