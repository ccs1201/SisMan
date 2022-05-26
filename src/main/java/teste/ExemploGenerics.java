package teste;

public class ExemploGenerics {

    public static void main(String ars[]) {

        MyGenericObject<Long, String> obj1 = new MyGenericObject<Long, String>();
        obj1.setA1((long) 123456);
        obj1.setA2("Alguma Coisa");

        MyGenericObject<String, Boolean> obj2 = new MyGenericObject<String, Boolean>();

        obj2.setA1("Alguma Coisa");
        obj2.setA2(true);

        System.out.println(obj1);

        System.out.println(obj2);

    }

}

class MyGenericObject<ATTRIBUTE1, ATTRIBUTE2> {

    private ATTRIBUTE1 a1;
    private ATTRIBUTE2 a2;

    @Override
    public String toString() {
        return "genericObject [a1= " + a1 + ", a2= " + a2 + "]";
    }

    public void setA1(ATTRIBUTE1 a1) {
        this.a1 = a1;
    }

    public void setA2(ATTRIBUTE2 a2) {
        this.a2 = a2;
    }

}
