package com.hys.listview;

import com.hys.dao.StudentDAO;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PersonActivity extends Activity {
	private ListView persons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person);
		persons=(ListView) findViewById(R.id.persons);
		StudentDAO studentDAO=new StudentDAO(this, "listview.db", null, 1);
		Cursor cursor=studentDAO.getPersons(1, 6);
		SimpleCursorAdapter adapter=new PersonAdapter(this, R.layout.person_item,
				cursor,new String[]{"name","number","sex"}, new int[]{R.id.name,R.id.number,R.id.sex});
		persons.setAdapter(adapter);
		persons.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Cursor c=(Cursor) parent.getItemAtPosition(position);
				Toast.makeText(PersonActivity.this, c.getString(1), Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
	}

}
	class PersonAdapter extends SimpleCursorAdapter{
	
		private Cursor mcursor;
		private int[] mFrom;
		private int[] mTo;
		
		private ViewBinder mViewBinder;
		
		public PersonAdapter(Context context, int layout, Cursor c,
				String[] from, int[] to) {
			super(context, layout, c, from, to);
			mcursor=c;
			mTo=to;
			findColumns(from);
		}
		
		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			final ViewBinder viewBinder=mViewBinder;
			final int count=mFrom.length;
			final int[] from=mFrom;
			final int[] to=mTo;
			
			for(int i=0;i<count;i++){
				final View v=view.findViewById(to[i]);
				if(v!=null){
					boolean bound=false;
					if(viewBinder!=null){
						bound=viewBinder.setViewValue(v, cursor, from[i]);
					}
					if(!bound){
						String text=cursor.getString(from[i]);
						if(text==null){
							text="";
						}
						if(v instanceof TextView){
							setViewText((TextView)v, text);
						}else if(v instanceof ImageView){
							if(text.equals("f")){
								setViewImage((ImageView)v, String.valueOf(R.drawable.boy));
							}else{
								setViewImage((ImageView)v, String.valueOf(R.drawable.girl));
							}
						}else {
							throw new IllegalStateException(v.getClass()+"is not a view that can be bounds by this SimpleCursorAdapter");
						}
						
					}
				}
			}
			
			
		}
		
		private void findColumns(String[] from){
			if(mcursor!=null){
				int count=from.length;
				if(mFrom==null||mFrom.length!=count){
					mFrom=new int[count];
				}
				for(int i=0;i<count;i++){
					mFrom[i]=mcursor.getColumnIndexOrThrow(from[i]);
				}
				
			}else {
				mFrom=null;
			}
		}
	}