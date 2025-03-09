@APITests
Feature: Create a new book

    Scenario: Successfully create a new book as an admin
      Given User is authorized as an admin
      When User creates a new book with title <title> and author <author>
      Then application should return status code 201 for create operation
      And the response should include the book ID
      And the book details should match the input title and author
      Examples:
            | title                    | author             |
            | "To Kill a Mockingbird"   | "Harper Lee"       |

    Scenario: Successfully create a new book as a user
      Given User is authorized as a regular user
      When User submits a POST request to create a book with title <title> and author <author>
      Then application should return status code 201 for create operation
      And the response should include the book ID
      And the book details should match the input title and author
      Examples:
            | title       | author          |
            | "1984"      | "George Orwell" |

    Scenario: Admin attempt to create a book with an existing title
        Given User is authorized as an admin
        When User creates a new book with title <title> and author <author>
        Then application should return status code 208 for already reported operation
        And the response message should be "Book Already Exists"

        Examples:
          | title                   | author             |
          | "To Kill a Mockingbird"       | "Harper Lee" |

    Scenario: User attempt to create a book with an existing title
        Given User is authorized as a regular user
        When User creates a new book with title <title> and author <author>
        Then application should return status code 208 for already reported operation
        And the response message should be "Book Already Exists"
        Examples:
          | title                   | author             |
          | "To Kill a Mockingbird" | "Harper Lee"       |

    Scenario: User attempts to create a book without a title
        Given User is authorized as a regular user
        When User creates a new book without a title and with author <author>
        Then the application should return status code 400 for bad request
        And the response message should be "Title is a mandatory field"

        Examples:
          | author       |
          | "F. Scott Fitzgerald" |


