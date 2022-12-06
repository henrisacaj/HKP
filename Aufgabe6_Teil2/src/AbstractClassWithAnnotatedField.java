import java.lang.reflect.*;

public class AbstractClassWithAnnotatedField {
	
	public AbstractClassWithAnnotatedField() {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(FieldAnno.class)) {
				FieldAnno annotation = field.getAnnotation(FieldAnno.class);
				field.setAccessible(true);
				try {
					field.setInt(this, annotation.value());
				} catch (IllegalAccessException i) {
					System.out.println("");
				}
				
			}
		}
	}
}
