/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    function DealerProfileSummaryCtrl ($scope, $routeParams, dealerResource, dealerProfilesResource) {
        var dealer = {dealerId: $routeParams.dealerId};
        dealerResource.get(dealer).then(function(returnedDealer) {
            $scope.dealer = returnedDealer;
        });

        dealerProfilesResource.query(dealer).then(function(returnedProfiles) {
            $scope.profiles = returnedProfiles;
        });

    }

    dealerProfileSummary.DealerProfileSummaryCtrl = DealerProfileSummaryCtrl;
}());