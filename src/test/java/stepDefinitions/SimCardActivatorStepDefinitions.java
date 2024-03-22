package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private String iccid;
    private String customerEmail;
    private String activationResponse;

    @Given("^a SIM card with ICCID \"([^\"]*)\"$")
    public void givenASimCardWithICCID(String iccid) {
        this.iccid = iccid;
    }

    @Given("^a customer email address \"([^\"]*)\"$")
    public void givenACustomerEmailAddress(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @When("^the activation request is sent$")
    public void whenTheActivationRequestIsSent() {
        String requestBody = "{\"iccid\": \"" + iccid + "\", \"customerEmail\": \"" + customerEmail + "\"}";
        activationResponse = restTemplate.postForObject("/activate", requestBody, String.class);
    }

    @Then("^the SIM card should be successfully activated$")
    public void thenTheSimCardShouldBeSuccessfullyActivated() {
        assertEquals("SIM card activated successfully.", activationResponse);
    }

    @Then("^the activation should fail$")
    public void thenTheActivationShouldFail() {
        assertEquals("Failed to activate SIM card.", activationResponse);
    }
}
