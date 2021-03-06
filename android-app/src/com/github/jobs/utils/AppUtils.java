/*
 * Copyright 2012 CodeSlap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jobs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.github.jobs.R;
import com.github.jobs.bean.Template;
import com.github.jobs.ui.activity.HomeActivity;
import com.github.jobs.ui.activity.TemplatesActivity;

import static com.github.jobs.utils.AnalyticsHelper.*;

/**
 * @author cristian
 * @version 1.0
 */
public class AppUtils {

  public static final boolean IN_DEVELOPMENT = true;

  public static void goHome(Activity activity) {
    Intent intent = new Intent(activity, HomeActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    activity.startActivity(intent);
    activity.overridePendingTransition(R.anim.home_enter, R.anim.home_exit);
    getTracker(activity).trackEvent(CATEGORY_JOBS, ACTION_BACK, LABEL_FROM_DETAILS);
  }

  public static void goTemplatesList(Activity activity) {
    Intent intent = new Intent(activity, TemplatesActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    activity.startActivity(intent);
    activity.overridePendingTransition(R.anim.home_enter, R.anim.home_exit);
    getTracker(activity).trackEvent(CATEGORY_JOBS, ACTION_BACK, LABEL_FROM_DETAILS);
  }

  public static Template getDefaultTemplate(Context context) {
    Template defaultTemplate = new Template();
    defaultTemplate.setName(context.getString(R.string.default_cover_letter_name));
    defaultTemplate.setContent(context.getString(R.string.default_cover_letter_content));
    defaultTemplate.setLastUpdate(System.currentTimeMillis());
    return defaultTemplate;
  }

  public static void setupWebView(WebView webView) {
    if (webView == null) {
      return;
    }
    webView.setHorizontalScrollBarEnabled(false);
    webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

    WebSettings settings = webView.getSettings();
    settings.setPluginsEnabled(false);
    settings.setJavaScriptEnabled(true);
    settings.setBuiltInZoomControls(false);
    settings.setJavaScriptCanOpenWindowsAutomatically(false);
    settings.setSupportMultipleWindows(false);
    settings.setAppCacheEnabled(true);
    settings.setCacheMode(WebSettings.LOAD_NORMAL);
    if (AppUtils.isHoneycombPlus()) {
      settings.enableSmoothTransition();
    }
    settings.setLoadsImagesAutomatically(true);
    settings.setGeolocationEnabled(true);
    settings.setDomStorageEnabled(true);
    settings.setSupportZoom(false);
    settings.setSaveFormData(false);
    settings.setSavePassword(false);
    settings.setLightTouchEnabled(false);
    settings.setDatabaseEnabled(true);
    settings.setAllowFileAccess(true);
  }

  public static boolean isHoneycombPlus() {
    return Build.VERSION.SDK_INT >= 11;
  }

  public static void hideKeyboard(Context context, IBinder token) {
    InputMethodManager imm =
        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(token, 0);
  }

  public static boolean isOnline(Context context) {
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (cm == null) {
      return false;
    }
    NetworkInfo info = cm.getActiveNetworkInfo();
    return info != null && info.isConnectedOrConnecting();
  }
}
