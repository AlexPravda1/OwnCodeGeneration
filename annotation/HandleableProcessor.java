package proc;

import ann.Handleable;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("ann.Handleable")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class HandleableProcessor extends AbstractProcessor {

    public HandleableProcessor() {
    }

    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getElementsAnnotatedWith(Handleable.class)) {
            if (e.getKind() != ElementKind.CLASS) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.WARNING,
                        "Skipping. Not a Class: " + e, e);
                continue;
            }
            String methodName = capitalize(e.getSimpleName().toString());
            Name className = e.getSimpleName();
            try {
                JavaFileObject f = processingEnv.getFiler().
                        createSourceFile(className + "Extras");
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                        "Creating " + f.toUri());
                Writer w = f.openWriter();
                try {
                    PrintWriter pw = new PrintWriter(w);
                    pw.println("package codegenerations." + e.getEnclosingElement().getSimpleName() + ";");
                    pw.println(" ");
                    pw.println("import codegenerations.model.User;");
                    pw.println("import org.springframework.web.bind.annotation.GetMapping;");
                    pw.println("import codegenerations.repository.UserRepository;");
                    pw.println("import org.springframework.web.bind.annotation.RestController;");
                    pw.println(" ");
                    pw.println("import java.util.List;");
                    pw.println(" ");
                    pw.println("@RestController");
                    pw.println("public class " + className + "Extras {");
                    pw.println(" ");
                    pw.println("    private final UserRepository userRepository;");
                    pw.println(" ");
                    pw.println("    protected " + className + "Extras(UserRepository userRepository) {");
                    pw.println("        this.userRepository = userRepository;");
                    pw.println("    }");
                    pw.println(" ");
                    pw.println("    @GetMapping(\"/\")");
                    pw.println("    public final String handle" + methodName + "() {");
                    pw.println("        List<User> userList = userRepository.findAll();");
                    pw.println("        return \"Total users: \" + userList.size() + \". Users in database: \" + userList;");
                    pw.println("    }");
                    pw.println("}");
                    pw.flush();
                } finally {
                    w.close();
                }
            } catch (IOException x) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        x.toString());
            }
        }
        return true;
    }

    private static String capitalize(String name) {
        char[] c = name.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        return new String(c);
    }
}
