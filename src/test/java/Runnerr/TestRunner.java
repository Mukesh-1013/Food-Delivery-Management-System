package Runnerr;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import stepDefinitions.FoodDelivery;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    FoodDelivery.class // Add your test classes here
})
public class TestRunner {
    // This class remains empty, it is used only as a holder for the above annotations
}
