/**
 * Created by bericks on 5/12/2014.
 */

angular.module('sellIn', [
    'sellIn.filters.dateFilters',
    'polaris.directives.commonHeader',
    'polaris.directives.commonFooter',
    'polaris.directives.menu',
    'polaris.directives.tabs',
    'polaris.directives.pagination',
    'sellIn.directives.unsavedChanges',
    'sellIn.directives.resultsmodal',
    'sellIn.directives.profiles',
    'sellIn.directives.dealerInfo',
    'sellIn.directives.numbersonly',
    'sellIn.directives.emailentry',
    'sellIn.directives.mainbutton',
    'sellIn.directives.spinnerinput',
    'sellIn.directives.dsmbutton',
    'sellIn.directives.rsmbutton',
    'sellIn.directives.reasonmodal',
    'sellIn.directives.savequantities',
    'sellIn.directives.emailrequired',
    'sellIn.directives.reasonrequired',
    'sellIn.directives.submitValues',
    'sellIn.directives.summarydatatables',
    'sellIn.directives.producttabs',
    'sellIn.directives.nondealersummaries',
    'sellIn.directives.commentdisplay',
    'sellIn.directives.translate',
    'sellIn.resources.dealerProfileDetails',
    'sellIn.resources.dealerProfiles',
    'sellIn.resources.dealerProfile',
    'sellIn.resources.dealer',
    'sellIn.resources.role',
    'sellIn.resources.dsmProfiles',
    'sellIn.resources.rsmProfiles',
    'sellIn.resources.reasoncode',
    'sellIn.resources.menu',
    'sellIn.resources.translate',
    'sellIn.resources.attribute',
    'sellIn.pages.dealerProfileSummary',
    'sellIn.pages.profile',
    'sellIn.pages.default',
    'sellIn.pages.nondealersummary',
    'sellIn.services.lasttab',
    'sellIn.services.currentdealer',
    'sellIn.services.translation',
    'sellIn.routing.paths',
    'sellIn.routing',
    'blockUI'
]).config(function (blockUIConfigProvider) {

    // Change the default overlay message
    blockUIConfigProvider.message('Please wait...');

    // Change the default delay to 100ms before the blocking is visible
    blockUIConfigProvider.delay(100);

});
