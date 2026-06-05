Feature: Place an order


  @positive
  Scenario Outline: User places an order successfully

    Given user lands on ecommerce platform
    Given User logs in with email "<email>" and password "<password>"
    When User adds product "<productName>" to the cart
    And checkout "<productName>" and submit the order
    Then order confirmation "THANKYOU FOR THE ORDER." should be displayed

    Examples:
      | email                | password | productName |
      | karthiTest@outlook.com  | karthiTest@123 | ZARA COAT 3 |
      | carthi@gmail.com   | N@ruto1999 | ADIDAS SHOES |