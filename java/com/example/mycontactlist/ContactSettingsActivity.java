package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ContactSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);
        initListButton();
        initMapButton();
        initSettingsButton();
        initSettings();
        initSortByClick();
        initSortOrderClick();
    }

    private void initListButton() {
        ImageButton contactList = findViewById(R.id.imageButtonList);
        contactList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initMapButton() {
        ImageButton contactList = findViewById(R.id.imageButtonMap); // Same as above
        contactList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // And listen
                //setForEditing(false);
            }
        });
    }

    private void initSettingsButton() {
        ImageButton ibSetting = findViewById(R.id.imageButtonSettings);
        ibSetting.setEnabled(false);
    }

    private void initSettings() {
        String sortBy = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortfield", "contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortorder", "ASC");

        RadioButton rbName = findViewById(R.id.radioName);
        RadioButton rbCity = findViewById(R.id.radioCity);
        RadioButton rbBirthday = findViewById(R.id.radioBirthday);

        if (sortBy.equalsIgnoreCase("contactname")) {
            rbName.setChecked(true);
        }
        else if (sortBy.equalsIgnoreCase("city")) {
            rbCity.setChecked(true);
        }
        else {
            rbBirthday.setChecked(true);
        }
        RadioButton rbAscending = findViewById(R.id.radioAscending);
        RadioButton rbDescending = findViewById(R.id.radioDescending);

        if (sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        }
        else {
            rbDescending.setChecked(true);
        }
    }

    private void initSortByClick() {
        RadioGroup rgSortBy = findViewById(R.id.radioGroupSortBy);
        rgSortBy.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rbName = findViewById(R.id.radioName);
            RadioButton rbCity = findViewById(R.id.radioCity);
            if (rbName.isChecked()) {
                getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
                        .putString("sortfield", "contactname").apply();
            }
            else if (rbCity.isChecked()) {
                getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
                        .putString("sortfield", "city").apply();
            }
            else {
                getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
                        .putString("sortfield", "birthday").apply();
            }
        });
    }

    private void initSortOrderClick() {
        RadioGroup rgSortOrder = findViewById(R.id.radioGroupSortOrder);
        rgSortOrder.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rbAscending = findViewById(R.id.radioAscending);

            if (rbAscending.isChecked()) {
                getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
                        .putString("sortorder", "ASC").apply();
            }
            else {
                getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
                        .putString("sortorder", "DESC").apply();
            }
        });
    }
}