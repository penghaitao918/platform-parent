package com.fanfandou.admin.system.test;

/**
 * Created by wangzhenwei on 2016/4/29.
 * Description 泛型
 */
public class Test2 {

    /*
1.在使用泛型类时，虽然传入了不同的泛型实参，但并没有真正意义上生成不同的类型，
传入不同泛型实参的泛型类在内存上只有一个，即还是原来的最基本的类型（本实例中为Box），
当然，在逻辑上我们可以理解成多个不同的泛型类型。
究其原因，在于Java中的泛型这一概念提出的目的，导致其只是作用于代码编译阶段，
在编译过程中，对于正确检验泛型结果后，会将泛型的相关信息擦出，也就是说，
成功编译过后的class文件中是不包含任何泛型信息的。泛型信息不会进入到运行时阶段。

对此总结成一句话：泛型类型在逻辑上可以看成是多个不同的类型，实际上都是相同的基本类型。*/
    /*public static void main(String[] args) {

        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(712);

        System.out.println("name class:" + name.getClass());
        System.out.println("age class:" + age.getClass());
        System.out.println(name.getClass() == age.getClass());
    }*/

   /* 2.Number是Integer的父类，但Box<Number>不是Box<Integer>的父类*/
    /*public static void main(String[] args) {

        Box<Number> name = new Box<Number>(99);
        Box<Integer> age = new Box<Integer>(712);

        getData(name);

        //The method getData(Box<Number>) in the type GenericTest is
        //not applicable for the arguments (Box<Integer>)
        getData(age);   // 1

    }

    public static void getData(Box<Number> data){
        System.out.println("data :" + data.getData());
    }*/



/*  3.  类型通配符一般是使用 ? 代替具体的类型实参。注意了，此处是类型实参，而不是类型形参！
且Box<?>在逻辑上是Box<Integer>、Box<Number>...等所有Box<具体类型实参>的父类。
由此，我们依然可以定义泛型方法，来完成此类需求。*/
    /*public static void main(String[] args) {

        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(712);
        Box<Number> number = new Box<Number>(314);

        getData(name);
        getData(age);
        getData(number);
    }

    public static void getData(Box<?> data) {
        System.out.println("data :" + data.getData());
    }*/

    /*4.类型通配符上限和类型通配符下限
    只能是Number类及其子类。此时，需要用到类型通配符上限。
    类型通配符上限通过形如Box<? extends Number>形式定义，
    相对应的，类型通配符下限为Box<? super Number>形式，其含义与类型通配符上限正好相反*/

    /*public static void main(String[] args) {


        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(712);
        Box<Number> number = new Box<Number>(314);

        getData(name);
        getData(age);
        getData(number);

        getUpperNumberData(name); // 1
        getUpperNumberData(age);    // 2
        getUpperNumberData(number); // 3
    }

    public static void getData(Box<?> data) {
        System.out.println("data :" + data.getData());
    }

    public static void getUpperNumberData(Box<? extends Number> data){
        System.out.println("data :" + data.getData());
    }*/

}

class Box<T> {

    private T data;

    public Box() {

    }

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}






