package tests;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import java.util.ArrayList;
import java.util.List;

public class RunAllTests {

    public static void main(String[] args) {
        List<Class> testCases = new ArrayList();
        boolean isFailedTest = false;

        testCases.add(TwitterTestCasesPositiveScenarios.class);
        testCases.add(TwitterTestCasesNegativeScenarios.class);

        JUnitCore jUnitCore = new JUnitCore();
        jUnitCore.addListener(new TextListener(System.out));

        for (Class testCase : testCases) {
            Result result = jUnitCore.run(testCase);
            if (result.getFailureCount() != 0) {
                isFailedTest = true;
            }
        }

        if (isFailedTest) {
            System.out.println("==== TESTS FAILED ====\n" +
                    "At least one of tests failed.\nPlease see above logs for more details.");
        } else {

            System.out.println("=== ALL TESTS PASSED ===");
        }
    }
}
