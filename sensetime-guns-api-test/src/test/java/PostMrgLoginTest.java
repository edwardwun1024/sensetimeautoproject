import guns.appcaller.GunsAppCaller;
import guns.bean.MrgLoginRequestBean;
import org.apache.http.HttpStatus;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class PostMrgLoginTest {

    private GunsAppCaller gunsAppCaller;


    @DataProvider(name = "postMrgLoginTestData")
    public Object[][] postMrgLoginTestData(ITestContext context){
        System.out.println("#############"+context.getName());
        MrgLoginRequestBean requestBean = new MrgLoginRequestBean("Edward","password123","内部用户：1",0001);


        return new Object[][]{
                {"testcase1:desc",requestBean, HttpStatus.SC_OK},
        };

    }

    @Test(dataProvider = "postMrgLoginTestData")
    public void TestPostMrgLoginTestCase(String caseDes, MrgLoginRequestBean requestBean, HttpStatus status){
        String responseBody = gunsAppCaller.postMrgLogin(requestBean);
        System.out.print(responseBody);
    }

}
