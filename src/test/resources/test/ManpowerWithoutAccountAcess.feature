Feature: Re-Test Cases for Manpower Website without account Access

  Background:
    Given User is on homepage

  @Bug734
  Scenario: Check that renumeration page content appear
    When User get on renumeration login
    Then User should see renumeration page without error

  @Bug740
  Scenario: Check that empty content and unsecure page don't appear
    When User click on **Fondation ManpowerGroup**
    Then User should access to secure page

  @Bug743
  Scenario: Check that click process conduce to different link
    When User make a bit of click
    Then User should see different link

  @Bug757
  Scenario: Check that alternances offers filter is effective
    When User get on filter page alternance
    Then User should see specific alternance offer

  @Bug763
  Scenario: Check that empty content don't appear
    When User click on **plan de site**
    And User make a lot of click
    Then User shouldn't important empty space

  @Bug767
  Scenario: Check that chantier btp page content appear
    When User get on chantier btp
    Then User should see chantier page without error

  @Bug801
  Scenario: Check that more informations about CDD appear
    When User get on CDD page
    And User click on **more informations**
    Then User shouldn't see error page for this