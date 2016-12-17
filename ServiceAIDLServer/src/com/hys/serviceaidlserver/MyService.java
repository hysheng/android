package com.hys.serviceaidlserver;

import com.hys.service.DataService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}
	Binder binder=new DataService.Stub() {
		
		@Override
		public double getData(String arg) throws RemoteException {
			// TODO Auto-generated method stub
			if(arg.equals("yoona")){
			return 22;
			}else {
				return 23;
			}
		}
	};
}
