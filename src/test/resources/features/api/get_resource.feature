@APITests
Feature: Get all books

  Scenario: Successfully get all books details as an admin
    Given User is authorized as an admin to get books
    When the admin user sends a GET all books request
    Then the status of the response should be 200
    And the response should contain list of available books with Id <bookId>

      Examples:
          | bookId | title            | author            |
          | 2      | "1984"           | "George Orwell"   |

  Scenario: Successfully get all books details as a regular user
    Given User is authorized as a regular user to get books
    When the regular user sends a GET all books request
    Then the status of the response should be 200
    And the response should contain list of available books with Id <bookId>

        Examples:
            | bookId | title            | author            |
            | 2      | "1984"           | "George Orwell"   |

  Scenario: Successfully get a book details as an admin
    Given User is authorized as an admin to get books
    When the admin user sends a GET request with ID <bookId>
    Then the status of the response should be 200
    And the response should contain a book with Id <bookId>

      Examples:
          | bookId | title            | author            |
          | 2      | "1984"           | "George Orwell"   |

  Scenario: Bug - failing to get a book details as a regular user
    Given User is authorized as a regular user to get books
    When the regular user sends a GET request with ID <bookId>
    Then the status of the response should be 200
    And the response should contain a book with Id <bookId>

      Examples:
          | bookId | title            | author            |
          | 2      | "1984"           | "George Orwell"   |

   Scenario:Admin receives a 404 error when requesting details for a non-existent book
      Given User is authorized as an admin to get books
      When the admin user sends a GET request with ID <bookId>
      Then the status of the response should be 404
      And the response should contain an error message "Book not found" for the book with Id <bookId>

        Examples:
            | bookId | title            | author                    |
            | 100    | "Madol Doova"    | "Martin Wickramasinghe"   |