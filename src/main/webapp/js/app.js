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
        'sellIn.directives.emailentry',
        'sellIn.directives.mainbutton',
        'sellIn.directives.reasonmodal',
        'sellIn.directives.savequantities',
        'sellIn.directives.summarydatatables',
        'sellIn.directives.producttabs',
        'sellIn.directives.dsmsummarydatatables',
        'sellIn.resources.dealerProfileDetails',
        'sellIn.resources.dealerProfiles',
        'sellIn.resources.dealerProfile',
        'sellIn.resources.dealer',
        'sellIn.resources.role',
        'sellIn.resources.reasoncode',
        'sellIn.pages.dealerProfileSummary',
        'sellIn.pages.profile',
        'sellIn.pages.default',
        'sellIn.pages.dsmsummary',
        'sellIn.routing.paths',
        'sellIn.routing'
    ]);
