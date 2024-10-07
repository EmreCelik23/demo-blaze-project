Feature: This feature includes tests for view products in Demo Blaze Website...

  Background:
    * url 'https://api.demoblaze.com/view'
    * def resSchema = read('classpath:data/viewProductRes.json')

  @load
  Scenario Outline: Viewing Products <id>
    Given request {id:<id>}
    When method POST
    Then status 200
    And match response == resSchema
    * print response


    Examples:
      | id |
      | 1  |
      | 2  |
      | 3  |