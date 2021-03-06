package com.mingo;

import static org.testng.AssertJUnit.assertEquals;
import com.google.common.collect.Maps;
import com.mingo.query.analyzer.SpringELQueryAnalyzer;
import org.apache.commons.lang3.time.DateUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Map;

/**
 * Test for {@link com.mingo.query.analyzer.SpringELQueryAnalyzer}
 */
public class SpringELQueryAnalyzerTest {


    private SpringELQueryAnalyzer springELQueryAnalyzer = new SpringELQueryAnalyzer();

    @DataProvider(name = "testExpressions")
    public Object[][] testExpressions() {
        return new Object[][]{

            // string
            {"#string != null", new ParameterBuilder().add("string", "val").build(), true},
            {"#string.length() == #length", new ParameterBuilder().add("string", "val")
                .add("length", "val".length()).build(), true},
        };
    }

    @Test(dataProvider = "testExpressions")
    public void testVerify(String exp, Map<String, Object> parameters, boolean result) {
        assertEquals(result, springELQueryAnalyzer.evaluate(exp, parameters));
    }

    /**
     * Creates date.
     *
     * @param year the amount to add, may be negative
     * @return the new date object with the amount added
     */
    private Date createDateAndAddYear(int year) {
        return DateUtils.addYears(new Date(), year);
    }

    private class ParameterBuilder {
        private Map<String, Object> parameters = Maps.newHashMap();

        public ParameterBuilder add(String key, Object val) {
            parameters.put(key, val);
            return this;
        }

        public Map<String, Object> build() {
            return parameters;
        }
    }

}
