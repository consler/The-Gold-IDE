import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileW {
    public static void createDir() throws IOException {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "AU Projects");
        boolean directoryExists = Files.exists(path);
        if(!directoryExists){
            Files.createDirectories(path);
            Files.createFile(Paths.get(home, "AU Projects", "getting-started.archbtw"));
            writeToFile(
                    """ 
                       ; Example program that prints "Hello, World!"
                       
                       linux the linux linux linux linux linux linux linux i arch use way i linux btw
                       linux the linux i arch arch arch arch arch use way i arch arch btw arch arch
                       arch arch arch arch arch btw btw arch arch arch btw the linux i arch arch arch
                       arch arch use way i arch btw linux linux linux linux linux linux linux linux
                       linux linux linux linux btw linux linux linux the linux i arch arch arch use way
                       i btw linux the linux linux linux i arch use way i linux linux linux btw arch
                       arch arch btw linux linux linux linux linux linux btw linux linux linux linux
                       linux linux linux linux btw linux the linux linux linux i arch use way i btw
                       the linux linux linux i arch use way i linux btw
                       
                       ; Original Brainfuck program:
                       ; -[------->+<]>-.-[->+++++<]>++.+++++++..+++.[->+++++<]>+.------------.---[->++
                       ; +<]>.-[--->+<]>---.+++.------.--------.-[--->+<]>.[--->+<]>-.""",
                    Paths.get(home, "AU Projects", "getting-started.archbtw")
            );
        }
    }
    public static void writeToFile(String data, Path path) throws IOException {
        Files.write(path, data.getBytes());
    }

    public static String readFromFile(Path path) throws IOException {
        List<String> p = Files.readAllLines(path);
        String returnData = "";
        int j = 0;
        for (String i : p) {
            if(!(j== p.size())){
                returnData = returnData + i + "\n";
            } else {
                returnData = returnData + i;
            }
            j++;
        }
        return returnData;
    }

    public static String listFiles(String dir) {
        Set<String> p = Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
        int j=0;
        String returnData = "";
        for (String i : p) {
            if(!(j== p.size())){
                returnData = returnData + i + "\n";
            } else {
                returnData = returnData + i;
            }
            j++;
        }
        return returnData;
    }
}
