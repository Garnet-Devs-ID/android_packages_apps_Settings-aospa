/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.preference.Preference;
import com.android.settings.R;

public class ZenModePreferenceController extends AdjustVolumeRestrictedPreferenceController {

    private static final String KEY_ZEN_MODE = "zen_mode";
    private String mSummaryPrefix;

    private ZenModeSettings.SummaryBuilder mSummaryBuilder;

    public ZenModePreferenceController(Context context) {
        super(context);
        mSummaryBuilder = new ZenModeSettings.SummaryBuilder(context);
        mSummaryPrefix = context.getString(R.string.zen_mode_priority_settings_title) + " ";
    }

    @Override
    public String getPreferenceKey() {
        return KEY_ZEN_MODE;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (preference.isEnabled()) {
            preference.setSummary(mSummaryPrefix + mSummaryBuilder.getPrioritySettingSummary(
                NotificationManager.from(mContext).getNotificationPolicy()));
        }
    }
}
