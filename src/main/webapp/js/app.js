/**
 * Created by bericks on 5/12/2014.
 */

angular.module('sellIn', [
        'ngRoute',
        'polaris.directives.commonHeader',
        'polaris.directives.commonSearch',
        'polaris.directives.commonFooter',
        'polaris.directives.tabs',
        'polaris.directives.pagination',
        'sellIn.directives.unsavedChanges',
        'sellIn.directives.profiles',
        'sellIn.directives.dealerInfo',
        'sellIn.directives.numbersonly',
        'sellIn.resources.dealerProfiles',
        'sellIn.resources.dealerProfile',
        'sellIn.resources.dealer',
        'sellIn.resources.role',
        'sellIn.pages.dealerProfileSummary',
        'sellIn.pages.profile',
        'sellIn.pages.default',
        'sellIn.routing.paths',
        'sellIn.routing'
    ]);
