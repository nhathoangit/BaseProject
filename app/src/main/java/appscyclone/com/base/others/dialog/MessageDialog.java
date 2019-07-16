package appscyclone.com.base.others.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import appscyclone.com.base.R;
import appscyclone.com.base.others.interfaces.ConfirmListener;
import appscyclone.com.base.others.interfaces.YesNoListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * Created by NhatHoang on 25/10/2018.
 */
public class MessageDialog extends Dialog {
    @BindView(R.id.dialogConfirm_tvOK)
    TextView tvOK;
    @BindView(R.id.dialogConfirm_tvTitle)
    TextView tvTitle;
    @BindView(R.id.dialogConfirm_tvCancel)
    TextView tvCancel;
    @BindView(R.id.dialogConfirm_flCancel)
    FrameLayout flCancel;
    @BindView(R.id.dialogConfirm_tvContent)
    TextView tvContent;
    private boolean isVisibilityVisit;
    private String title = "", content = "";

    private ConfirmListener confirmListener;

    private YesNoListener yesNoListener;

    public MessageDialog(Context context) {
        super(context, android.R.style.Theme_Holo_Dialog);
    }

    public MessageDialog(Context context, String title, String content, @Nullable ConfirmListener confirmListener) {
        super(context, android.R.style.Theme_Holo_Dialog);
        this.title = title;
        this.content = content;
        this.confirmListener = confirmListener;
    }

    public MessageDialog(Context context, String title, String content, boolean isVisibilityVisit, @Nullable YesNoListener yesNoListener) {
        super(context, android.R.style.Theme_Holo_Dialog);
        this.isVisibilityVisit = isVisibilityVisit;
        this.title = title;
        this.content = content;
        this.yesNoListener = yesNoListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getWindow() != null) {
            getWindow().setDimAmount(0.3f);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        setContentView(R.layout.dialog_confirm);
        ButterKnife.bind(this);
        flCancel.setVisibility(isVisibilityVisit ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(content)) {
            tvContent.setText(content);
        }
    }

    @OnClick({R.id.dialogConfirm_tvOK, R.id.dialogConfirm_flCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialogConfirm_tvOK:
                if (yesNoListener != null)
                    yesNoListener.onYesListener();
                break;
            case R.id.dialogConfirm_flCancel:
                if (confirmListener != null) {
                    confirmListener.onConfirmClicked();
                } else if (yesNoListener != null) {
                    yesNoListener.onNoListener();
                }
                break;
        }
        dismiss();
    }
}
