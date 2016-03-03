package com.example.fangyi.preferenceactivity;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by FANGYI on 2016/3/2.
 */
public class MyPreferenceActivity extends PreferenceActivity {

    //checkboxPreference
    //listPreference
    //edittextPreference

    PreferenceManager manager;
    CheckBoxPreference checkBoxPreference;
    ListPreference listPreference;
    EditTextPreference editTextPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mypreference);

        manager = getPreferenceManager();

        checkBoxPreference = (CheckBoxPreference) manager.findPreference("checkbox");
        Toast.makeText(getApplicationContext(), "当前的状态为：" + checkBoxPreference.isChecked(), Toast.LENGTH_SHORT).show();

        listPreference = (ListPreference) manager.findPreference("list");
        Toast.makeText(getApplicationContext(), listPreference.getEntry() + "的开发环境为：" + listPreference.getValue(), Toast.LENGTH_SHORT).show();

        editTextPreference = (EditTextPreference) manager.findPreference("text");
        Toast.makeText(getApplicationContext(), "你输入的内容为：" + editTextPreference.getText(), Toast.LENGTH_SHORT).show();

    }
}
