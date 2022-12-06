import java.io.*;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.regex.*;
import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.naming.*;
import javax.tools.*;
import javax.lang.model.SourceVersion;

@SupportedSourceVersion(SourceVersion.RELEASE_17)
@SupportedAnnotationTypes("FieldAnno")
public class FieldAnnoProcessor extends AbstractProcessor {
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for ( TypeElement annotation : annotations ) {
			for ( Element element : roundEnv.getElementsAnnotatedWith(annotation) ) {
				String className = element.getEnclosingElement().toString();
				String newClassName = element.getEnclosingElement().toString() + "Constructor";
				String annotationValue = element.getAnnotationMirrors().get(0).toString().replaceAll("[\\D]", "");
		        try(Writer writer = processingEnv.getFiler().createSourceFile(newClassName).openWriter();
		            PrintWriter printWriter = new PrintWriter(writer)) {
		            printWriter.printf("public class %s extends %s {%n", newClassName, className);
		            printWriter.printf("  public %s(){%n", newClassName);
		            printWriter.printf("    this.%s = %s;%n", element.toString(), annotationValue);
		            printWriter.printf("  }%n");
		            printWriter.printf("}%n");
		        } catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
		return true;
	}
				
		        
}