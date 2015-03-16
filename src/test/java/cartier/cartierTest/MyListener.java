package cartier.cartierTest;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class MyListener extends TestListenerAdapter{
	private int m_count = 0;
	 
    @Override
    public void onTestFailure(ITestResult tr) {
        log(tr.getName()+ "--Test method failed\n");   
        log(LogUtil.getLog().toString());
        LogUtil.clearLog();
    }
	 
    @Override
    public void onTestSkipped(ITestResult tr) {
        log(tr.getName()+ "--Test method skipped\n");
        LogUtil.clearLog();
    }
	 
    @Override
    public void onTestSuccess(ITestResult tr) {
        log(tr.getName()+ "--Test method success\n");
        LogUtil.clearLog();
    }
	 
    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
        	System.out.println("");
        }
    }
}
