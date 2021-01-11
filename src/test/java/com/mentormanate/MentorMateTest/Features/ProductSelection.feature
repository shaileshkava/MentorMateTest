@product_selection
Feature: As a user i would like to select five starred product with lowest price

  Background: 
    Given Open URL

  Scenario Outline: Select five starred 15 to 16 in lowest price laptop
    When Click on hamburger menu
    And Select "<main-category>"
    And Select Sub Caetgory "<sub-category>"
    And Select Display Size "<product-size>"
    And Select CPU Type "<cpu-type>"
    And Select Storage Type "<storage-type>"
    Then Select five starred laptop "<starred>"
    And Sort by price "<sort-by>"
    And Select lowest price product
    Then Verify Detail as Title and Price

    Examples: 
      | main-category           | sub-category | product-size | cpu-type      | storage-type | starred      | sort-by            |
      | Electronics & Computers | Laptops      | 15 to 16 in  | Intel Core i5 | SSD          | 4 Stars & Up | Price: Low to High |
