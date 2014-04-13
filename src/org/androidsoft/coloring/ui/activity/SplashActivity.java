/* Copyright (c) 2010-2011 Pierre LEVY androidsoft.org
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.androidsoft.coloring.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.fairket.sdk.android.FairketApiClient;
import com.fairket.sdk.android.FairketHelperForGingerbread;
import com.silo.games.coloring.kids.R;

import org.androidsoft.utils.ui.WhatsNewActivity;

/**
 * Splash activity
 * 
 * @author Pierre Levy
 */
public class SplashActivity extends WhatsNewActivity implements OnClickListener {

	public static final String FAIRKET_LOG_TAG = "FairketKidsMemory";
	public static final String FAIRKET_APP_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAySve+POu83Evq5DLonjfbExt9DJoDkfazLR3zeoD6xsNqdpGYOdMS0Q2u3WHpPcKUKnciPAZJd8nKvJhhMQwWoutOd0iI8VIdN7glJmni2SnF6CjCPcz5TEEd3D7MBiW68VM77QFhK+qNTKAVyRhRM5X517rbWpVNlvDB43CCJVu2nNyDFVUYv4qTcA3DPNVUnkS3ChoShuVbQyuRoc3msWzUsZDqcAl92WrqNSkvcDwtObOvpQJut5JfHeI6UkGFQzPXONvsrviwL0Vka8iKxnV+l9PsDMjb4SrGandMNQQIk/Kfy86N4LQnnx9n0HsNFwvfqAKwjwonaDruVLCQwIDAQAB";

	private Button mButtonPlay;
	private FairketApiClient mFaiirketApiClient;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.splash);

		mButtonPlay = (Button) findViewById(R.id.button_go);
		mButtonPlay.setOnClickListener(this);

		ImageView image = (ImageView) findViewById(R.id.image_splash);
		image.setImageResource(R.drawable.splash);
		
		mFaiirketApiClient = FairketHelperForGingerbread.onCreate(this,
				SplashActivity.FAIRKET_APP_PUBLIC_KEY,
				SplashActivity.FAIRKET_LOG_TAG);


	}

	/**
	 * {@inheritDoc }
	 */
	public void onClick(View v) {
		if (v == mButtonPlay) {
			Intent intent = new Intent(this, PaintActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public int getFirstRunDialogTitleRes() {
		return R.string.first_run_dialog_title;
	}

	@Override
	public int getFirstRunDialogMsgRes() {
		return R.string.first_run_dialog_message;
	}

	@Override
	public int getWhatsNewDialogTitleRes() {
		return R.string.whats_new_dialog_title;
	}

	@Override
	public int getWhatsNewDialogMsgRes() {
		return R.string.whats_new_dialog_message;
	}
	
	@Override
	protected void onPause() {
		super.onPause();

		FairketHelperForGingerbread.onPause(mFaiirketApiClient);
	}

	@Override
	protected void onResume() {
		super.onResume();

		FairketHelperForGingerbread.onResume(mFaiirketApiClient);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		FairketHelperForGingerbread.onDestroy(mFaiirketApiClient);
	}
}
