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
import android.app.NotificationManager.Policy;
import android.content.Context;
import android.support.v7.preference.Preference;

import com.android.settings.SettingsRobolectricTestRunner;
import com.android.settings.TestConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;
import org.robolectric.util.ReflectionHelpers;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SettingsRobolectricTestRunner.class)
@Config(manifest = TestConfig.MANIFEST_PATH, sdk = TestConfig.SDK_VERSION)
public class ZenModePreferenceControllerTest {

    @Mock
    private Context mContext;
    @Mock
    private Preference mPreference;
    @Mock
    private NotificationManager mNotificationManager;
    @Mock
    private Policy mPolicy;

    private ZenModePreferenceController mController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mController = new ZenModePreferenceController(mContext);
        when(mContext.getSystemService(Context.NOTIFICATION_SERVICE))
            .thenReturn(mNotificationManager);
        when(mNotificationManager.getNotificationPolicy()).thenReturn(mPolicy);
    }

    @Test
    public void isAlwaysAvailable() {
        assertThat(mController.isAvailable()).isTrue();
    }

    @Test
    public void updateState_preferenceEnabled_shouldSetSummary() {
        when(mPreference.isEnabled()).thenReturn(true);

        mController.updateState(mPreference);

        verify(mPreference).setSummary(anyString());
    }

    @Test
    public void updateState_preferenceDisabled_shouldNotSetSummary() {
        when(mPreference.isEnabled()).thenReturn(false);

        mController.updateState(mPreference);

        verify(mPreference, never()).setSummary(anyString());
    }

}
