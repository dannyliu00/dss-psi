/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    function DealerProfileSummaryCtrl ($scope, $routeParams, $location, dealerResource, dealerProfilesResource,
                                       profilePageUrl, lastTab) {
        this.location = $location;

	    var dealer = {dealerId: $routeParams.dealerId, type: $routeParams.type};
	    dealerResource.get(dealer).then(function(returnedDealer) {
	    	$scope.dealer = returnedDealer;
	        });

	    dealerProfilesResource.query(dealer).then(function(returnedProfiles) {
	        $scope.profiles = returnedProfiles;
	        });

        $scope.navigateToProfile = function(dealerId, profileId, type) {
        	lastTab.changeType('');
            var finalUrl = profilePageUrl.replace(':dealerId', dealerId)
                .replace(':profileId', profileId)
                .replace(':type', type);
            $location.path(finalUrl);
            };
    }

    dealerProfileSummary.DealerProfileSummaryCtrl = DealerProfileSummaryCtrl;
}());
