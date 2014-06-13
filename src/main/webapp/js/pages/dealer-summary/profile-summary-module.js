(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    angular.module('sellIn.pages.dealerProfileSummary', ['sellIn.resources.dealer', 'sellIn.resources.dealerProfiles', 'sellIn.routing.paths','datatables'])
        .controller('dealerProfileSummaryCtrl', dealerProfileSummary.DealerProfileSummaryCtrl);
})();