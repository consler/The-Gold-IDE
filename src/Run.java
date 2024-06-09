import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Run {
    public static String run() throws IOException {
            // Create a new process and run the command
            String[] command = new String[] {"i-use-arch-btw", String.valueOf(VGlobals.curFile)}; // Can also directly be put into the process builder as an argument without it being in an array
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = null;
            try {
                process = builder.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            OutputStream stdin = process.getOutputStream();
            InputStream stdout = process.getInputStream();
            InputStream stderr = process.getErrorStream();

// Store the input and output streams in a buffer
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
            BufferedReader error = new BufferedReader(new InputStreamReader(stderr));


//        writer.write("p\n"); // Don't forget the '\n' here, otherwise it'll continue to wait for input
//        writer.flush();
//        writer.close(); // Add if doesn't work without it

// Display the output
            String line;
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return line;
            }
// Display any errors
            while (true) {
                try {
                    if (!((line = error.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return line;
            }
            return "";
    }
    public static void wrt(String txt) throws IOException {
            // Create a new process and run the command
            String[] command = new String[] {"i-use-arch-btw", String.valueOf(VGlobals.curFile)}; // Can also directly be put into the process builder as an argument without it being in an array
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = null;
            try {
                process = builder.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            OutputStream stdin = process.getOutputStream();
            InputStream stdout = process.getInputStream();
            InputStream stderr = process.getErrorStream();

// Store the input and output streams in a buffer
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
            BufferedReader error = new BufferedReader(new InputStreamReader(stderr));

            try {
                writer.write(txt + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
