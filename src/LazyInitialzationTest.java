/**
 * Created by zhaoshiqiang on 2016/10/18.
 */
//延迟初始化测试
public class LazyInitialzationTest {
    private volatile Cup cup1;
    private volatile Cup cup3;

    //对于实例域，使用双重检查模式
    public Cup getCup1() {
        Cup result = cup1;
        if (result == null){
            synchronized (this){
                result = cup1;
                if (result == null){
                    cup1 = result = new Cup(1);
                }
            }
        }
        return cup1;
    }

    //对于可以接受重复初始化的实例域，可以采用单重检查模式
    public Cup getCup3(){
        //定义这个变量的作用是确保cup3只在被初始化的情况下读取一次。虽然不是很严格，但是可以提升性能
        Cup result = cup3;
        if (result == null){
            cup3 = result = new Cup(3);
        }
        return result;
    }
    //对于静态域，则使用lazy initialization holder class idiom

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类没有绑定关系，
     * 而且只有被调用到才会装作，从而实现延迟加载
     */
    private static class SingletonHolder{
        //静态的初始化其，有JVM保证线程安全
        static Cup cup2 = new Cup(2);
    }
    /**
     * getcup2第一次调用的时候，它第一次读取SingletonHolder.cup2，导致SingletonHolder类得到初始化。
     * 而这个类在装载并被初始化的时候，会初始化好它的静态域，从而创建Cup的实例。由于是静态的域，
     * 因此只会被虚拟器在装载类的时候初始化一次，并有虚拟机来保证它的线程安全性
     * 这个lazy initialization holder class idiom的优势在于，getcup2并没有被同步，
     * 并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本
    */
    public static Cup getcup2(){
        return SingletonHolder.cup2;
    }
}

class Cup {
    public Cup(int i) {
        System.out.println("cup"+i);
    }
}
