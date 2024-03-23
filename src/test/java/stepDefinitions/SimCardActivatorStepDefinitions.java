package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimCardActivatorStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private String iccid;
    private ResponseEntity<String> response;

    @Given("a SIM card with ICCID {string}")
    public void a_SIM_card_with_ICCID(String iccid) {
        this.iccid = iccid;
    }

    @When("the activation request is submitted")
    public void the_activation_request_is_submitted() {
        String url = "http://localhost:8080/activate";
        response = restTemplate.postForEntity(url, iccid, String.class);
    }

    @Then("the activation should be successful")
    public void the_activation_should_be_successful() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SIM card activated successfully.", response.getBody());
    }

    @Then("the activation should fail")
    public void the_activation_should_fail() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to activate SIM card.", response.getBody());
    }
}
