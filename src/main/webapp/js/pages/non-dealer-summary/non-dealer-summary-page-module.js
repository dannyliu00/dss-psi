(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    angular.module('sellIn.pages.nondealersummary', [
        'ngRoute',
        'sellIn.routing.paths',
        'sellIn.directives.nondealersummaries',
        'sellIn.resources.rsmProfiles',
        'sellIn.resources.dsmProfiles',
        'sellIn.resources.role'])
        .controller('nonDealerSummaryController', nonDealerSummary.NonDealerSummaryController);
})();