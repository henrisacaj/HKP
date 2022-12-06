
public class ConcreteClassWithAnnotatedField{
	@FieldAnno(300)
	public int myAnnotatedAttr;
	
	public static void main(String[] args) {
		ConcreteClassWithAnnotatedField c = new ConcreteClassWithAnnotatedFieldConstructor();
		System.out.println(c.myAnnotatedAttr);
	}
	
}
