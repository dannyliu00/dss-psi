(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    angular.module('sellIn.pages.nondealersummary', ['sellIn.routing.paths','sellIn.directives.nondealersummaries'])
        .controller('nonDealerSummaryController', nonDealerSummary.NonDealerSummaryController);
})();