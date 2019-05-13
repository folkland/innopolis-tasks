package part01.lesson09;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Generate new SomeClass doWork method realization from console
 *
 * @author folkland
 */
public class GenerateClassFile {

    private final String PATH = "resource/lesson09/";
    private final String NAME = "SomeClass";
    private final String FILE_NAME = NAME + ".java";
    private int status;

    /**
     * Do all class logic
     */
    public GenerateClassFile() {
        String code = generateClassText();
        generateJavaFile(code);
        status = compileJavaFile();
        if (status == 0) {
            System.out.println("Success compile");
        } else {
            System.out.println("Trouble then compile");
        }
    }

    /**
     * Read from console code and write it in String
     * @return code
     */
    private String generateClassText() {
        System.out.println("Enter our code");
        StringBuilder sb = new StringBuilder();
        sb.append("package part01.lesson09;\n");
        sb.append("public class " + NAME + " implements Worker {\n");
        sb.append("@Override\n");
        sb.append("public void doWork() {\n");
        boolean b = true;
        Scanner scanner = new Scanner(System.in);
        while (b) {
            String line = scanner.nextLine();
            if (line.length() == 0) {
                b = false;
                continue;
            }
            sb.append(line + ";\n");
        }
        sb.append("}\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Generate .java file from code
     * @param code code which must be into doWork method
     */
    private void generateJavaFile(String code) {
        try (FileOutputStream fos = new FileOutputStream(PATH + FILE_NAME)) {
            fos.write(code.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compile .java file
     * @return compilation status
     */
    private int compileJavaFile() {
        JavaCompiler java = ToolProvider.getSystemJavaCompiler();
        return java.run(null, null, null, PATH + FILE_NAME);
    }

    public int getStatus() {
        return status;
    }
}
