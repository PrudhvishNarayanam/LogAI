public class MainActivity extends AppCompatActivity {
    private void logExceptionToFile(Exception e) { 
        try { 
            File logFile = new File(getFilesDir(), "app_exception_log.txt"); 
            FileWriter writer = new FileWriter(logFile, true); 
            writer.write("Exception: " + e.toString() + "\n"); 
            for (StackTraceElement element : e.getStackTrace()) { 
                writer.write("    at " + element.toString() + "\n"); 
            } 
            writer.write("
"); 
            writer.close(); 
        } catch (IOException ioException) { 
            Log.e("MainActivity", "Failed to write log file", ioException); 
        } 
    } 

    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        EdgeToEdge.enable(this); 
        setContentView(R.layout.activity_main); 
        try { 
            // Intentionally cause a NullPointerException 
            String nullString = null; 
            int length = nullString.length(); // This will throw NullPointerException 
        } catch (Exception e) { 
            logExceptionToFile(e); 
        } 
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        });
    }
}