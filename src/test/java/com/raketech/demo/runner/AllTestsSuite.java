package com.raketech.demo.runner;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@SelectPackages({"com.raketech.demo.api.tests", "com.raketech.demo.ui.tests"})
@Suite
public class AllTestsSuite {
}
