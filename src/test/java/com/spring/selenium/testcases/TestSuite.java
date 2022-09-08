package com.spring.selenium.testcases;

import com.spring.selenium.testcases.testcases.webTests;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(webTests.class)
public class TestSuite {
}
