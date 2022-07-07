package sg.edu.rp.c346.id20006092.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvGPA;
    EditText etName, etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.textViewName);
        tvGPA = findViewById(R.id.textViewGPA);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);

        rgGender = findViewById(R.id.RadioGroupGender);


    }

    @Override
    protected void onStop() {
        super.onStop();

        //what goes in here? grab input & store into SharedPreferences
        String strName = etName.getText().toString();
        float gpa= Float.parseFloat(etGPA.getText().toString());
        String gender;
        if (rgGender.getCheckedRadioButtonId() == R.id.radioButtonMale){
            gender = "Male";
        } else {
            gender = "Female";
        }

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();
        //what about code in between the lines here?
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.putString("gender", gender);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String strName = prefs.getString("name", "JohnDoe");
        float gpa = prefs.getFloat("gpa", 0);
        String gender = prefs.getString("gender", "Male");

        etName.setText(strName);
        etGPA.setText(gpa + "");

        if (gender.equals("Male") ){
            rgGender.check(R.id.radioButtonMale);
        } else if ( gender.equals("Female") ){
            rgGender.check(R.id.radioButtonFemale);
        }
    }


}