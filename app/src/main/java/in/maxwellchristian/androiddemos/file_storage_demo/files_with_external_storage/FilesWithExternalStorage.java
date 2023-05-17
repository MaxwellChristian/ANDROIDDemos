package in.maxwellchristian.androiddemos.file_storage_demo.files_with_external_storage;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.documentfile.provider.DocumentFile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import in.maxwellchristian.androiddemos.R;

public class FilesWithExternalStorage extends AppCompatActivity implements
        ActivityResultCallback<ActivityResult> {

    private static final String FILE_NAME = "FileFromMyApp.txt";
    EditText etContentForFileOfExternalStorage;
    TextView tvContentOfFileFromExternalStorage;

    Button btnWriteToFile;
    Button btnReadFromFile;
    private ActivityResultLauncher<Intent> launcher;
    private Uri baseDocumentTreeUri;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_with_external_storage);

        launcher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        FilesWithExternalStorage.this);

        etContentForFileOfExternalStorage =
                findViewById(R.id.etContentForFileInExternalStorage);
        tvContentOfFileFromExternalStorage =
                findViewById(R.id.tvContentForFileInExternalStorage);

        btnWriteToFile = findViewById(R.id.btnWriteToFileInExternalStorage);
        btnReadFromFile = findViewById(R.id.btnReadFromFileOfExternalStorage);

        btnWriteToFile.setOnClickListener(v -> writeToExternalStorage());
        btnReadFromFile.setOnClickListener(v -> readFromExternalStorage());
    }

    private void readFromExternalStorage() {

        // get the file to read from the external storage

        try {
            SharedPreferences preferences =
                    FilesWithExternalStorage.this.getSharedPreferences(
                            getPackageName(),
                            Context.MODE_PRIVATE);
            String uriToUse = preferences.getString("filestorageuri", "");

            DocumentFile directory =
                    DocumentFile.fromTreeUri(FilesWithExternalStorage.this,
                            Uri.parse(uriToUse));

            DocumentFile file = directory.findFile(FILE_NAME);

            ParcelFileDescriptor pfd = FilesWithExternalStorage.this
                    .getContentResolver()
                    .openFileDescriptor(file.getUri(), "r");

            FileInputStream fis =
                    new FileInputStream(pfd.getFileDescriptor());

            StringBuilder dataFromFile = new StringBuilder();
            int ch;
            while ((ch =fis.read()) != -1) {
                dataFromFile.append((char)ch);
            }
            fis.close();

            tvContentOfFileFromExternalStorage.setText(dataFromFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void writeToExternalStorage() {

        // 1. launch the picker UI
        launchPickerUI();

        // 2. get permissions and persist into shared preferences
        // performed using activity result

        // 3. write to the file
        // performed in activity result
    }

    private void writeFile(String fileName, String fileContent) {
        try {

            DocumentFile directory =
                    DocumentFile.fromTreeUri(FilesWithExternalStorage.this,
                            baseDocumentTreeUri);

            assert directory != null;
            DocumentFile file = directory.createFile("text/*", fileName);

            assert file != null;
            ParcelFileDescriptor pfd = FilesWithExternalStorage.this
                    .getContentResolver()
                    .openFileDescriptor(file.getUri(), "w");

            FileOutputStream fos =
                    new FileOutputStream(pfd.getFileDescriptor());
            fos.write(fileContent.getBytes());
            fos.close();
        } catch (IOException ignored) {

        }
    }

    private void launchPickerUI() {
        intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        launcher.launch(intent);
    }

    @Override
    public void onActivityResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {

            assert result.getData() != null;
            Log.d("Result.data", String.valueOf(result.getData()));

            // Initialise this object in Activity.onCreate()
            baseDocumentTreeUri =
                    Objects.requireNonNull(result.getData()).getData();
            final int takeFlags = (Intent.FLAG_GRANT_READ_URI_PERMISSION |
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            // take persistable Uri Permission for future use
            FilesWithExternalStorage.this.getContentResolver()
                    .takePersistableUriPermission(result.getData().getData(),
                            takeFlags);
            SharedPreferences preferences =
                    FilesWithExternalStorage.this.getSharedPreferences(
                            getPackageName(),
                            Context.MODE_PRIVATE);
            preferences.edit().putString("filestorageuri",
                    result.getData().getData().toString()).apply();

            String fileContent =
                    etContentForFileOfExternalStorage.getText().toString();
            writeFile(FILE_NAME, fileContent);

        } else {
            Log.e("FileUtility", "Some Error Occurred : " + result);
        }
    }
}