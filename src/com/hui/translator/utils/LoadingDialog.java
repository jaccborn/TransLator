package com.hui.translator.utils;

import com.hui.translator.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/** 
 * º”‘ÿ∂‘ª∞øÚ 
 **/
public class LoadingDialog {

	
	private Context mContext;
	private View mDialogView;
	private Dialog dialog;
	private TextView mTextView;
	public LoadingDialog(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		mDialogView = LayoutInflater.from(context).inflate(R.layout.loading_dialog_layout,null);
		mTextView = (TextView) mDialogView.findViewById(R.id.loding_dialog_msg_tv);
		initDialog();
	}
	
	private void initDialog() {
		if(dialog != null) {
			dialog.cancel();
		}
		dialog = new Dialog(mContext,R.style.loddingDilog);
		dialog.setContentView(mDialogView);
		dialog.setCanceledOnTouchOutside(true);
	}
	
	public void dialogShow() {
		if(dialog != null) {
			dialog.show();
		}
		
	}
	
	public void setMessage(CharSequence msg) {
		mTextView.setText(msg);
	}
	
	public void setMessage(int msg) {
		mTextView.setText(msg);
	}
	
	public void dismiss() {
		if(dialog != null) {
			dialog.dismiss();
		}
	}
}
