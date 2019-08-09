import org.testng.annotations.Test;

public class MyStudyTest {
    String s1 = "wangcheng";
    StringBuilder stringBuilder = null;
    StringBuffer stringBuffer = null;

    @Test
    public void main(){
        System.out.println(s1.hashCode());
    }
}
