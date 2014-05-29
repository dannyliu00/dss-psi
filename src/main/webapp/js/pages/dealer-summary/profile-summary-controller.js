/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    function DealerProfileSummaryCtrl ($scope, $routeParams, $location, dealerResource, dealerProfilesResource, profileDetailUrl) {
        this.location = $location;

        var dealer = {dealerId: $routeParams.dealerId};
        dealerResource.get(dealer).then(function(returnedDealer) {
            $scope.dealer = returnedDealer;
        });

        dealerProfilesResource.query(dealer).then(function(returnedProfiles) {
            $scope.profiles = returnedProfiles;
        });

        $scope.navigateToProfile = function(dealerId, profileId, type) {
            var url = profileDetailUrl.replace(':dealerId', dealerId)
                .replace(':profileId', profileId)
                .replace(':type', type);
            $location.path(url);
        };
    }

    dealerProfileSummary.DealerProfileSummaryCtrl = DealerProfileSummaryCtrl;
}());