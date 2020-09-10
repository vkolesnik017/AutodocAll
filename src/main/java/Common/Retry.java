package Common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int nowCount=0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        int maxCount = 1;
        if (nowCount < maxCount) {
            nowCount++;
            return true; //пока истина перезапускаем
        }
        System.out.println("ТЕСТ ПРОВАЛЕН ДВАЖДЫ!!! "); // пишем в лог или делаем скриншот
        return false;
    }
}
