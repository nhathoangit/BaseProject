package com.appscyclone.aclibrary.view.viewholder;
/*
 * Created by HoangDong on 20/09/2017.
 */

import androidx.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appscyclone.aclibrary.utils.ACValidUtils;
import com.appscyclone.aclibrary.view.ACRecyclerView;
import com.appscyclone.aclibrary.view.model.ACBaseTextImageModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

public class ACTextImageViewHolder extends ACRecyclerView.ACBaseViewHolder {
    private TextView[] mTextView;
    private ImageView[] mImageView;
    private int[] mIdTextView, mIdImageView;

    public ACTextImageViewHolder(ViewGroup parent, @LayoutRes int res, int[] idTextView, int[] idImageView) {
        super(parent, res);
        this.mIdTextView = idTextView;
        this.mIdImageView = idImageView;
        mTextView = new TextView[idTextView.length];
        mImageView = new ImageView[idImageView.length];
    }

    @Override
    public void bindData(Object data) {
        if (data instanceof ACBaseTextImageModel) {
            ACBaseTextImageModel model = (ACBaseTextImageModel) data;
            String[] dataText = model.getTextBind();
            for (int i = 0; i < mTextView.length; i++) {
                TextView tv = mTextView[i];
                String str = i < dataText.length ? dataText[i] : "";
                tv.setText(TextUtils.isEmpty(str) ? "" : str);
            }
            String[] dataImage = model.getImageBind();
            for (int i = 0; i < mImageView.length; i++) {
                ImageView img = mImageView[i];
                String url = i < dataImage.length ? dataImage[i] : "";
                img.setImageDrawable(null);
                if (ACValidUtils.isURL(url)) {
                    Glide.with(itemView.getContext())
                            .load(url)
                            .apply(new RequestOptions().centerCrop())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(img);
                }
            }

        }

    }

    @Override
    public void onCreatedView(View view) {
        for (int i = 0; i < mIdTextView.length; i++)
            mTextView[i] = view.findViewById(mIdTextView[i]);
        for (int i = 0; i < mIdImageView.length; i++)
            mImageView[i] = view.findViewById(mIdImageView[i]);
    }
}
