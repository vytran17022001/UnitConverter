package com.example.unitconverter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber;
    TextView valueOfConverter;
    Button button;
    Spinner fromUnitSpinner, toUnitSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các widget
        editTextNumber = findViewById(R.id.editTextNumber);
        valueOfConverter = findViewById(R.id.valueOfConverter);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        button = findViewById(R.id.button);

        // Set up ArrayAdapter cho Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        // Xử lý sự kiện nhấn nút
        button.setOnClickListener(view -> unitConverter());
    }

    private void unitConverter() {

        if (editTextNumber.getText().toString().isEmpty()) {
            editTextNumber.setError("Vui lòng nhập giá trị");
            return;
        }
        // Chuyển đổi giá trị nhập
        double input = Double.parseDouble(editTextNumber.getText().toString());
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double result = unitConverterLength(input, fromUnit, toUnit);
        valueOfConverter.setText(String.format("%.2f", result));
    }

    // Phương thức chuyển đổi đơn vị
    private double unitConverterLength(double input, String fromUnit, String toUnit) {
        double meters = 0;
        switch (fromUnit) {
            case "Meters":
                meters = input;
                break;
            case "Millimeters":
                meters = input / 1000;
                break;
            case "Miles":
                meters = input * 1609.34;
                break;
            case "Feet":
                meters = input * 0.3048;
                break;
        };

        switch (toUnit) {
            case "Meters":
                return meters;
            case "Millimeters":
                return meters * 1000;
            case "Miles":
                return meters / 1609.34;
            case "Feet":
                return meters / 0.3048;
        };
        return 0;
    }
}
