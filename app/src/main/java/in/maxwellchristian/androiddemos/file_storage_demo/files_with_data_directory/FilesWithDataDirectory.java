package in.maxwellchristian.androiddemos.file_storage_demo.files_with_data_directory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import in.maxwellchristian.androiddemos.R;

public class FilesWithDataDirectory extends AppCompatActivity {

    private static final String FILE_NAME = "DemoFile.txt";
    EditText etContentForFileDataDir;

    Button btnWriteToFile;
    Button btnReadFromFile;

    TextView tvContentFromFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_with_data_directory);

        etContentForFileDataDir = findViewById(R.id.etContentForFileDataDir);
        tvContentFromFile = findViewById(R.id.tvContentsOfFileDataDir);

        btnWriteToFile = findViewById(R.id.btnWriteToFileDataDir);
        btnReadFromFile = findViewById(R.id.btnReadFromFileDataDir);

        btnWriteToFile.setOnClickListener(view -> writeToFileInDataDir());
        btnReadFromFile.setOnClickListener(view -> readFromFileInDataDir());

    }

    private void readFromFileInDataDir() {

        try (FileInputStream fileInputStream = openFileInput(FILE_NAME)) {

            InputStreamReader inputStreamReader =
                    new InputStreamReader(fileInputStream);

            StringBuilder dataFromFile = new StringBuilder();
            int charRead;
            while ((charRead = inputStreamReader.read()) != -1) {
                dataFromFile.append((char)charRead);
            }
            inputStreamReader.close();

            tvContentFromFile.setText(dataFromFile.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void writeToFileInDataDir() {


        // create an instance to write to the desired file in data directory
        try (FileOutputStream fileOutputStream =
                     openFileOutput(FilesWithDataDirectory.FILE_NAME,
                             MODE_PRIVATE | MODE_APPEND)) {

            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(fileOutputStream);

            outputStreamWriter.write(
                    etContentForFileDataDir.getText().toString());
            outputStreamWriter.close();

            Toast.makeText(FilesWithDataDirectory.this, "File Saved",
                    Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}