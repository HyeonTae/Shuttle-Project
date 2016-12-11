package smu.custom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.widget.Toast;

public class TabListener<T extends Fragment> implements android.app.ActionBar.TabListener {
	
	private final Activity mActivity;
	private final String mtag;
	private final Class<T> mclass;
	private Fragment mfragment;
	public TabListener(Activity activity, String tag, Class<T> clz){
		mActivity = activity;
		mtag = tag;
		mclass = clz;
		
		mfragment = mActivity.getFragmentManager().findFragmentByTag(mtag);
		if(mfragment !=null && !mfragment.isDetached()){
			FragmentTransaction fragmentTransaction = mActivity.getFragmentManager().beginTransaction();
			fragmentTransaction.detach(mfragment);
			fragmentTransaction.commit();
		}
	}
	
	
	public void onTabSelected (Tab tab, FragmentTransaction fragmentTransaction){
		if(mfragment==null){
			mfragment= mfragment.instantiate(mActivity, mclass.getName(),null);
			fragmentTransaction.add(android.R.id.content, mfragment,mtag);
			
		}else {
			fragmentTransaction.attach(mfragment);
		}
	}




	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(mfragment!=null){
			ft.detach(mfragment);
		}
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Toast.makeText(mActivity, "onReSelected", Toast.LENGTH_SHORT).show();
	}
}
