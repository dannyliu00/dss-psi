(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    angular.module('sellIn.pages.dealerProfileSummary', [])
        .controller('dealerProfileSummaryCtrl', dealerProfileSummary.DealerProfileSummaryCtrl);
}());