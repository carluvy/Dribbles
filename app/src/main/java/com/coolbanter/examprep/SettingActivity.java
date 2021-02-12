package com.coolbanter.examprep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import com.coolbanter.examprep.notification.NotificationWorker;

import java.util.concurrent.TimeUnit;

public class SettingActivity extends AppCompatActivity {
    public static final String
            KEY_PREF_EXAMPLE_SWITCH = "example_switch";
//    private SharedPreferences mPreferences;
//    private String sharedPrefFile =
//            "com.coolbanter.dribble";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();


    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

        public static final String WORKER_TAG = "notification_worker";
        private SharedPreferences mPreferences;
        private String sharedPrefFile =
                "com.coolbanter.dribble";
        ListPreference mListPreference;


        @Override
        public void onCreatePreferences(Bundle bundle, String string) {
            addPreferencesFromResource(R.xml.preferences);


        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            mPreferences = this.getActivity()
                    .getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
            Preference preference =
                    this.findPreference(getString(R.string.pref_key_notification));
            preference.setSummary(mPreferences.getString("summary",
                    getString(R.string.option_on)));

            preference.setOnPreferenceChangeListener((preference1, newValue) -> {
                if ((Boolean) newValue) {
                    preference1.setSummary(R.string.option_on);
                    SharedPreferences.Editor preferencesEditor =
                            mPreferences.edit();
                    preferencesEditor.putString("summary",
                            getString(R.string.option_on)).apply();
                } else {
                    preference1.setSummary(R.string.option_off);
                    SharedPreferences.Editor preferencesEditor =
                            mPreferences.edit();
                    preferencesEditor.putString("summary",
                            getString(R.string.option_off)).apply();
                }


                return true;


            });

//            bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_key_answers)));

//
//            bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_key_notification)));

//            pref();

        }



        private void bindPreferenceSummaryToValue(Preference preference) {
            // Set the listener to watch for value changes.

            preference.setOnPreferenceChangeListener((preference1, newValue) -> {
                String stringValue = newValue.toString();

                if (preference1 instanceof ListPreference) {
                    ListPreference listPreference = (ListPreference) preference1;
                    int prefIndex = listPreference.findIndexOfValue(stringValue);
                    if (prefIndex >= 0) {

                        preference1.setSummary(listPreference.getEntries()[prefIndex]);



//                            intent.putExtra(((ListPreference) preference).getValue(), ((ListPreference) preference).getEntries());
//                            sendBroadcast(intent, Manifest.permission.SEND_RESPOND_VIA_MESSAGE);
                    } else {


                        // For other preferences, set the summary to the value's simple string representation.
                        preference1.setSummary(stringValue);
                    }
                }


                return true;
            });
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        }

//        @Override
//        public void onPause() {
//            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
//            super.onPause();
//        }

        @Override
        public boolean onPreferenceTreeClick(Preference preference) {
//            mPreferences = this.getActivity()
//                    .getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
            mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            final String notifyKey = getString(R.string.pref_key_notification);
            final String answerKey = getString(R.string.pref_key_answers);
                if (preference.getKey().equals(notifyKey)) {
                    boolean on = ((SwitchPreferenceCompat) preference).isChecked();
                    if (on) {

                        Constraints.Builder constraintsBuilder = new Constraints.Builder();
                        constraintsBuilder.setRequiresBatteryNotLow(false);
                        constraintsBuilder.setRequiredNetworkType(NetworkType.NOT_REQUIRED);
                        constraintsBuilder.setRequiresCharging(false);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            constraintsBuilder.setRequiresDeviceIdle(false);
                        }
//
                        Constraints constraints = constraintsBuilder.build();

                        PeriodicWorkRequest periodicWorkRequest =
                                new PeriodicWorkRequest.Builder(NotificationWorker.class, 1,
                                        TimeUnit.MINUTES).addTag(WORKER_TAG)
                                        .setConstraints(constraints)
                                        .build();


//                       preference.callChangeListener(on);
//                    periodicWorkRequest.setConstraints(constraints);

//                    WorkRequest request = periodicWorkRequest.build();

//                    WorkManager.getInstance(getContext())
//                            .enqueue(periodicWorkRequest);
                        WorkManager.getInstance(getContext())
                                .enqueueUniquePeriodicWork(NotificationWorker.WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);


                        Toast.makeText(getContext(), "You will receive an emoji", Toast.LENGTH_SHORT).show();
                    }

//                if (preference.getKey().equals(answerKey)) {
//
//                    final
//
//                    private void bindPreferenceSummaryToValue (Preference preference){
////            // Set the listener to watch for value changes.
//
//                        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//                            @Override
//                            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                                String stringValue = newValue.toString();
//
//                                ListPreference listPreference = (ListPreference) preference;
//                                int prefIndex = listPreference.findIndexOfValue(stringValue);
//                                if (prefIndex >= 0) {
//                                    preference.setSummary(listPreference.getEntries()[prefIndex]);
//                                } else {
//                                    // For other preferences, set the summary to the value's simple string representation.
//                                    preference.setSummary(stringValue);
//                                }
//
//                                return true;
//
//
//                            }
//                        });


                }
//                    SharedPreferences.OnSharedPreferenceChangeListener listener;
//
//                    listener = (sharedPreferences, key) -> {

//                    if (preference.getKey().equals(answerKey)) {
////                    Preference preference1 = this.findPreference(answerKey);
//                        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_key_answers)));
////                        mPreferences.registerOnSharedPreferenceChangeListener(listener);
//
//                    }


            return super.onPreferenceTreeClick(preference);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Preference preference = findPreference(key);
            if(key.equals(getString(R.string.pref_key_answers))){
                ListPreference listPreference = (ListPreference) preference;
                String val = listPreference.getValue();
                int prefIndex = listPreference.findIndexOfValue(val);
                if (prefIndex >= 0) {
                    preference.setSummary(((ListPreference) preference).getEntry());

                    Intent intent = new Intent(getContext(), MainActivity.class);
//                PendingIntent pendingIntent =
//                        PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    intent.putExtra("pref_key_answers", val);
                    startActivity(intent);
            }
//                int prefIndex = listPreference.findIndexOfValue(stringValue);
//                if (prefIndex >= 0) {
//                preference.setSummary(((ListPreference) preference).getEntry());

//                Intent intent = new Intent(getContext(), MainActivity.class);
////                PendingIntent pendingIntent =
////                        PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                intent.putExtra("pref_key_answers", ((ListPreference) preference).getEntry());
//                startActivity(intent);
            }
        }

//        private void pref() {
//            mPreferences = this.getActivity()
//                    .getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
//
//
//            ListPreference list = (ListPreference) getPreferenceManager().findPreference("pref_key_answers");
//            list.setValue(mPreferences.getString(String.valueOf(R.string.pref_key_answers), "default"));
//            list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//
//                public boolean onPreferenceChange(Preference preference, Object newValue) {
//                    mPreferences.getString(String.valueOf(R.string.pref_key_answers), newValue.toString());
//                    return true;
//
//
//
//                }
//
//            });
//
//        }
        }
    }

