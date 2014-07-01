(function() {
    var dsmSummary = sellInNamespace('sellIn.pages.dsmSummary');

    angular.module('sellIn.pages.dsmSummary', ['sellIn.routing.paths', 'sellIn.resources.dealerProfileDetails'])
        .controller('dsmSummaryController', dsmSummary.DsmSummaryController);
})();