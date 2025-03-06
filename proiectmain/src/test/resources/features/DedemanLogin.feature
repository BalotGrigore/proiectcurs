Feature: Succesfully login into Dedeman.ro

    @AllTestsScenario @dedeman
    Scenario Outline:  Login into dedeman.ro with pre-registered account
    Given we navigate to Dedeman.ro page
    When cookies appear we accept them
    Then we will start login process by pressing Contul Meniu and authenticate
    When we are asked for credential to login
    Then we fill in the credentials "<username>" and "<password>"
    Then we press continue
    Then we will succesfully reach contul meu page
    Examples:
      | username | password  |
      | testgrigselenium@gmail.com | Test123# |
      