(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    angular.module('sellIn.pages.dealerProfileSummary', ['sellIn.resources.dealer', 'sellIn.resources.dealerProfiles'])
        .controller('dealerProfileSummaryCtrl', dealerProfileSummary.DealerProfileSummaryCtrl);
}());