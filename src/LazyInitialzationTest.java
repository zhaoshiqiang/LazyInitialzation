/**
 * Created by zhaoshiqiang on 2016/10/18.
 */
//�ӳٳ�ʼ������
public class LazyInitialzationTest {
    private volatile Cup cup1;
    private volatile Cup cup3;

    //����ʵ����ʹ��˫�ؼ��ģʽ
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

    //���ڿ��Խ����ظ���ʼ����ʵ���򣬿��Բ��õ��ؼ��ģʽ
    public Cup getCup3(){
        //�������������������ȷ��cup3ֻ�ڱ���ʼ��������¶�ȡһ�Ρ���Ȼ���Ǻ��ϸ񣬵��ǿ�����������
        Cup result = cup3;
        if (result == null){
            cup3 = result = new Cup(3);
        }
        return result;
    }
    //���ھ�̬����ʹ��lazy initialization holder class idiom

    /**
     * �༶���ڲ��࣬Ҳ���Ǿ�̬�ĳ�Աʽ�ڲ��࣬���ڲ����ʵ�����ⲿ��û�а󶨹�ϵ��
     * ����ֻ�б����õ��Ż�װ�����Ӷ�ʵ���ӳټ���
     */
    private static class SingletonHolder{
        //��̬�ĳ�ʼ���䣬��JVM��֤�̰߳�ȫ
        static Cup cup2 = new Cup(2);
    }
    /**
     * getcup2��һ�ε��õ�ʱ������һ�ζ�ȡSingletonHolder.cup2������SingletonHolder��õ���ʼ����
     * ���������װ�ز�����ʼ����ʱ�򣬻��ʼ�������ľ�̬�򣬴Ӷ�����Cup��ʵ���������Ǿ�̬����
     * ���ֻ�ᱻ��������װ�����ʱ���ʼ��һ�Σ��������������֤�����̰߳�ȫ��
     * ���lazy initialization holder class idiom���������ڣ�getcup2��û�б�ͬ����
     * ����ֻ��ִ��һ����ķ��ʣ�����ӳٳ�ʼ����û�������κη��ʳɱ�
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
