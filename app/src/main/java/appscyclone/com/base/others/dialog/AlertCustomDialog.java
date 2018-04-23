package appscyclone.com.base.others.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import appscyclone.com.base.R;
import appscyclone.com.base.interfaces.DialogListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * Created by NhatHoang on 23/04/2018.
 */
public class AlertCustomDialog extends Dialog {

    @BindView(R.id.dialogAlert_tvContent)
    TextView tvContent;
    @BindView(R.id.dialogAlert_tvTitle)
    TextView tvTitle;
    @BindView(R.id.dialogAlert_frmLine)
    FrameLayout frmLine;
    @BindView(R.id.dialogAlert_tvNo)
    TextView tvNo;
    @BindView(R.id.dialogAlert_tvOK)
    TextView tvOK;

    private DialogListener clickListener;
    private String title = "", content = "";

    public AlertCustomDialog(Context context, String title, String content, DialogListener listener) {
        super(context, android.R.style.Theme_Holo_Dialog);
        this.title = title;
        this.content = content;
        clickListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getWindow() != null) {
            getWindow().setDimAmount(0.4f);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        setContentView(R.layout.dialog_alert);
        ButterKnife.bind(this);
        if (!content.isEmpty()) {
            tvContent.setText(content);
        }
    }

    @OnClick({R.id.dialogAlert_tvOK, R.id.dialogAlert_tvNo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialogAlert_tvOK:
                if (clickListener != null)
                    clickListener.onConfirmClicked();
                break;
            case R.id.dialogAlert_tvNo:
                break;
        }
        dismiss();
    }
}
