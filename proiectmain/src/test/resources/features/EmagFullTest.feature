Feature: All Emag.ro scenarios

@AllTestsScenario
Scenario: Navigate all Main Meniu pages
    Given the user navigates to emag.ro page
    When cookies appear we will accept  
    Then we will navigate to Televisoare page
    Then we will navigate to and check that Oferata Zilei is for current day
    Then we will navigate to and check Resigilate page
    Then we will navigate to and check Genius page
    Then we will navigate to and check Genius Deals page
    Then we will navigate to and check EasyBuyBack page
    Then we will navigate to and check Ofertele Emag page
    Then we will add a products to favorite and try to checkout a product

@specific
Scenario: Test what's wrong
    Given the user navigates to emag.ro page
    When cookies appear we will accept  
    Then we will navigate to and check Ofertele Emag page
    Then we will add a products to favorite and try to checkout a product
