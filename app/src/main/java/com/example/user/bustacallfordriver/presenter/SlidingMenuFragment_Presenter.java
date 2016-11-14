package com.example.user.bustacallfordriver.presenter;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.dialog.Dialog_Progress;
import com.example.user.bustacallfordriver.view.SlidingMenuFragment;

/**
 * Created by user on 2016-11-13.
 */

public class SlidingMenuFragment_Presenter {

    SlidingMenuFragment view;
    AppController app;
    Dialog_Progress dialog_progress;

    public SlidingMenuFragment_Presenter(SlidingMenuFragment view) {
        this.view = view;
        app = (AppController) view.getActivity().getApplicationContext();
        dialog_progress = new Dialog_Progress(view.getContext());
    }
}
