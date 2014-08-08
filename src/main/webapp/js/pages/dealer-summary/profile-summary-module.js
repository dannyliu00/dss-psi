(function () {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    angular.module('sellIn.pages.dealerProfileSummary', [
        'sellIn.resources.dealer',
        'sellIn.resources.dealerProfiles',
        'sellIn.services.lasttab',
        'sellIn.routing.paths',
        'datatables',
        'blockUI'])
        .controller('dealerProfileSummaryCtrl', dealerProfileSummary.DealerProfileSummaryCtrl);
})();