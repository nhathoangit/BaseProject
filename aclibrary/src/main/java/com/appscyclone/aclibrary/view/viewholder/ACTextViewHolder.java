package com.appscyclone.aclibrary.view.viewholder;
/*
 * Created by HoangDong on 20/09/2017.
 */

import androidx.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appscyclone.aclibrary.view.ACRecyclerView;
import com.appscyclone.aclibrary.view.model.ACBaseTextModel;

public class ACTextViewHolder extends ACRecyclerView.ACBaseViewHolder {
    private TextView[] mTextView;
    private int[] mIdTextView=new int[]{};
    private int mIdSingleTextView=-1;

    public ACTextViewHolder(ViewGroup parent, @LayoutRes int res, int[] idTextView) {
        super(parent, res);
        this.mIdTextView = idTextView;
        mTextView = new TextView[idTextView.length];
    }
    public ACTextViewHolder(ViewGroup parent, @LayoutRes int res, int idTextView) {
        super(parent, res);
        this.mIdSingleTextView = idTextView;
        mTextView = new TextView[1];
    }

    @Override
    public void bindData(Object data) {
        if (data instanceof ACBaseTextModel) {
            ACBaseTextModel model = (ACBaseTextModel) data;
            String[] dataText = model.getTextBind();
            for (int i = 0; i < mTextView.length; i++) {
                TextView tv = mTextView[i];
                String str = i < dataText.length ? dataText[i] : "";
                tv.setText(TextUtils.isEmpty(str) ? "" : str);
            }
        }else if(data instanceof String && mTextView.length>0)
        {
            if(mTextView[0]==null) {
                Toast.makeText(itemView.getContext(), " Please create id: acTvTitle for TextView", Toast.LENGTH_SHORT).show();
                return;
            }
            mTextView[0].setText((String)data);

        }

    }

    @Override
    public void onCreatedView(View view) {
        for (int i = 0; i < mIdTextView.length; i++)
            mTextView[i] = view.findViewById(mIdTextView[i]);
        if(mIdSingleTextView!=-1)
            mTextView[0]=view.findViewById(mIdSingleTextView);

    }
}
