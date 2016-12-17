package com.hys.dialogtoast;






import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomView {
	private Context context;
	private Builder builder;
	public CustomView(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	public void createDialog(String title,String message,String btmessage,final CallBack callBack){
		builder=new Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(btmessage, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				callBack.isConfirm(true);
			}
		});
		builder.create().show();
	}
	public void createToast(String message,LayoutInflater inflater){
		View view=inflater.inflate(R.layout.toast, null);
		TextView textView=(TextView)view.findViewById(R.id.text);
		textView.setText(message);
		Toast toast=new Toast(context);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
	}
	public interface CallBack{
		public void isConfirm(boolean flag);
	}
}
