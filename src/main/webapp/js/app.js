/**
 * Created by bericks on 5/12/2014.
 */

angular.module('sellIn', [
        'sellIn.filters.dateFilters',
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
        'sellIn.directives.dsmbutton',
        'sellIn.directives.reasonmodal',
        'sellIn.directives.savequantities',
        'sellIn.directives.submitValues',
        'sellIn.directives.summarydatatables',
        'sellIn.directives.producttabs',
        'sellIn.directives.nondealersummaries',
        'sellIn.directives.commentdisplay',
        'sellIn.resources.dealerProfileDetails',
        'sellIn.resources.dealerProfiles',
        'sellIn.resources.dealerProfile',
        'sellIn.resources.dealer',
        'sellIn.resources.role',
        'sellIn.resources.dsmProfiles',
        'sellIn.resources.rsmProfiles',
        'sellIn.resources.reasoncode',
        'sellIn.resources.menu',
        'sellIn.resources.attribute',
        'sellIn.pages.dealerProfileSummary',
        'sellIn.pages.profile',
        'sellIn.pages.default',
        'sellIn.pages.nondealersummary',
        'sellIn.services.lasttab',
        'sellIn.routing.paths',
        'sellIn.routing'
    ]);
