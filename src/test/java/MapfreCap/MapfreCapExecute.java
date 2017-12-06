package MapfreCap;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/MapfreCap/MapfreTeste.feature", plugin = {
		"json:target/test-report.json" }, glue = "", monochrome = true, dryRun = false)
public class MapfreCapExecute {

}
// DEVR2
// adm
//// mcap007
// 19881
// driver.switchTo.frame("Frame_ID");