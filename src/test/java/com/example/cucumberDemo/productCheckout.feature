Feature: product checkout Feature
  Scenario Outline: check the product price
    Given the prices of the "<product>" is <price>
    When I check <quantity> of "<product>"
    Then The total price of "<product>" should be <total>

    Examples:
    | product     | price | quantity | total |
    | Headphone   | 1000  |  1       | 1000  |
    | Earphone    | 500   |  3       | 1500  |
    | Watch       | 3000  |  3       | 9000  |
    | Charger     | 2000  |  2       | 4000  |
    | Trimmer     | 3000  |  1       | 3000  |
    | Iphone 15   | 80000 |  2       | 160000|
    | MobCase     | 500   |  10      | 5000  |

